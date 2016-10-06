public class node {
	
	String name;
	String fp;
	String bp;
	String found;
	
	
	public node(String name){
		this.name = name;
	}
	
	public String print(){
		return "["+ fp + "," + found + "," + bp + "]";
	}
	
public String toString(){
	return name;
}

}
