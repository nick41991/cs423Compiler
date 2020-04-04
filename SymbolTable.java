import java.util.*;

/**
 * Class for handling anything in relation to the Symbol Table
 */
public class SymbolTable {
    //characteristics of a symbol
    public static class Symbol {
        public String scope;
        public String type;
        public String symbol;

        public Symbol(String s, String t, String sym) {
            scope = s;
            type = t;
            symbol = sym;
        }
    };

    //public SymbolTable parent; //keeps track of parent table
    public ArrayList<SymbolTable> children; //keeps track of child tables
    public ArrayList<Symbol> symbols; //keeps track of symbols in table
    public String name;

    public SymbolTable() {
        children = new ArrayList<SymbolTable>();
        symbols = new ArrayList<Symbol>();
        name = "";
    }

    public void addSymbol(Symbol s) {
        symbols.add(s);
    }

    public void addChild(SymbolTable child) {
        children.add(child);
    }

    /* Creates all symbol tables for functions */
    public static SymbolTable createSymbolTable(Node node) {
        SymbolTable symRoot = new SymbolTable();
        Symbol sym;
        Node tmp;
        Node tmp2;

        for(Node n : node.children) {
            //This is an indication that the current node is a function
            if(n.children.size() > 2) {
                SymbolTable st = new SymbolTable();
                st.name = n.payload;
                //goes through params
                st = getSymbols(st, n.children.get(1), n.payload);
                //goes through statements
                st = getSymbols(st, n.children.get(2), n.payload);
                symRoot.addChild(st);
            }
        }

        return symRoot;
    }

    /* Checks for any declarations
     * Recursively traverses tree until it finds an int 
     */
    public static SymbolTable getSymbols(SymbolTable st, Node node, String scope) {
        Symbol sym;
        for(Node n : node.children) {
            st = getSymbols(st, n, scope);
            if(n.payload.equals("int")) {
                sym = new Symbol(scope, n.payload, n.parent.payload);
                st.addSymbol(sym);
            }
        }
        return st;
    }

    /* Prints the symbol table */
    public static void printSymbolTable(SymbolTable root) {
        int i;

        for(SymbolTable t : root.children) {
            System.out.print("\n\n");
            for(i = 0; i < t.name.length() + 4; i++) {
                System.out.print("-");
            }
            System.out.println("\n| " + t.name + " |");
            System.out.println("------------------------");
            System.out.println("Symbol\tType\tScope");
            System.out.println("------------------------");
            for(Symbol s : t.symbols) {
                System.out.println(s.symbol + "\t" + s.type + "\t" + s.scope);
            }
            if(t.symbols.isEmpty()) {
                System.out.println("Empty symbol table");
            }
            System.out.println("------------------------\n");
        }
    }



}
