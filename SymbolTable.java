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

    public static SymbolTable createSymbolTable(Node node) {
        SymbolTable symRoot = new SymbolTable();
        Symbol sym;
        Node tmp;
        Node tmp2;

        for(Node n : node.children) {
            //This is an indication that the current node is a function
            if(n.children.size() > 2) {
                SymbolTable st = new SymbolTable();
                tmp = n.children.get(2);
                //Local declarations of function
                tmp = tmp.children.get(0);
                for(Node m : tmp.children) {
                    sym = new Symbol(n.payload, m.children.get(0).payload, m.payload);
                    st.addSymbol(sym);
                }
                symRoot.addChild(st);
            }
        }

        return symRoot;
    }

    public static void printSymbolTable(SymbolTable root) {

        for(SymbolTable t : root.children) {
            System.out.println("------------------------");
            System.out.println("Symbol\tType\tScope");
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
