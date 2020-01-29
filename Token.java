public class Token {

	private String value;
	private int type; //Enum of possible tokens??

	public Token(){
		this.value = null;
		this.type = -1;
	}

	public Token(String val, int typ){
		this.value = val;
		this.type = typ;
	}

	public String getValue(){
		return this.value;
	}

	public int getType(){
                return this.type;
        }

	public void setValue(String newVal){
                this.value = newVal;
        }

	public void setType(int newTyp){
                this.type = newTyp;
        }
}
