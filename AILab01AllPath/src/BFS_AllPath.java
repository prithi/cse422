import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_AllPath {

	static int number_of_cities;
	static int number_of_roads;
	static String start;
	static String end;
	static String city_list[];
	static int graph_matrix[][];

	static String c[];
	static int parent[];
	static int level[];

	public static void main(String[] args) throws IOException {

		fileReader();
		c = new String[number_of_cities];
		parent = new int[number_of_cities];
		level = new int[number_of_cities];

		System.out.println("BFS has benn executed");
		bfs(search(city_list, start), search(city_list, end));

		Object res[] = print(search(city_list, end), search(city_list, start));

		for (int i = res.length - 1; i >= 0; i--) {
			System.out.print(String.valueOf(res[i]) + "->");
		}
		System.out.println("\nThis is the best path");
		bfs(search(city_list, start), search(city_list, end), true);
	}

	public static void fileReader() throws FileNotFoundException, IOException {
		FileReader f = new FileReader("sample.txt");
		BufferedReader k = new BufferedReader(f);

		String line = k.readLine();
		String temp[] = line.split(",");
		number_of_cities = Integer.parseInt(temp[0]);
		number_of_roads = Integer.parseInt(temp[1]);
		city_list = new String[number_of_cities];
		graph_matrix = new int[number_of_cities][number_of_cities];

		start = k.readLine();
		end = k.readLine();

		int count = 0;

		for (int i = 0; i < number_of_roads; i++) {
			String temp2 = k.readLine();
			String roads[] = temp2.split(",");

			int row = count;
			int r = search(city_list, roads[0]);
			if (r == -1) {
				city_list[count] = roads[0];
				count++;
			} else {
				row = r;
			}
			int col = count;
			r = search(city_list, roads[1]);
			if (r == -1) {
				city_list[count] = roads[1];
				count++;
			} else {
				col = r;
			}

			graph_matrix[row][col] = graph_matrix[col][row] = Integer.parseInt(roads[2]);
			// both way check ty like 01 10

		}

	}// End of read File Method

	public static int search(String[] arr, String val) {
		// System.out.println(val);
		for (int i = 0; i < arr.length; i++) {

			if (arr[i] != null) {
				if (arr[i].equalsIgnoreCase(val)) {

					return i;
				}
			}
		}
		return -1;
	}// End of function search

	public static void bfs(int start, int end) {

		for (int i = 0; i < number_of_cities; i++) {
			c[i] = "w"; // w=not visited
			parent[i] = -1;
		}
		Queue<Integer> q = new LinkedList<>();

		q.add(start);

		while (!q.isEmpty()) {
			int t = q.poll();

			// System.out.print(city_list[t]+"->");
			for (int i = 0; i < number_of_cities; i++) {
				if (graph_matrix[t][i] > 0 && c[i].equals("w")) {//
					c[i] = "g";
					parent[i] = t;
					level[i] = level[t] + 1; // counter
					q.add(i);
				}
				if (t == end) {
					break;
				}
			}
		}
	}

	public static void bfs(int start, int end, boolean v) {

		for (int i = 0; i < number_of_cities; i++) {
			c[i] = "w"; // w=not visited
			parent[i] = -1;
		}
		Queue<Integer> q = new LinkedList<>();

		q.add(start);

		while (!q.isEmpty()) {
			int t = q.poll();

			System.out.print(city_list[t] + "->");
			for (int i = 0; i < number_of_cities; i++) {
				if (graph_matrix[t][i] > 0 && c[i].equals("w")) {
					c[i] = "g";
					parent[i] = t;
					level[i] = level[t] + 1; // counter
					q.add(i);
				} else if (graph_matrix[t][i] < 0 && c[i].equals("w")) {
					System.out.println("A broken path has been found " + city_list[t] + "  to " + city_list[i]);
				}
				if (t == end) {
					break;
				}
			}
		}

	}

	public static Object[] print(int end, int start) {
		ArrayList as = new ArrayList();

		int i = end;

		while (true) {
			as.add(city_list[i]);
			// System.out.print(city_list[i]+"->");
			if (i == start) {
				break;
			}

			i = parent[i];
		}

		return as.toArray();
	}// End of method print

}
