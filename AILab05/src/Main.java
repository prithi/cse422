import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		int min = 0, max = 0, depth = 0, branch = 0;

		ArrayList<Integer> nodes = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		try {
			String line = br.readLine();
			depth = Integer.parseInt(line);
			System.out.println("Depth: " + (2 * depth));

			line = br.readLine();
			branch = Integer.parseInt(line);
			System.out.println("Branch: " + line);

			System.out.println("Terminal states: " + Math.pow(branch, (depth*2)));

			line = br.readLine();

			StringTokenizer st = new StringTokenizer(line);

			min = Integer.parseInt(st.nextToken());
			max = Integer.parseInt(st.nextToken());

			Random rand = new Random();
			// change the value to check with the docs
			
//			nodes.add(3);
//			nodes.add(12);
//			nodes.add(8);
//			nodes.add(3);
//			nodes.add(4);
//			nodes.add(6);
//			nodes.add(14);
//			nodes.add(5);
//			nodes.add(2);
			
			for (int c = 0; c < branch * branch; c++) {
				int n = rand.nextInt(max) + min;
				nodes.add(n);
			}
			
		} finally {
			br.close();
		}

		Graph g = new Graph();

		Node rootNode = new Node(0, 21);

		ArrayList<Node> edges = new ArrayList<>();

		Node nA = new Node(0, 21);
		Node nB = new Node(0, 21);
		Node nC = new Node(0, 21);

		edges.add(nA);
		edges.add(nB);
		edges.add(nC);

		ArrayList<Node> child = new ArrayList<>();

		Node a = new Node(0, nodes.get(0));
		Node b = new Node(0, nodes.get(1));
		Node c = new Node(0, nodes.get(2));
		Node d = new Node(0, nodes.get(3));
		Node e = new Node(0, nodes.get(4));
		Node f = new Node(0, nodes.get(5));
		Node i = new Node(0, nodes.get(6));
		Node j = new Node(0, nodes.get(7));
		Node k = new Node(0, nodes.get(8));

		child.add(a);
		child.add(b);
		child.add(c);
		child.add(d);
		child.add(e);
		child.add(f);
		child.add(i);
		child.add(k);
		child.add(k);

		System.out.print("Set: {");
		for (int count = 0; count < child.size() - 1; count++) {
			System.out.print((child.get(count)).getNodeMax() + ", ");
		}
		System.out.print((child.get(child.size() - 1)).getNodeMax());
		System.out.println("}");
		System.out.println();

		ArrayList<Node> maxNode = new ArrayList<>();

		int counter = 0;

		while (!edges.isEmpty()) {
			counter++;
			Node temp = child.get(0);
			int minimum = temp.getNodeMax();
			for (int count = 1; count < branch; count++) {
				counter++;
				Node thisNode = child.get(count);
				if (thisNode.getNodeMax() < minimum) {
					minimum = thisNode.getNodeMax();
				}
			}

			for (int t = 0; t < branch; t++) {
				child.remove(0);
			}
			Node first = edges.get(0);
			first.setNodeMax(minimum);
			// System.out.println(edges.get(0).getNodeMax());
			maxNode.add(edges.get(0));
			edges.remove(0);

		}

		int maximum = 0;
		while (!maxNode.isEmpty()) {
			Node thisNode = maxNode.get(0);
			maximum = thisNode.getNodeMax();
			for (int count = 1; count < branch; count++) {
				Node q = maxNode.get(count);
				if (q.getNodeMax() > maximum) {
					maximum = q.getNodeMax();
				}
			}
			while (!maxNode.isEmpty()) {
				maxNode.remove(0);
			}
		}

		System.out.println("Max amount collected by Riyad: " + maximum);
		System.out.println("Comparisons before alpha-beta pruning: " + counter);

		edges.isEmpty();
		nA.setNodeMax(21);
		nB.setNodeMax(21);
		nC.setNodeMax(21);
		edges.add(nA);
		edges.add(nB);
		edges.add(nC);

		child.isEmpty();
		child.add(a);
		child.add(b);
		child.add(c);
		child.add(d);
		child.add(e);
		child.add(f);
		child.add(i);
		child.add(j);
		child.add(k);

		System.out.println(maxNode.isEmpty());

		counter = 0;
		boolean check = false;

		while (!edges.isEmpty()) {
			counter++;
			Node temp = child.get(0);
			int minimum = temp.getNodeMax();//beta
			if (!maxNode.isEmpty()) {
				for (int count = 0; count < maxNode.size(); count++) {
					int value = (maxNode.get(count).getNodeMax()); // value =
																	// alpha
					if (value > minimum) { // alpha > beta
						check = true;
						counter++;
						break;
					}
				}

			} else {
				for (int count = 1; count < branch; count++) {
					counter++;
					Node thisNode = child.get(count);
					if (thisNode.getNodeMax() < minimum) {
						minimum = thisNode.getNodeMax();//beta
					}
				}
				check = false;
			}

			if (check = true) {
				for (int t = 0; t < branch; t++) {
					child.remove(0);
				}
				check = false;
				Node first = edges.get(0);
				first.setNodeMax(minimum);
				maxNode.add(edges.get(0));
				edges.remove(0);
			}
		}

		maximum = 0;
		while (!maxNode.isEmpty()) {
			Node thisNode = maxNode.get(0);
			maximum = thisNode.getNodeMax();
			for (int count = 1; count < branch; count++) {
				Node q = maxNode.get(count);
				if (q.getNodeMax() > maximum) {
					maximum = q.getNodeMax();

				}
			}
			while (!maxNode.isEmpty()) {
				maxNode.remove(0);
			}
		}
		System.out.println("Comparison after alpha-beta pruning: " + counter);
	}

}
