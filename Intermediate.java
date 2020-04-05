/**
	Generator class for an intermediate representation
*/
import java.util.ArrayList;
public class Intermediate
{
	private int ph_num; /*Used in assigning placeholder names*/
	private ArrayList<Placeholder> placeholders; // Store placeholders

	public Intermediate(){
		ph_num = 1;
		placeholders = new ArrayList<Placeholder>();
	}

	/*Flattens out tree*/
	public Node run(Node root){
		io_traverse(root, 0);
		ph_flatten();

		dfsprint(root, 0);

		for(Placeholder p : placeholders){
			System.out.println(p.name + ": " + p.expression + ", " + p.height);
		}
		return root;
	}

	private void dfsprint(Node node, int height){
		int i;
		for(i = 0; i < height - 1; i++) {
			System.out.print("\t");
		}
		if(height != 0){
			System.out.println("-------- " + node.getPayload());
		} else {
			System.out.println(node.getPayload());
		}
		for(Node n : node.children) {
			for(i = 0; i < height; i++) {
				System.out.print("\t");
			}
			System.out.println("|");
			// for(i = 0; i < height + 1; i++) {
			// 	System.out.print("\t");
			// }
			// System.out.println("|");

			dfsprint(n, height + 1);
		}
	}

	/*In order traversal of a tree*/
	private void io_traverse(Node root, int height){

		//System.out.println(root.getPayload());

		/*If expression, branch into expression handler*/
		if(root.getPayload().equals("Expression")){
			flatten(root, height);
			System.out.println("");
		} else {
			/*If not expression, keep traversing*/
			for(Node c : root.children){
				io_traverse(c, height + 1);
			}
		}
	}

	/*Expression handling function*/
	private void flatten(Node root, int height){

		//If a node is an expression, we will create a placeholder for it

		//Make expression string
		String exp = "";
		for(Node c : root.children){
			if(c.getPayload().equals("Expression")){
				flatten(c, height + 1);
			}
			exp = exp.concat(c.getPayload());
		}

		Placeholder expression = new Placeholder("L" + ph_num, exp, height);
		placeholders.add(expression);
		root.setPayload(expression.name);
		root.children.clear();
		ph_num++;
		//System.out.println(root.getPayload() + ", " + height);

	}

	/*Placeholders with mutilplication and division can hold more than one
	operation due to the tree structure.
	Here, they will be ironed into 3 addr holders.*/
	private void ph_flatten(){
		boolean modified = false;
		int op_count;
		char cur;
		ArrayList<Placeholder> newPlaceholders = new ArrayList<Placeholder>();
		for(Placeholder p : placeholders){
			op_count = 0;
			/*A count of operators tells us how many operations occur within a ph*/
			for(int i = 0; i < p.expression.length(); i++){
				cur = p.expression.charAt(i);
				switch(cur){
					case '+':
					case '-':
					case '*':
					case '/':
						op_count++;
					default:
						break;
				}
			}
			
			if(op_count > 1){
				modified = true;
				String exp;
				int ops = 0;
				/*Determine index for substring*/
				int j;
				for(j = 0; j < p.expression.length() && ops != 2; j++){
					cur = p.expression.charAt(j);
					switch(cur){
						case '+':
						case '-':
						case '*':
						case '/':
							ops++;
						default:
							break;
					}
				}
				/*Create new placeholder*/
				exp = p.expression.substring(0, j - 1);
				Placeholder sub_expression = new Placeholder("L" + ph_num, exp, p.height + 1);
				p.expression = sub_expression.name.concat(p.expression.substring(j - 1));
				newPlaceholders.add(sub_expression);
				ph_num++;
			}
		}
		if(modified){
			for(Placeholder p : newPlaceholders){
				placeholders.add(p);
			}
			ph_flatten();
		}

	}
}
