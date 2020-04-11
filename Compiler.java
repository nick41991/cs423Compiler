import java.util.*;

public class Compiler {
    public static void main(String[] args) {
        Parser c = new Parser();

        boolean tokenBool = false;
	boolean parseTreeBool = false;
        boolean symbolTableBool = false;
	boolean irBool = false;
	boolean fileSet = false;
	String fileName = null;
	//test

        for(String s : args){
		if(s.equals("-t")){
			tokenBool = true;
                	System.out.println("-t");

		} else if (s.equals("-pt")){
			parseTreeBool = true;

		} else if (s.equals("-s")) {
                	symbolTableBool = true;

            	} else if (s.equals("-ir")) {
                	irBool = true;

            	} else if (!fileSet){
			fileName = s;
			fileSet = true;

		} else {
			System.out.println("Error: More than one file passed as argument.");

		}
	}
        Node root = c.run(tokenBool, fileSet, fileName);
        if (parseTreeBool) {
        	root.printParseTree(root,0);
	}

        SymbolTable symRoot;
        symRoot = SymbolTable.createSymbolTable(root);

	Intermediate n = new Intermediate(symRoot);
	IntRep ir = n.run(root);

	if(symbolTableBool) {
            SymbolTable.printSymbolTable(symRoot);
        }

	if(irBool){
		ir.write();
	}
    }
}
