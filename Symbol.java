import java.util.ArrayList;

public class Symbol{

	public String name;
	public ArrayList<String> scope;
	public String type;

	public Symbol(){
		this.name = null;
		this.scope = new ArrayList<String>();
	}

	public Symbol(String name, ArrayList<String> scope){
		this.name = name;
		this.scope = (ArrayList<String>)scope.clone();
	}


	public Symbol(String name, ArrayList<String> parentScope, String newScope){
		this.name = name;
		this.scope = (ArrayList<String>)parentScope.clone();
		this.scope.add(newScope);
	}


}
