import java.io.File;

public class Main {
	
	
	public static void print_usage() {
		System.out.println("USAGE: java Main [FLAGS] [PATH_OF_FILE]");
		System.out.println("FLAGS");
		System.out.println("-t : Display tokens");
		System.out.println("-pt: Display sintax tree");
	}

	public static void main(String[] args) {
		
		File file = new File(args[1]);
		
		if (args.length < 2) {
			print_usage();
		}
		
		
		if (args[0].equals("-t")) {
			//call tokenizer function
		}
		
		else if (args[0].equals("-pt")) {
			//call parser
			//CParser parser = new CParser (args[0]);
		}
		else 
			print_usage();
		
		
	 
	}

}
