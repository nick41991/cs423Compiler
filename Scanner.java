import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Scanner {
	public static void main(String[] args){
		File inFile = null;
		if (0 < args.length) {
   			inFile = new File(args[0]);
		} else {
   			System.err.println("Invalid arguments count:" + args.length);
   			System.exit(1);
		}
		BufferedReader br = null;
		Token t = new Token("Test", 1);
		ArrayList<Token> tokens = new ArrayList<Token>();
		try {
			String s;
			br = new BufferedReader(new FileReader(inFile));
			
			while((s = br.readLine()) != null) {
				System.out.println(s);
				//tokenize s
			}
		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(t.getValue());
	}
}
