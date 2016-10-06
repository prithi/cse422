
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class graph {

	static int noOfNodes, start, finish;
	static int noOfEdges;
	static int adjacencyMatrix[][] = null;
	static String[] cityList;
	static int[] brokenRoute, edgeRoute;

	
	/********************************************************************************/
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

	
	/********************************************************************************/
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
				String startCity = scn.nextLine();
				start = checkCityIndex(startCity);
				String finishCity = scn.nextLine();
				finish = checkCityIndex(finishCity);

				int brokenRoads = scn.nextInt();
				scn.nextLine();
				System.out.println("Total No of broken roads are : " + brokenRoads);
				brokenRoute = new int[noOfNodes];
				for (int i = 0; i < brokenRoads; i++) {
					
					String temp = scn.nextLine();
					String[] brokenTemp = temp.split(",", 2);
					int j = 0;
					int temp1 = checkCityIndex(brokenTemp[j]);
					int temp2 = checkCityIndex(brokenTemp[j + 1]);
					
					brokenRoute[temp1] = temp2;
					brokenRoute[temp2] = temp1;
					
					System.out.println(convCityIndex(brokenRoute[temp2]) + " to " + convCityIndex(brokenRoute[temp1]));
					
				}
				
				scn.nextLine();
				for (int i = 0; i < noOfNodes; i++) {
					adjacencyList[i] = new ArrayList<Integer>();
				}
				
				edgeRoute = new int[noOfNodes];
				System.out.println();
				System.out.println("Total No of roads are : " + noOfEdges);
				for (int i = 0; i < noOfEdges; i++) {
					
					String temp = scn.nextLine();
					String[] brokenTemp = temp.split(",", 2);
					int j = 0;
					int u = checkCityIndex(brokenTemp[j]);
					int v = checkCityIndex(brokenTemp[j + 1]);
					
					adjacencyList[u].add(v);
					adjacencyList[v].add(u);
					edgeRoute[u] = v;
				    edgeRoute[v] = u;
					
				
					System.out.println(convCityIndex(edgeRoute[v]) + " to "+ convCityIndex(edgeRoute[u]));
					
				}
				System.out.println();
				
				//for (int i = 0; i < noOfEdges; i++) {
				//	int u = i;
				//	int v = edgeRoute[u];

				//	adjacencyList[u].add(v);
				//	adjacencyList[v].add(u);
				//}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return adjacencyList;
	}
	
	
	/********************************************************************************/
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
	
	
	/********************************************************************************/
	private String convCityIndex(int index) {
		String city;
		
		city = cityList[index];

		return city;
	}
	
	
	/********************************************************************************/
	public void printArray(ArrayList<Integer>[] adjList) {
		int size = adjList.length;
		for (int i = 0; i < size; i++) {
			System.out.print(convCityIndex(i) + " --> " );
			for (int j = 0; j < adjList[i].size(); j++) {
				System.out.print(convCityIndex(adjList[i].get(j)) + "   " );
			}
			System.out.println();
		}
	}


	/********************************************************************************/
	public static void main(String[] args) throws FileNotFoundException {
		graph g = new graph();
		ArrayList<Integer>[] adjancencyList = g.readFromFile("graph.txt");
		g.printArray(adjancencyList);

		Scanner kb = new Scanner(System.in);
		
		BFS b = new BFS(start, finish, adjancencyList, noOfEdges, brokenRoute);
		
		ArrayList<Integer> length = b.lengths();
		System.out.println();

		System.out.print("Shortest Path: ");
		ArrayList<String> convCity = new ArrayList<String>();
		String convertedCity= null;
		for (int i=0; i<length.size(); i++){
		convertedCity = g.convCityIndex((int) length.get(i));
			convCity.add(convertedCity);
			
		}
		
		int k = -1;
		for (int i=0; i<convCity.size()-1; i++){
			System.out.print(convCity.get(i) + " -> ");
				k=i;
			}
		
		System.out.print(convCity.get(k+1));
		
	

	}
}
