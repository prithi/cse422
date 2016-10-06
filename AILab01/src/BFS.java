
import java.util.Queue;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BFS {
	
	static int start;
	static int finish; 
	static ArrayList<Integer>[] list;
	static int noOfEdges;
	static int[] brokenRoute;
	
	/********************************************************************************/
	public BFS(int start, int finish, ArrayList<Integer>[] adjancencyList, int noOfEdges, int[] brokenRoute) {
		this.start = start;
		this.finish = finish;
		this.list = adjancencyList;
		this.noOfEdges = noOfEdges;
		this.brokenRoute = brokenRoute;
	}

/********************************************************************************/
	public static ArrayList lengths() {
		Queue<Integer> queue = new LinkedList<Integer>();
		ArrayList directions = new ArrayList();
		int distance[] = new int[list.length];
		int[] vis = new int[list.length];

		int[] prev = new int[noOfEdges];
		
		vis[start] = 1;
		distance[start] = 0;
		queue.add(start);
		int current = start;
		while (!queue.isEmpty()) {
			current = queue.poll();
			if (current == finish) {
				break;
			} else {

				for (int i = 0; i < list[current].size(); i++) {
					int v = list[current].get(i);
					if (brokenRoute[current] != v) {
						if (vis[v] == 0) {
							vis[v] = 1;
							distance[v] = distance[current] + 1;
							queue.add(v);

							prev[v] = current;

						}

					}
				}
			}
		}

		
		
		System.out.println("");
		if (current != finish) {
			System.out.println("No safe route found, including the broken roads to the path...");
			prev = includeBrokenRoads();
			
		}

		for (int i = finish; i != start; i = prev[i]) {
			directions.add(i);
		}
		directions.add(start);
		Collections.reverse(directions);
		return directions;
	}
	
	/********************************************************************************/
	private static int[] includeBrokenRoads() {
		Queue<Integer> queue = new LinkedList<Integer>();
		ArrayList directions = new ArrayList();
		ArrayList<ArrayList<Integer>> allPath = new ArrayList<ArrayList<Integer>>();
		int distance[] = new int[list.length];
		int[] vis = new int[list.length];

		int[] prev = new int[noOfEdges];
		
		vis[start] = 1;
		distance[start] = 0;
		queue.add(start);
		int current = start;
		current = start;
		
		while (!queue.isEmpty()) {
			current = queue.poll();
			if (current == finish) {
				break;
			} else {
				
				for (int i = 0; i < list[current].size(); i++) {
					int v = list[current].get(i);
					
						if (vis[v] == 0) {
							vis[v] = 1;
							distance[v] = distance[current] + 1;
							queue.add(v);

							prev[v] = current;

						}

					
				}
			}
		}
		return prev;
	}


	

}
// }
