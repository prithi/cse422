import java.util.ArrayList;

public class Dijkstra {

	static int start;
	static int end;
	static ArrayList<Integer>[] list;
	static int noOfNodes;
	static double[][] weight;
	static String[] cityList;
	static double[] h;

	/*************************************************************************/

	public Dijkstra(int start, int finish, ArrayList<Integer>[] adjancencyList, int noOfNodes, double[][] weight,
			String[] cityList, double[] h) {

		this.start = start;
		this.end = finish;
		this.list = adjancencyList;
		this.noOfNodes = noOfNodes;
		this.weight = weight;
		this.cityList = cityList;
		this.h = h;

	}

	/*************************************************************************/

	public static double[] dijkstra() {
		final double[] dist = new double[noOfNodes];
		final int[] pred = new int[noOfNodes];
		final boolean[] visited = new boolean[noOfNodes];
		double path = 0;
		for (int i = 0; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;

		for (int i = 0; i < dist.length; i++) {
			final int parent = minVertex(dist, visited);
			// System.out.println(convCityIndex(current, cityList));
			visited[parent] = true;

			for (int j = 0; j < list[parent].size(); j++) {
				int child = list[parent].get(j);

				final double d = dist[parent] + weight[parent][child] + h[parent];

				if (dist[child] > d) {
					dist[child] = d;
					pred[child] = parent;

					// System.out.println(convCityIndex(parent, cityList) + " to
					// " + convCityIndex(child, cityList) + " : " + d );
				}
			}
			System.out.println();
		}

		printPath(pred);
		return dist;
	}

	/*************************************************************************/

	private static int minVertex(double[] dist, boolean[] v) {
		double x = Integer.MAX_VALUE;
		int y = -1;
		for (int i = 0; i < dist.length; i++) {
			if (!v[i] && dist[i] < x) {
				y = i;
				x = dist[i];
				//System.out.println(convCityIndex(i));

			}
		}
		return y;
	}

	/*************************************************************************/

	public static void printPath(int[] pred) {
		final ArrayList<String> path = new ArrayList<String>();
		int x = end;
		double pathCost = 0;
		while (x != start) {
			path.add(0, convCityIndex(x));
			pathCost = pathCost + weight[pred[x]][x];
			x = pred[x];

		}
		path.add(0, convCityIndex(start));
		System.out.println("Shortest Path from " + convCityIndex(start) + " to " + convCityIndex(end) + ": ");

		for (int i = 0; i < path.size() - 1; i++) {
			System.out.print(path.get(i) + " -->> ");
		}

		System.out.print(path.get(path.size() - 1));

		System.out.println();

		System.out.println("Total Cost is : " + pathCost);
	}

	/*************************************************************************/

	private static String convCityIndex(int index) {
		String city;

		city = cityList[index];

		return city;
	}

}