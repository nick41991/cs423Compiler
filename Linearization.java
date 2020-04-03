
public static class Linearization
{
	public Node root;
	public SymbolTable st;

	public Linearization(Node root){
		this.root = root;
		st = new SymbolTable();
	}

	public void run(){
		generateSymbolTable();
	}
}
