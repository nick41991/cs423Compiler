/*Holds an Intermediate representation*/
import java.util.ArrayList;
import java.nio.file.*;

public class IntRep {

	public ArrayList<String> rep;

	public IntRep(){
		rep = new ArrayList<String>();
	}

	public IntRep(String fileName){
		rep = new ArrayList<String>();
		try {
			/*Reads file into ArrayList for reading in the file*/
			rep = (ArrayList<String>)Files.readAllLines(Paths.get(fileName));
		}
		catch(Exception e){
			System.out.println("Intermediate representation:  File " + fileName + " not found.");
			return;
		}
	}

	public void addLine(String line){
		rep.add(line);
	}

	/*Output to std out*/
	public void write(){
		for(String s: rep){
			System.out.println(s);
		}
	}

	/*Output to a given file*/
	public void write(String fileName){
		for(String s: rep){
			System.out.println(s);
		}
	}

}
