import java.util.ArrayList;

public class Node{
	public Node parent;
	public ArrayList<Node> children;
	public String payload;

	public Node(){
		parent = null;
		children = new ArrayList<Node>();
		payload = "";
	}

	public Node(Node p, String info){
		parent = p;
		children = new ArrayList<Node>();
		payload = info;
	}

	public void addChild(Node child){
		children.add(child);
	}

	public String getPayload(){
		return payload;
	}

	public void setPayload(String newPayload){
		payload = newPayload;
	}
}
