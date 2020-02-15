public class ParseTree {
	String type;
	String image;

	public ParseTree(){
		type = null;
		image = null;
	}

	public ParseTree (String t, String i){
		type = t;
		image = i;
	}

	public String toString(){
		return ("<" + image + ", " + type + ">");
	}

}
