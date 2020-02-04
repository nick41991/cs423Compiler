import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Main {
	
	public static void main(String[] args) throws Exception  {
		URL path = Main.class.getResource("File.txt");
		File file = new File(path.getFile());
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line; 
		while ((line = br.readLine()) != null) {
			System.out.println(line); 
		 } 
		
	}

}
