import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BSearch {
	
	static ArrayList<String> c = new ArrayList<String>(); 
	static ArrayList<node> city = new ArrayList<node>();
	
	static int[][] roads;
	static String source;
	static String dest;
	
	public static void main(String [] args){
		String s = "";
		node temp = null;
		readTxt();
		
		Q q1 = new Q();
		Q q2 = new Q();
		
		
		q1.enqueue(city.get(c.indexOf(source)));
		q2.enqueue(city.get(c.indexOf(dest)));
		q1.peek().found = "f";
		q2.peek().found = "b";
		
		while(!q1.isEmpty() || !q2.isEmpty()){
			q1 = bfs(q1, "f");
			if (q1.peek().found.equals("b")){
				s = "Forward";
				temp = q1.peek();
				break;
			}
			q2 = bfs(q2, "b");
			if (q2.peek().found.equals("f")){
				s = "Backward";
				temp = q2.peek();
				break;
			}
		}
		print(temp, s);
	}

	private static void print(node n, String str) {
		int fcost = 0;
		int bcost = 0;
		String path = "->" + n.toString() + "->";
		node temp = n;
		
		//print forward path
		while(!temp.fp.equals(source)){
			temp = city.get(c.indexOf(temp.fp));
			path = "->" + temp+path;
			fcost++;
		}
		
		temp = n;
		//print backword path
		while(!temp.bp.equals(dest)){
			temp = city.get(c.indexOf(temp.bp));
			path = path + temp +"->";
			bcost++;
		}
		
		int cost=0;
		if(fcost>bcost){
			cost=fcost+1;
		}
		else{
			cost=bcost+1;
		}
		System.out.println("Route: " + source+path+dest);
		System.out.println("length: " + (fcost+bcost+2));
		System.out.println("Direction: " + str + ", " + "City: " + n.name + ", " + "Roads: "  + cost);
		
	}

	private static Q bfs(Q q, String stamp) {
		node temp = q.dequeue();
		int n = city.indexOf(temp);
		
		for (int i=0; i<roads[0].length; i++ ){
			if(roads[n][i]>0){
				if(city.get(i).found == null){
					q.enqueue(city.get(i));
					city.get(i).found = stamp;
				}
				else if (!city.get(i).found.equals(stamp)){
					q.enqueue(city.get(i));
				}
				
				if (stamp.equals("f") && city.get(i).fp == null){
					city.get(i).fp = temp.name;
				}else if(stamp.equals("b") && city.get(i).bp == null){
					city.get(i).bp = temp.name;
				}
			}
		}
		return q;
	}

	private static void readTxt() {
		Scanner sc;
		try{
			sc = new Scanner(new File("input.txt"));
			//building matrix
			sc.nextInt();
			int n = sc.nextInt();
			//System.out.println(n);
			roads = new int[n][n];
			
			//set source and destination
			sc.nextLine();
			source = sc.nextLine();
			dest = sc.nextLine();
			System.out.println(source+"-->"+dest);
			
			//set all connection
			String str[];
			while(sc.hasNextLine()){
				String s = sc.nextLine();
				str = s.split(","); 
				
				if (!c.contains(str[0])){
					c.add(str[0]);
				}
				
				if (!c.contains(str[1])){
					c.add(str[1]);
				}
				
				roads[c.indexOf(str[0])][c.indexOf(str[1])]++;
				roads[c.indexOf(str[1])][c.indexOf(str[0])]++;
			}
			
			for(String s:c){
				System.out.println(s);
				System.out.println(c.indexOf(s));
				city.add(new node(s));
			}
			
			
			
		}catch(FileNotFoundException e){
			System.out.println("File Not Found");
		}
		
	}
	

}
