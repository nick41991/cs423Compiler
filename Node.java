import java.util.ArrayList;

/** Creates a node for the Abstract Syntax Tree which contains the value, parent, and children.
 * 
 */
public class Node{
	public Node parent;
	public ArrayList<Node> children;
	public String payload;					//value of node

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

	//Adds a child node to the list of children for this node.
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
