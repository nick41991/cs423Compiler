// Manages memory
import java.util.ArrayList;
public class MemoryManager{
	private StackManager stack;
	private RegisterManager registers;
	private ArrayList<MemoryUnit> units;

	public class MemoryUnit{
		String name; // reference
		String scope; // scope
		int offset;
		boolean inRegister;
		String register;

		public MemoryUnit(String n, String s, int o){
			name = n;
			scope = s;
			offset = o;
			inRegister = false;
			register = "";
		}
	}


	public MemoryManager(){
		stack = new StackManager();
		registers = new RegisterManager();
		units = new ArrayList<MemoryUnit>();
	}

	// Push new references to stack
	public void addReference(String name, String scope){
		units.add(new MemoryUnit(name, scope, stack.push(name)));
	}

	/*The last string returned in the array list will always be the register for the reference
	but there may be instructions in the prior entries to move values around*/
	public ArrayList<String> accessReference(String name, String scope){
		ArrayList<String> access = new ArrayList<String>();
		MemoryUnit reference = null;
		//Find reference
		for(MemoryUnit m : units){
			if(m.name.equals(name) && m.scope.equals(scope)){
				reference = m;
				break;
			}
		}
		if(reference == null){

			//Not found allocate new reference
			reference = new MemoryUnit(name, scope, stack.push(name));
			units.add(reference);
		}

		if(reference.inRegister){
			access.add(reference.register);
		} else {
			String alloced = registers.gpr_allocate();
			/*See if any other unit was using the allocated register*/
			for(MemoryUnit m : units){
				if(m.register.equals(alloced)){
					/*Move unit from register back to stack*/
					m.register = "";
					m.inRegister = false;
					access.add("movl " + alloced + ", [%ebp - " + (4 * m.offset) + "]"); //Need proper addressing scheme
					break; /*Should only be one unit in a register, so save cycles*/
				}
			}
			/*Move value into register*/
			reference.register = alloced;
			reference.inRegister = true;
			access.add("movl [%ebp - " + (4 * reference.offset) + "], " + alloced);
			access.add(reference.register);
		}

		return access;
	}

	public String grabRegister(String name){
		for(MemoryUnit m : units){
			if(m.name.equals(name)){
				return m.register;
			}
		}
		return registers.gpr_allocate();
	}

}
