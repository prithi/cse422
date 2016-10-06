
public class Node {

	public int min;
	public int max;
	public boolean visited = false;
	public Node(int min, int max) {
		this.min = min;
		this.max = max;
	}
	public int getNodeMin() {
		return min;
	}
	public void setNodeMin(int min) {
		this.min = min;
	}
	public int getNodeMax() {
		return max;
	}
	public void setNodeMax(int max) {
		this.max = max;
	}

}
