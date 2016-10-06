import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class astar {

	static int noOfNodes, start, finish;
	static int noOfEdges;
	static int adjacencyMatrix[][] = null;
	static String[] cityList;
	static int[] brokenRoute, edgeRoute;
	static double[][] weight = null;
	static double[] h;

	/*************************************************************************/

	public String[] cityList(String filename) throws FileNotFoundException {
		String[] cityList = new String[noOfNodes];

		Scanner k = new Scanner(new File(filename));

		int i = 0;
		while (k.hasNextLine()) {

			String temp = k.nextLine();

			cityList[i] = temp;
			i++;

		}

		return cityList;
	}
	/*************************************************************************/

	public ArrayList<Integer>[] readFromFile(String filename) throws FileNotFoundException {
		ArrayList<Integer>[] adjacencyList = null;

		try {
			File f = new File(filename);
			if (f.exists()) {
				Scanner scn = new Scanner(f);
				noOfNodes = scn.nextInt();
				noOfEdges = scn.nextInt();
				adjacencyList = new ArrayList[noOfNodes];

				cityList = cityList("cities.txt");

				scn.nextLine();

				String temps = scn.nextLine();
				String[] broTemp = temps.split(" ");
				String startCity = broTemp[0];
				// System.out.println(startCity);
				start = checkCityIndex(startCity);
				// System.out.println(start);
				temps = scn.nextLine();
				broTemp = temps.split(" ");
				String finishCity = broTemp[0];
				// System.out.println(finishCity);
				finish = checkCityIndex(finishCity);
				// System.out.println(finish);

				// scn.nextLine();
				for (int i = 0; i < noOfNodes; i++) {
					adjacencyList[i] = new ArrayList<Integer>();
				}

				edgeRoute = new int[noOfNodes];
				weight = new double[noOfNodes][noOfNodes];
				h = new double[noOfNodes];
				System.out.println();
				System.out.println("Total No of roads are : " + noOfEdges);
				for (int i = 0; i < noOfEdges; i++) {

					String temp = scn.nextLine();
					String[] brokenTemp = temp.split(" ", 3);
					int j = 0;
					int u = checkCityIndex(brokenTemp[j]);
					int v = checkCityIndex(brokenTemp[j + 1]);

					adjacencyList[u].add(v);
					adjacencyList[v].add(u);
					edgeRoute[u] = v;
					edgeRoute[v] = u;

					weight[u][v] = Double.parseDouble(brokenTemp[j + 2]);

					weight[v][u] = Double.parseDouble(brokenTemp[j + 2]);
					// System.out.println(weight[v][u]);
					System.out.print(convCityIndex(edgeRoute[v]) + " " + convCityIndex(edgeRoute[u]));
					System.out.print(" " + weight[u][v]);
					System.out.println();

				}

				System.out.println();

				for (int i = 0; i < noOfNodes; i++) {

					String temp = scn.nextLine();
					String[] brokenTemp = temp.split(" ", 2);
					int j = 0;
					int u = checkCityIndex(brokenTemp[j]);

					h[u] = Double.parseDouble(brokenTemp[j + 1]);

					System.out.print(convCityIndex(u));
					System.out.print(" " + h[u]);
					System.out.println();

				}

				System.out.println();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return adjacencyList;
	}
	/*************************************************************************/

	public int checkCityIndex(String city) {
		int index = -1;
		for (int i = 0; i < cityList.length; i++) {

			if (city.equals(cityList[i])) {
				index = i;
				break;
			}

		}

		return index;
	}
	/*************************************************************************/

	private String convCityIndex(int index) {
		String city;

		city = cityList[index];

		return city;
	}

	public void printArray(ArrayList<Integer>[] adjList) {
		int size = adjList.length;
		for (int i = 0; i < size; i++) {
			System.out.print(convCityIndex(i) + " --> " );
			for (int j = 0; j < adjList[i].size(); j++) {
				System.out.print(convCityIndex(adjList[i].get(j)) + "   " );
			}
			System.out.println();
		}
		System.out.println();
	}

	/*************************************************************************/

	public static void main(String[] args) throws FileNotFoundException {
		astar g = new astar();
		ArrayList<Integer>[] adjancencyList = g.readFromFile("graph.txt");
		g.printArray(adjancencyList);

		Scanner kb = new Scanner(System.in);
		
		Dijkstra d = new Dijkstra(start, finish, adjancencyList, noOfNodes, weight, cityList, h);

		double[] gOfN = d.dijkstra();

		
	}
}