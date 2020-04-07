/**
	Generator class for an intermediate representation
*/
import java.util.ArrayList;
public class Intermediate
{
	private int ph_num; /*Used in assigning placeholder names*/
	private ArrayList<Placeholder> placeholders; // Store placeholders

	private SymbolTable st;

	public Intermediate(SymbolTable sym){
		st = sym;
		ph_num = 1;
		placeholders = new ArrayList<Placeholder>();
	}

	/*Flattens out tree and generates IR*/
	public Node run(Node root){
		io_traverse(root, 0);
		one_char_ph_flatten();
		two_char_ph_flatten();

		two_char_ph_flatten();
		dfsprint(root, 0);
		for(Placeholder p : placeholders){
			System.out.println(p.name + ": " + p.expression + ", " + p.height);
		}
		write_IR(root, 0, st.name);
		return root;
	}

	/*Temporary print until functionality is moved to node class from parser*/
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
			dfsprint(n, height + 1);
		}
	}

	/*In order traversal of a tree, begins flattening process*/
	private void io_traverse(Node root, int height){
		/*If expression, branch into expression handler*/
		if(root.getPayload().equals("Expression")){
			flatten(root, height);
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

			/*This part was added for handling function calls (or expression Node with expression children).
			After, a function call in the tree looks like: nameL1L2L3
			where name is the function name and each placeholder L is a param
			*/
			if(c.children != null){
				for(Node d: c.children){
					if(d.getPayload().equals("Expression")){
						flatten(d, height + 1);
						exp = exp.concat("L" + (ph_num - 1));
					}
				}
			}
		}
		/*Replace the expression with the generated placeholder*/
		Placeholder expression = new Placeholder("L" + ph_num, exp, height);
		placeholders.add(expression);
		root.setPayload(expression.name);
		root.children.clear();
		ph_num++;
	}

	/*Placeholders with mutilplication and division can hold more than one
	operation due to the tree structure.
	Here, they will be ironed into 3 addr holders.*/
	private void one_char_ph_flatten(){
		boolean modified = false;
		int op_count;
		ArrayList<Placeholder> newPlaceholders = new ArrayList<Placeholder>();
		for(Placeholder p : placeholders){
			op_count = getOpCount(p.expression);
			/*A count of operators tells us how many operations occur within a ph*/
			if(op_count > 1){
				modified = true;
				String exp;
				int ops = 0;
				char cur;
				/*Determine index for substring*/
				int j;
				for(j = 0; j < p.expression.length() && ops != 2; j++){
					cur = p.expression.charAt(j);
					switch(cur){
						/*! is not included because it only has one expression attached to it*/
						case '+':
						case '-':
						case '*':
						case '/':
						case '%':
						case '^':
						case '&':
						case '|':
						case '>':
						case '<':
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
			one_char_ph_flatten();
		}
	}

	/*Count operators in a string*/
	private int getOpCount(String s){
		int op_count = 0;
		for(int i = 0; i < s.length(); i++){
			switch(s.charAt(i)){
				case '+':
				case '-':
				case '*':
				case '/':
				case '%':
				case '^':
				case '&':
				case '|':
				case '>':
				case '<':
					op_count++;
				default:
					break;
			}
		}
		return op_count;
	}

	/*Flatten out two character operations*/
	private void two_char_ph_flatten(){
		boolean modified = false;
		int op_count = 0;
		int i;
		String cur;
		ArrayList<Placeholder> newPlaceholders = new ArrayList<Placeholder>();
		for(Placeholder p : placeholders){

			for(i = 0; i < (p.expression.length() - 1); i++){
				cur = p.expression.substring(i, i + 1);
				if(cur.equals("&&")){
					op_count++;
				} else if(cur.equals("||")){
					op_count++;
				} else if(cur.equals("==")){
					op_count++;
				} else if(cur.equals(">=")){
					op_count++;
				} else if(cur.equals("<=")){
					op_count++;
				} else if(cur.equals(">>")){
					op_count++;
				} else if(cur.equals("<<")){
					op_count++;
				} else if(cur.equals("*=")){
					op_count++;
				} else if(cur.equals("/=")){
					op_count++;
				} else if(cur.equals("+=")){
					op_count++;
				} else if(cur.equals("-=")){
					op_count++;
				}
			}
			/*A count of operators tells us how many operations occur within a ph*/
			if(op_count > 1){
				modified = true;
				String exp;
				int ops = 0;
				/*Determine index for substring*/
				int j;
				for(j = 0; j < (p.expression.length() - 1) && ops != 2; j++){
					cur = p.expression.substring(j, j + 1);
					if(cur.equals("&&")){
						ops++;
					} else if(cur.equals("||")){
						ops++;
					} else if(cur.equals("==")){
						ops++;
					} else if(cur.equals(">=")){
						ops++;
					} else if(cur.equals("<=")){
						ops++;
					} else if(cur.equals(">>")){
						ops++;
					} else if(cur.equals("<<")){
						ops++;
					} else if(cur.equals("*=")){
						ops++;
					} else if(cur.equals("/=")){
						ops++;
					} else if(cur.equals("+=")){
						ops++;
					} else if(cur.equals("-=")){
						ops++;
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
			two_char_ph_flatten();
		}
	}



	private void write_IR(Node node, int height, String table){
		//System.out.println("Working with table: " + table);
		String s = "";
		if(height == 0){ /*Top node is always Program*/
			/*Write nothing move to children*/
			for(Node c: node.children){
				write_IR(c, height + 1, c.getPayload());
			}

		} else if(height == 1){ /*Function declarations*/

			s = s.concat(node.getPayload() + "(");
			if(node.children.get(1).children.size() > 0){
				for(Node c : node.children.get(1).children){
					s = s.concat(c.getPayload() + ", ");
				}
				s = s.substring(0, s.length() - 2);
			}
			s = s.concat("){");
			System.out.println(s);
			write_IR(node.children.get(2), height + 1, table);
			System.out.println("}");
		} else if(height == 2) { /*Compound statement processing, there are two parts, local declarations and the statement list*/
			/*Local Declarations*/
			for(Node c: node.children.get(0).children){
				s = s.concat(c.getPayload() + ";");
				System.out.println(s);
				s = "";
			}
			/*Statement list*/
		}

	}
}
