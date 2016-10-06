import java.util.ArrayList;
import java.util.Stack;

public class Graph {

	public Node rootNode;

	public ArrayList nodes = new ArrayList();
	public int[][] adjMatrix;
	int size;

	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	public void connectNode(Node start, Node end) {
		if (adjMatrix == null) {
			size = nodes.size();
			adjMatrix = new int[size][size];
		}

		int startIndex = nodes.indexOf(start);
		int endIndex = nodes.indexOf(end);
		adjMatrix[startIndex][endIndex] = 1;
		adjMatrix[endIndex][startIndex] = 1;
	}

	public Node getUnvisitedChildNode(Node n) {

		int index = nodes.indexOf(n);
		int j = 0;

		while (j < size) {
			if (adjMatrix[index][j] > 1 && ((Node) nodes.get(j)).visited == false) {
				return ((Node) nodes.get(j));
			}
			j++;
		}
		return null;
	}

	public int temp = 0;

	public void dfs(Node nHome) {
		// DFS uses stack data structure
		Stack s = new Stack();
		s.push(this.rootNode);
		rootNode.visited = true;
		// printNode(rootNode);

		while (!s.isEmpty()) {
			Node n = (Node) s.peek();
			Node child = getUnvisitedChildNode(n);

			if (child != null) {
				child.visited = true;
				// printNode(Child);
				temp++;

				if (child == nHome) {
					s.isEmpty();
					System.out.println("Path Cost: " + temp);
					temp = 0;
					break;
				}
				s.push(child);
			} else {
				s.pop(); /// check
			}
		}
		// clear visited property of nodes
		clearNodes();
	}

	// clear visited property of nodes
	private void clearNodes() {
		for (int i = 0; i < size; i++) {
			((Node) nodes.get(i)).visited = false;

		}
	}
	
	public void printNode(Node n){
		System.out.println("label: " + n.toString());
	}
}
