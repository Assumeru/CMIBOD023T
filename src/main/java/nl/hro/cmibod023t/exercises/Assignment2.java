package nl.hro.cmibod023t.exercises;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.analysis.AnalysisLauncher;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import nl.hro.cmibod023t.cluster.Cluster;
import nl.hro.cmibod023t.cluster.KDPointCollection;
import nl.hro.cmibod023t.cluster.dbscan.Dbscan;

public class Assignment2 {
	private static final long SEED = 0;
	private static final double EPSILON = 0.1;
	private static final int MIN_POINTS = 10;

	public static void main(String[] args) throws Exception {
		File file = new File(args[0]);
		List<Star> stars = parseStars(file);
		Dbscan<Star> dbscan = new Dbscan<>(MIN_POINTS, new KDPointCollection<>(EPSILON));
		dbscan.addPoints(stars);
		Collection<Cluster<Star>> clusters = dbscan.cluster();
		System.out.println("Clusters: " + clusters.size());
		for(Cluster<Star> c : clusters) {
			System.out.println(c.size());
		}
		draw(stars, clusters);
	}

	private static List<Star> parseStars(File file) throws IOException {
		List<Star> stars = new ArrayList<>(64000);
		try(Scanner sc = new Scanner(file)) {
			sc.nextLine();
			while(sc.hasNextLine()) {
				String[] values = sc.nextLine().split(",");
				stars.add(new Star(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2]), Integer.parseInt(values[7])));
			}
		}
		return stars;
	}

	private static void draw(List<Star> stars, Collection<Cluster<Star>> clusters) throws Exception {
		Coord3d[] points = new Coord3d[stars.size()];
		Color[] colors = new Color[stars.size()];
		Map<Cluster<Star>, Color> colorMap = getColors(clusters);
		for(int i = 0; i < points.length; i++) {
			Star star = stars.get(i);
			points[i] = new Coord3d(star.getX(), star.getY(), star.getZ());
			colors[i] = colorMap.get(star.getCluster());
		}
		Scatter scatter = new Scatter(points, colors);
		AnalysisLauncher.open(new AbstractAnalysis() {
			@Override
			public void init() throws Exception {
				chart = AWTChartComponentFactory.chart(Quality.Fastest);
				chart.getScene().add(scatter);
			}
		});
	}

	private static Map<Cluster<Star>, Color> getColors(Collection<Cluster<Star>> clusters) {
		Map<Cluster<Star>, Color> colorMap = new HashMap<>();
		colorMap.put(null, new Color(0, 0, 0));
		Random random = new Random(SEED);
		for(Cluster<Star> cluster : clusters) {
			float r = random.nextFloat();
			float g = random.nextFloat();
			float b = random.nextFloat();
			colorMap.put(cluster, new Color(r, g, b));
		}
		return colorMap;
	}

	private Assignment2() {
	}
}
