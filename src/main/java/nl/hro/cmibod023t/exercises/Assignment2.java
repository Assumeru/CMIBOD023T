package nl.hro.cmibod023t.exercises;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import nl.hro.cmibod023t.cluster.Cluster;
import nl.hro.cmibod023t.cluster.KDPointCollection;
import nl.hro.cmibod023t.cluster.dbscan.Dbscan;

public class Assignment2 {
	private static final double EPSILON = 0.05;
	private static final int MIN_POINTS = 10;

	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		List<Star> stars = parseStars(file);
		Dbscan<Star> dbscan = new Dbscan<>(MIN_POINTS, new KDPointCollection<>(EPSILON));
		dbscan.addPoints(stars);
		Collection<Cluster<Star>> clusters = dbscan.cluster();
		System.out.println("Clusters: " + clusters.size());
		for(Cluster<Star> c : clusters) {
			System.out.println(c.size());
		}
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
}
