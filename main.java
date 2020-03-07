import java.util.*;

public class main {
    public static void main(String[] args) {
        Compiler c = new Compiler();
        boolean tokenBool = false;
		boolean parseTreeBool = false;
		boolean fileSet = false;
		String fileName = null;

        for(String s : args){
			if(s.equals("-t")){
				tokenBool = true;
			} else if (s.equals("-pt")){
				parseTreeBool = true;
			} else if (!fileSet){
				fileName = s;
				fileSet = true;
			} else {
				System.out.println("Error: More than one file passed as argument.");
			}
		}
        Node root = c.run(tokenBool, parseTreeBool, fileSet, fileName);
    }
}
