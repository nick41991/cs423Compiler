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
		if(reference != null){
			if(reference.inRegister){
				access.add(reference.register);
			} else {
				String alloced = registers.gpr_allocate();
				for(MemoryUnit m : units){
					if(m.register.equals(alloced)){
						m.register = "";
						m.inRegister = false;
						access.add("mov " + alloced + ", %ebp");
						break;
					}
				}
			}
		} else {
			// reference not defined!
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
