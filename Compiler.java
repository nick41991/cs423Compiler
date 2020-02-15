/* Generated By:JavaCC: Do not edit this line. Compiler.java */
import java.util.*;

public class Compiler implements CompilerConstants {
        public static ArrayList<SyntaxToken> tokenList = new ArrayList<SyntaxToken>();

        private static void printSuccess(){
                System.out.println("Input Parsed Successfully!");
        }

        private static void printTokens(){
                for(SyntaxToken tok : tokenList){
                        System.out.println(tok.toString());
                }
        }

        private static void printParseTree(){
                System.out.println("Not Yet Implemented");
        }

    // Run the parser
        public static void main ( String args [ ] ) {
                Compiler parser;
                boolean tokenBool = false;
                boolean parseTreeBool = false;
                boolean fileSet = false;
                String fileName = null;


                for(String s : args){
                        if(s.equals("-t")){
                                tokenBool = true;
                        } else if (s.equals("-pt")){
                                parseTreeBool = true;
                        } else if (!fileSet){
                                fileName = s;
                                fileSet = true;
                        } else {
                                System.out.println("Error: More than one file passed as argument.");
                                return;
                        }
                }

                //Mostly for easy debugging, may not be in final version
                if(!fileSet){
                        System.out.println("C Parser:  Reading from standard input . . .");
                        parser = new Compiler(System.in);
                }

                else if(fileSet){
                        System.out.println("C Parser:  Reading from file " + fileName + " . . ." );
                        try {
                                parser = new Compiler(new java.io.FileInputStream(fileName));
                        }
                        catch(java.io.FileNotFoundException e){
                                System.out.println("C Parser:  File " + args[0] + " not found.");
                                return;
                        }
                } else {
                        System.out.println("Compiler:  Usage is one of:");
                        System.out.println("         java Compiler [-t] [-pt] [fileName]");
                        return;
                }
                try {
                        parser.Program();
                        if(tokenBool){
                                printTokens();
                        }
                        if(parseTreeBool){
                                printParseTree();
                        }
                        System.out.println("Compiler:  C program parsed successfully.");
                }
                catch(ParseException e){
                        System.out.println("Compiler:  Encountered errors during parse.");
                        e.printStackTrace();
                }
        }

  static final public void Program() throws ParseException {
    label_1:
    while (true) {
      DeclarationList();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
    }
         printSuccess();
  }

  static final public void DeclarationList() throws ParseException {
    label_2:
    while (true) {
      Declaration();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
    }
  }

  static final public void Declaration() throws ParseException {
    if (jj_2_1(2147483647)) {
      VarDeclaration();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
        FuncDeclaration();
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void VarDeclaration() throws ParseException {
    TypeSpecifier();
    jj_consume_token(IDENTIFIER);
    jj_consume_token(28);
  }

  static final public void TypeSpecifier() throws ParseException {
                        Token i; Token v;
    i = jj_consume_token(INT);
    v = jj_consume_token(VOID);
                tokenList.add(new SyntaxToken("TYPE_SPECIFIER", i.image));
                tokenList.add(new SyntaxToken("TYPE_SPECIFIER", v.image));
  }

  static final public void FuncDeclaration() throws ParseException {
    TypeSpecifier();
    jj_consume_token(IDENTIFIER);
    jj_consume_token(29);
    Params();
    jj_consume_token(30);
    CompoundStatement();
  }

  static final public void Params() throws ParseException {
    ParamList();
  }

  static final public void ParamList() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      if (jj_2_2(2147483647)) {
        Param();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INT:
          Param();
          label_3:
          while (true) {
            jj_consume_token(31);
            Param();
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case 31:
              ;
              break;
            default:
              jj_la1[3] = jj_gen;
              break label_3;
            }
          }
          break;
        default:
          jj_la1[4] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
  }

  static final public void Param() throws ParseException {
    TypeSpecifier();
    jj_consume_token(IDENTIFIER);
  }

  static final public void CompoundStatement() throws ParseException {
    jj_consume_token(32);
    LocalDeclarations();
    StatementList();
    jj_consume_token(33);
  }

  static final public void LocalDeclarations() throws ParseException {
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_4;
      }
      VarDeclaration();
    }
  }

  static final public void StatementList() throws ParseException {
    label_5:
    while (true) {
      Statement();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER_LITERAL:
      case CONTINUE:
      case RETURN:
      case WHILE:
      case BREAK:
      case IF:
      case GOTO:
      case INT:
      case IDENTIFIER:
      case 28:
      case 29:
      case 32:
      case 35:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_5;
      }
    }
  }

  static final public void Statement() throws ParseException {
    if (jj_2_3(2)) {
      LabeledStatement();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER_LITERAL:
      case INT:
      case IDENTIFIER:
      case 28:
      case 29:
      case 35:
        ExpressionStatement();
        break;
      case 32:
        CompoundStatement();
        break;
      case IF:
        SelectionStatement();
        break;
      case WHILE:
        IterationStatement();
        break;
      case CONTINUE:
      case RETURN:
      case BREAK:
      case GOTO:
        JumpStatement();
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void LabeledStatement() throws ParseException {
    jj_consume_token(IDENTIFIER);
    jj_consume_token(34);
    Statement();
  }

  static final public void ExpressionStatement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
    case INT:
    case IDENTIFIER:
    case 29:
    case 35:
      Expression();
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
    jj_consume_token(28);
  }

  static final public void SelectionStatement() throws ParseException {
    jj_consume_token(IF);
    jj_consume_token(29);
    Expression();
    jj_consume_token(30);
    Statement();
    if (jj_2_4(2)) {
      jj_consume_token(ELSE);
      Statement();
    } else {
      ;
    }
  }

  static final public void IterationStatement() throws ParseException {
    jj_consume_token(WHILE);
    jj_consume_token(29);
    Expression();
    jj_consume_token(30);
    Statement();
  }

  static final public void JumpStatement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case GOTO:
      jj_consume_token(GOTO);
      jj_consume_token(IDENTIFIER);
      jj_consume_token(28);
      break;
    case CONTINUE:
      jj_consume_token(CONTINUE);
      jj_consume_token(28);
      break;
    case BREAK:
      jj_consume_token(BREAK);
      jj_consume_token(28);
      break;
    case RETURN:
      jj_consume_token(RETURN);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER_LITERAL:
      case INT:
      case IDENTIFIER:
      case 29:
      case 35:
        Expression();
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      jj_consume_token(28);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Expression() throws ParseException {
    if (jj_2_5(2147483647)) {
      Variable();
      AssignmentOperator();
      Expression();
    } else if (jj_2_6(2147483647)) {
      Negation();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER_LITERAL:
      case INT:
      case IDENTIFIER:
      case 29:
        LogicalORExpression();
        break;
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void Negation() throws ParseException {
    jj_consume_token(35);
    Expression();
  }

  static final public void Variable() throws ParseException {
    if (jj_2_7(2147483647)) {
      TypeSpecifier();
    } else {
      ;
    }
    jj_consume_token(IDENTIFIER);
  }

  static final public void AssignmentOperator() throws ParseException {
                             Token eq; Token em; Token ed; Token ea; Token es;
    eq = jj_consume_token(36);
    em = jj_consume_token(37);
    ed = jj_consume_token(38);
    ea = jj_consume_token(39);
    es = jj_consume_token(40);
                tokenList.add(new SyntaxToken("ASSIGNMENTOP", eq.image));
                tokenList.add(new SyntaxToken("ASSIGNMENTOP", em.image));
                tokenList.add(new SyntaxToken("ASSIGNMENTOP", ed.image));
                tokenList.add(new SyntaxToken("ASSIGNMENTOP", ea.image));
                tokenList.add(new SyntaxToken("ASSIGNMENTOP", es.image));
  }

//Boolean Operations have lower precedence than Mathematical Operations so they are higher in the parse tree

//Boolean Operations: Order of precedence (low to high): || -> && -> | -> ^ -> & -> == or != -> <, >, <=, or >=
  static final public void LogicalORExpression() throws ParseException {
                              Token lor;
    LogicalANDExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 41:
      lor = jj_consume_token(41);
                tokenList.add(new SyntaxToken("LOGICOP", lor.image));
      LogicalORExpression();
      break;
    default:
      jj_la1[13] = jj_gen;
      ;
    }
  }

  static final public void LogicalANDExpression() throws ParseException {
                               Token land;
    InclusiveORExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 42:
      land = jj_consume_token(42);
                tokenList.add(new SyntaxToken("LOGICOP", land.image));
      LogicalANDExpression();
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
  }

  static final public void InclusiveORExpression() throws ParseException {
                                Token o;
    ExclusiveORExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 43:
      o = jj_consume_token(43);
                tokenList.add(new SyntaxToken("BINARYOP", o.image));
      InclusiveORExpression();
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
  }

  static final public void ExclusiveORExpression() throws ParseException {
                                Token e;
    ANDExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 44:
      e = jj_consume_token(44);
                tokenList.add(new SyntaxToken("BINARYOP", e.image));
      ExclusiveORExpression();
      break;
    default:
      jj_la1[16] = jj_gen;
      ;
    }
  }

  static final public void ANDExpression() throws ParseException {
                        Token a;
    EqualityExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 45:
      a = jj_consume_token(45);
                tokenList.add(new SyntaxToken("BINARYOP", a.image));
      ANDExpression();
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
  }

  static final public void EqualityExpression() throws ParseException {
                             Token e; Token n;
    RelationalExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 46:
      e = jj_consume_token(46);
      n = jj_consume_token(47);
                tokenList.add(new SyntaxToken("RELOP", e.image));
                tokenList.add(new SyntaxToken("RELOP", n.image));
      EqualityExpression();
      break;
    default:
      jj_la1[18] = jj_gen;
      ;
    }
  }

  static final public void RelationalExpression() throws ParseException {
                               Token l; Token g; Token le; Token ge;
    ShiftExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 48:
      l = jj_consume_token(48);
      g = jj_consume_token(49);
      le = jj_consume_token(50);
      ge = jj_consume_token(51);
                tokenList.add(new SyntaxToken("RELOP", l.image));
                tokenList.add(new SyntaxToken("RELOP", g.image));
                tokenList.add(new SyntaxToken("RELOP", le.image));
                tokenList.add(new SyntaxToken("RELOP", ge.image));
      RelationalExpression();
      break;
    default:
      jj_la1[19] = jj_gen;
      ;
    }
  }

//End of Booleans

//Mathematical Operations: Order of precedence (low to high): >> or << -> + or - -> *, /, or %
  static final public void ShiftExpression() throws ParseException {
    AdditiveExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 52:
    case 53:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 52:
        jj_consume_token(52);
        break;
      case 53:
        jj_consume_token(53);
        break;
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      ShiftExpression();
      break;
    default:
      jj_la1[21] = jj_gen;
      ;
    }
  }

  static final public void AdditiveExpression() throws ParseException {
                             Token p; Token m;
    MultiplicativeExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 54:
      p = jj_consume_token(54);
      m = jj_consume_token(55);
                tokenList.add(new SyntaxToken("ADDOP", p.image));
                tokenList.add(new SyntaxToken("ADDOP", m.image));
      AdditiveExpression();
      break;
    default:
      jj_la1[22] = jj_gen;
      ;
    }
  }

  static final public void MultiplicativeExpression() throws ParseException {
                                   Token mult; Token div; Token mod;
    Factor();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 56:
      mult = jj_consume_token(56);
      div = jj_consume_token(57);
      mod = jj_consume_token(58);
                tokenList.add(new SyntaxToken("MULOP", mult.image));
                tokenList.add(new SyntaxToken("MULOP", div.image));
                tokenList.add(new SyntaxToken("MULOP", mod.image));
      MultiplicativeExpression();
      break;
    default:
      jj_la1[23] = jj_gen;
      ;
    }
  }

  static final public void Factor() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 29:
      jj_consume_token(29);
      LogicalORExpression();
      jj_consume_token(30);
      break;
    default:
      jj_la1[24] = jj_gen;
      if (jj_2_8(2147483647)) {
        Call();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INT:
        case IDENTIFIER:
          Variable();
          break;
        case INTEGER_LITERAL:
          Constant();
          break;
        default:
          jj_la1[25] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

  static final public void Call() throws ParseException {
               Token t; Token l; Token r;
    t = jj_consume_token(IDENTIFIER);
    l = jj_consume_token(29);
    Args();
    r = jj_consume_token(30);
                tokenList.add(new SyntaxToken("IDENTIFIER", t.image));
                tokenList.add(new SyntaxToken("LPAREN", l.image));
                tokenList.add(new SyntaxToken("RPAREN", r.image));
  }

  static final public void Args() throws ParseException {
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INTEGER_LITERAL:
      case INT:
      case IDENTIFIER:
      case 29:
      case 35:
        ;
        break;
      default:
        jj_la1[26] = jj_gen;
        break label_6;
      }
      Expression();
    }
  }

  static final public void Constant() throws ParseException {
                   Token i; Token l; Token s;
    i = jj_consume_token(INTEGER_LITERAL);
    l = jj_consume_token(CHARACTER_LITERAL);
    s = jj_consume_token(STRING_LITERAL);
                tokenList.add(new SyntaxToken("INTEGER_LITERAL", i.image));
                tokenList.add(new SyntaxToken("CHARACTER_LITERAL", l.image));
                tokenList.add(new SyntaxToken("STRING_LITERAL", s.image));
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  static private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  static private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  static private boolean jj_3R_58() {
    if (jj_scan_token(56)) return true;
    if (jj_scan_token(57)) return true;
    if (jj_scan_token(58)) return true;
    if (jj_3R_55()) return true;
    return false;
  }

  static private boolean jj_3R_54() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(52)) {
    jj_scanpos = xsp;
    if (jj_scan_token(53)) return true;
    }
    if (jj_3R_51()) return true;
    return false;
  }

  static private boolean jj_3R_38() {
    if (jj_3R_39()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_40()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_31() {
    if (jj_3R_38()) return true;
    return false;
  }

  static private boolean jj_3R_55() {
    if (jj_3R_57()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_58()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_scan_token(ELSE)) return true;
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3_7() {
    if (jj_3R_14()) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    if (jj_scan_token(36)) return true;
    if (jj_scan_token(37)) return true;
    if (jj_scan_token(38)) return true;
    if (jj_scan_token(39)) return true;
    if (jj_scan_token(40)) return true;
    return false;
  }

  static private boolean jj_3R_53() {
    if (jj_3R_55()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_56()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_52() {
    if (jj_scan_token(48)) return true;
    if (jj_scan_token(49)) return true;
    if (jj_scan_token(50)) return true;
    if (jj_scan_token(51)) return true;
    if (jj_3R_49()) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_scan_token(INT)) return true;
    if (jj_scan_token(VOID)) return true;
    return false;
  }

  static private boolean jj_3R_21() {
    if (jj_3R_14()) return true;
    return false;
  }

  static private boolean jj_3R_50() {
    if (jj_scan_token(46)) return true;
    if (jj_scan_token(47)) return true;
    if (jj_3R_47()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_21()) jj_scanpos = xsp;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  static private boolean jj_3_5() {
    if (jj_3R_11()) return true;
    if (jj_3R_12()) return true;
    return false;
  }

  static private boolean jj_3R_51() {
    if (jj_3R_53()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_54()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_7() {
    if (jj_3R_14()) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(28)) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_scan_token(35)) return true;
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3_6() {
    if (jj_3R_13()) return true;
    return false;
  }

  static private boolean jj_3R_22() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) {
    jj_scanpos = xsp;
    if (jj_3R_31()) return true;
    }
    }
    return false;
  }

  static private boolean jj_3R_29() {
    if (jj_3R_11()) return true;
    if (jj_3R_12()) return true;
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3R_48() {
    if (jj_scan_token(45)) return true;
    if (jj_3R_45()) return true;
    return false;
  }

  static private boolean jj_3R_37() {
    if (jj_scan_token(RETURN)) return true;
    return false;
  }

  static private boolean jj_3R_36() {
    if (jj_scan_token(BREAK)) return true;
    return false;
  }

  static private boolean jj_3R_35() {
    if (jj_scan_token(CONTINUE)) return true;
    return false;
  }

  static private boolean jj_3R_30() {
    if (jj_3R_13()) return true;
    return false;
  }

  static private boolean jj_3R_49() {
    if (jj_3R_51()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_52()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_34() {
    if (jj_scan_token(GOTO)) return true;
    return false;
  }

  static private boolean jj_3R_28() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_34()) {
    jj_scanpos = xsp;
    if (jj_3R_35()) {
    jj_scanpos = xsp;
    if (jj_3R_36()) {
    jj_scanpos = xsp;
    if (jj_3R_37()) return true;
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_27() {
    if (jj_scan_token(WHILE)) return true;
    return false;
  }

  static private boolean jj_3R_47() {
    if (jj_3R_49()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_50()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_46() {
    if (jj_scan_token(44)) return true;
    if (jj_3R_43()) return true;
    return false;
  }

  static private boolean jj_3R_26() {
    if (jj_scan_token(IF)) return true;
    return false;
  }

  static private boolean jj_3R_44() {
    if (jj_scan_token(43)) return true;
    if (jj_3R_41()) return true;
    return false;
  }

  static private boolean jj_3R_33() {
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3R_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_33()) jj_scanpos = xsp;
    if (jj_scan_token(28)) return true;
    return false;
  }

  static private boolean jj_3R_45() {
    if (jj_3R_47()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_48()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(34)) return true;
    return false;
  }

  static private boolean jj_3R_42() {
    if (jj_scan_token(42)) return true;
    if (jj_3R_39()) return true;
    return false;
  }

  static private boolean jj_3R_20() {
    if (jj_3R_28()) return true;
    return false;
  }

  static private boolean jj_3R_19() {
    if (jj_3R_27()) return true;
    return false;
  }

  static private boolean jj_3R_18() {
    if (jj_3R_26()) return true;
    return false;
  }

  static private boolean jj_3R_17() {
    if (jj_3R_25()) return true;
    return false;
  }

  static private boolean jj_3R_63() {
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    if (jj_scan_token(CHARACTER_LITERAL)) return true;
    if (jj_scan_token(STRING_LITERAL)) return true;
    return false;
  }

  static private boolean jj_3R_43() {
    if (jj_3R_45()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_46()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_16() {
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_3R_9()) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3R_16()) {
    jj_scanpos = xsp;
    if (jj_3R_17()) {
    jj_scanpos = xsp;
    if (jj_3R_18()) {
    jj_scanpos = xsp;
    if (jj_3R_19()) {
    jj_scanpos = xsp;
    if (jj_3R_20()) return true;
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_32() {
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3R_40() {
    if (jj_scan_token(41)) return true;
    if (jj_3R_38()) return true;
    return false;
  }

  static private boolean jj_3R_23() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_32()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_41() {
    if (jj_3R_43()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_44()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3_8() {
    if (jj_3R_15()) return true;
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(29)) return true;
    if (jj_3R_23()) return true;
    if (jj_scan_token(30)) return true;
    return false;
  }

  static private boolean jj_3R_56() {
    if (jj_scan_token(54)) return true;
    if (jj_scan_token(55)) return true;
    if (jj_3R_53()) return true;
    return false;
  }

  static private boolean jj_3R_25() {
    if (jj_scan_token(32)) return true;
    return false;
  }

  static private boolean jj_3R_62() {
    if (jj_3R_63()) return true;
    return false;
  }

  static private boolean jj_3R_39() {
    if (jj_3R_41()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_42()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_61() {
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3R_60() {
    if (jj_3R_15()) return true;
    return false;
  }

  static private boolean jj_3R_59() {
    if (jj_scan_token(29)) return true;
    if (jj_3R_38()) return true;
    if (jj_scan_token(30)) return true;
    return false;
  }

  static private boolean jj_3R_57() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_59()) {
    jj_scanpos = xsp;
    if (jj_3R_60()) {
    jj_scanpos = xsp;
    if (jj_3R_61()) {
    jj_scanpos = xsp;
    if (jj_3R_62()) return true;
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_8() {
    if (jj_3R_14()) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public CompilerTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[27];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x800000,0x800000,0x800000,0x80000000,0x800000,0x800000,0x800000,0x32cf9000,0x32cf9000,0x22801000,0x22801000,0x458000,0x22801000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x20000000,0x2801000,0x22801000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x9,0x9,0x8,0x8,0x0,0x0,0x200,0x400,0x800,0x1000,0x2000,0x4000,0x10000,0x300000,0x300000,0x400000,0x1000000,0x0,0x0,0x8,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[8];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Compiler(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Compiler(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CompilerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Compiler(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CompilerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Compiler(CompilerTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(CompilerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 27; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        exists = true;
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.add(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[59];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 27; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 59; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 8; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
