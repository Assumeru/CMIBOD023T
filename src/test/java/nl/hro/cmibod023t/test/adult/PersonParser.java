package nl.hro.cmibod023t.test.adult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonParser {
	private final File file;

	public PersonParser(File file) {
		this.file = file;
	}

	public List<Person> parsePeople() throws IOException {
		List<Person> people = new ArrayList<>();
		try(Scanner sc = new Scanner(file)) {
			int line = 0;
			while(sc.hasNextLine()) {
				String ln = sc.nextLine();
				line++;
				if(ln.isEmpty()) {
					continue;
				}
				String[] columns = ln.split("\\s*,\\s");
				try {
					people.add(new Person(
							Integer.parseInt(columns[0]),
							columns[1],
							Integer.parseInt(columns[2]),
							columns[3],
							Integer.parseInt(columns[4]),
							columns[5],
							columns[6],
							columns[7],
							columns[8],
							columns[9],
							Integer.parseInt(columns[10]),
							Integer.parseInt(columns[11]),
							Integer.parseInt(columns[12]),
							columns[13],
							columns[14]
					));
				} catch(Exception e) {
					System.err.println("Error on line " + line + ": " + e.getMessage());
				}
			}
		}
		return people;
	}
}
