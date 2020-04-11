import java.util.*;

public class Compiler {
    public static void main(String[] args) {
        Parser c = new Parser();

    	boolean tokenBool = false;
		boolean parseTreeBool = false;
    	boolean symbolTableBool = false;
		boolean irBool = false;
		boolean setFileName = false;
		boolean setIRName = false;
		boolean IRWriteBool = false;
		String fileName = null;
		String IRFileName = null;
	
	
		for(int i = 0; i < args.length; i++){
        
        	switch(args[i]){
				case "-t":
					tokenBool = true;
					break;
				
				case "-pt":
					parseTreeBool = true;
					break;
				
				case "-s":
					symbolTableBool = true;
					break;
				
				case "-ir":
					irBool = true;
					break;
				
				case "-f":
					IRWriteBool = true;
					setIRName = true;
					break;
					
				default:
					if (setIRName){
					
						IRFileName = args[i];
						setIRName = false;
					}
					else {
						fileName = args[i];
						setFileName = true;	
					}
					break;
        	
        	}
        }

        /**for(String s : args){
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
	}**/
        Node root = c.run(tokenBool, setFileName, fileName);
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
		
		if (IRWriteBool){
			ir.toFile(IRFileName);
		}
    }
}
