public class Token {
	private String value;
	private String type; //Enum of possible tokens??

	public Token(){
		this.value = null;
		this.type = null;
	}

	public Token(String val, String typ){
		this.value = val;
		this.type = type;
	}

	public String getValue(){
		return this.value;
	}

	public String getType(){
                return this.type;
        }

	public void setValue(String newVal){
                this.value = newVal;
        }

	public void setType(String newTyp){
                this.type = newTyp;
        }
}
