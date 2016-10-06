import java.util.ArrayList;

public class Q {

	ArrayList<node> queue = new ArrayList<node>();

	public boolean enqueue(node n) {
		if (queue.contains(n)) {
			return false;
		}

		queue.add(n);
		return true;
	}

	public node dequeue() {
		if (!queue.isEmpty()) {
			node temp = queue.get(0);
			queue.remove(0);
			return temp;
		}
		return null;
	}

	public node peek() {
		if (!queue.isEmpty()) {
			return queue.get(0);
		}
		return null;
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}

	public int size() {
		return queue.size();
	}
	
	
	public void print(){
		String str = "";
		for (node temp:queue){
			str = str + " " + temp;
		}
		System.out.println(str);
	}


}
