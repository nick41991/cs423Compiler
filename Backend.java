// This class converts an IR into ASM
// This will be primarily done by reading a line of IR and generating appropriate ASM for that line
import java.util.ArrayList;
import java.util.regex.*;


public class Backend {
	public ArrayList<String> output;
	public IntRep ir;
	private SymbolTable st; // Store main symbol table
	private SymbolTable context; // Working table

	private int jumpLabel;


	public Backend(IntRep irep, SymbolTable sym){
		output = new ArrayList<String>();
		ir = irep;
		st = sym;
		jumpLabel = 0;
	}

	public void run(){
		state_switch(0, 0);
	}

	/**
	Cases to handle:		id ir	write asm`
		Function declaration	+	-
		LabeledStatement	+	-
		SelectionStatement	+	-
		IterationStatement	+	-
		JumpStatement     	+	-
		returns			+	-
		ExpressionStatement	-	- //Fallthrough case
	*/

	//Main control function, controls state of backend
	public int state_switch(int i, int indent){
		String s;
		for(; i < ir.rep.size(); i++){
			s = ir.rep.get(i);
			//Function Declaration
			if(Pattern.matches("[a-z][a-zA-Z_0-9]*[(][)][{]", s)){
				functionHeader(s);
			} else if (Pattern.matches("[A-Z][a-zA-Z_0-9]*[:]", s)){
				label(s);
			} else if (Pattern.matches("jmp [a-zA-Z][a-zA-Z_0-9]*", s)){
				jump(s);
			} else if (Pattern.matches("while [a-zA-Z][a-zA-Z_0-9]* [{]", s)){
				iterator(s);
			} else if (Pattern.matches("if [a-zA-Z][a-zA-Z_0-9]* [{]", s)){
				selectionIf(s);
			} else if (Pattern.matches("[}] else if [a-zA-Z][a-zA-Z_0-9]* [{]", s)){
				popLabel();
				selectionIf(s);
			} else if (Pattern.matches("[}] else [{]", s)){
				popLabel();

			} else if (Pattern.matches("return [a-zA-Z][a-zA-Z_0-9]*", s)){
				returns(s);

			} else if (Pattern.matches("[}]", s)){
				//return i;
			} else {
				//Assume expression
			}

			//TestPrint
			System.out.println(s + " " + Pattern.matches("[}] else if [a-zA-Z][a-zA-Z_0-9]* [{]", s));
		}
	}

	private void functionHeader(String s){
		// write function head. Allocate space for variables on stack via symbol table.
		// call state_switch() to write function until "}" forces return
	}

	private void label(String s){
		//A user defined label in the code
	}

	private void jump(String s){
		//Unconditional YEET to a label
	}

	private void iterator(String s){
		//For s == "while cond {"

		/* code to be written
			test cond
			jump to x if false		//Push x onto label stack
			label y:			//y needs to be stored localy though until we return from state_switch()
				looped code (state_switch() to write)
				Condition recalculation is included in looped code via IR
 			test cond
			jump to y if true		//write jump using stored label y
			label x:			//pop x from label stack
				continuation of code outside this structure
		*/

	}

	private void selectionIf(String s){ //if else
		//FOR: s == "if cond {"
		//Should also work for s = "} else if cond {"

		// For asm: test ph
		// jump to label x if test fails //label x is the next test for chained statements
		// code for if test doesn't fail, written by call to state_switch()
		// at the bottom of this code jump (unconditionally) to label y, the end of the if-else chain
		// place label x below the unconditional jump
		// x and y may point to the same instruction

		/*
			test cond
			jump if false to label x		push z to label stack then push x
				code for if true
				jump (unconditionally) to z
			label x:



			label z:
				continuation of code outside this structure
		*/

	}

	private void selectionElse(String s){
		//FOR: s == "else {"

		//write code via state_switch() until "}" is encountered
		//Place label y for bottom of chained statement here.
	}

	private void returns(String s){
		//FOR s == "return exp"

		/*
			Want to check register array and if need be stack for exp Location
			then load exp into %rax
			ret
		*/
	}

	private void expression(String s){
		//Expressions including function calls.
	}

}
