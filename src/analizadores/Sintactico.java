
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package analizadores;

import java.util.LinkedList;
import java_cup.runtime.*;
import arbol.Arbol;
import arbol.Instruccion;
import arbol.Operacion;
import arbol.Operacion.tipo_operacion;
import arbol.Print;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Sintactico extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public Sintactico() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Sintactico(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Sintactico(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\034\000\002\002\004\000\002\002\003\000\002\003" +
    "\004\000\002\003\003\000\002\004\007\000\002\004\006" +
    "\000\002\005\004\000\002\005\004\000\002\005\005\000" +
    "\002\005\005\000\002\005\005\000\002\005\005\000\002" +
    "\005\005\000\002\005\005\000\002\005\005\000\002\005" +
    "\005\000\002\005\005\000\002\005\005\000\002\005\005" +
    "\000\002\005\005\000\002\005\005\000\002\005\005\000" +
    "\002\005\003\000\002\005\003\000\002\005\003\000\002" +
    "\005\003\000\002\005\003\000\002\005\005" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\063\000\004\027\007\001\002\000\006\002\000\027" +
    "\007\001\002\000\006\002\ufffe\027\ufffe\001\002\000\004" +
    "\002\064\001\002\000\004\055\010\001\002\000\022\004" +
    "\011\010\015\011\020\055\013\064\014\101\021\107\016" +
    "\110\012\001\002\000\040\056\uffe9\063\uffe9\064\uffe9\065" +
    "\uffe9\066\uffe9\067\uffe9\070\uffe9\071\uffe9\072\uffe9\073\uffe9" +
    "\074\uffe9\075\uffe9\076\uffe9\077\uffe9\100\uffe9\001\002\000" +
    "\040\056\uffea\063\uffea\064\uffea\065\uffea\066\uffea\067\uffea" +
    "\070\uffea\071\uffea\072\uffea\073\uffea\074\uffea\075\uffea\076" +
    "\uffea\077\uffea\100\uffea\001\002\000\022\004\011\010\015" +
    "\011\020\055\013\064\014\101\021\107\016\110\012\001" +
    "\002\000\022\004\011\010\015\011\020\055\013\064\014" +
    "\101\021\107\016\110\012\001\002\000\040\056\uffe8\063" +
    "\uffe8\064\uffe8\065\uffe8\066\uffe8\067\uffe8\070\uffe8\071\uffe8" +
    "\072\uffe8\073\uffe8\074\uffe8\075\uffe8\076\uffe8\077\uffe8\100" +
    "\uffe8\001\002\000\040\056\uffeb\063\uffeb\064\uffeb\065\uffeb" +
    "\066\uffeb\067\uffeb\070\uffeb\071\uffeb\072\uffeb\073\uffeb\074" +
    "\uffeb\075\uffeb\076\uffeb\077\uffeb\100\uffeb\001\002\000\040" +
    "\056\057\063\033\064\024\065\030\066\036\067\025\070" +
    "\035\071\040\072\032\073\027\074\031\075\026\076\034" +
    "\077\023\100\037\001\002\000\040\056\uffe7\063\uffe7\064" +
    "\uffe7\065\uffe7\066\uffe7\067\uffe7\070\uffe7\071\uffe7\072\uffe7" +
    "\073\uffe7\074\uffe7\075\uffe7\076\uffe7\077\uffe7\100\uffe7\001" +
    "\002\000\022\004\011\010\015\011\020\055\013\064\014" +
    "\101\021\107\016\110\012\001\002\000\040\056\ufffa\063" +
    "\ufffa\064\ufffa\065\ufffa\066\ufffa\067\ufffa\070\ufffa\071\ufffa" +
    "\072\ufffa\073\ufffa\074\ufffa\075\ufffa\076\ufffa\077\ufffa\100" +
    "\ufffa\001\002\000\022\004\011\010\015\011\020\055\013" +
    "\064\014\101\021\107\016\110\012\001\002\000\022\004" +
    "\011\010\015\011\020\055\013\064\014\101\021\107\016" +
    "\110\012\001\002\000\022\004\011\010\015\011\020\055" +
    "\013\064\014\101\021\107\016\110\012\001\002\000\022" +
    "\004\011\010\015\011\020\055\013\064\014\101\021\107" +
    "\016\110\012\001\002\000\022\004\011\010\015\011\020" +
    "\055\013\064\014\101\021\107\016\110\012\001\002\000" +
    "\022\004\011\010\015\011\020\055\013\064\014\101\021" +
    "\107\016\110\012\001\002\000\022\004\011\010\015\011" +
    "\020\055\013\064\014\101\021\107\016\110\012\001\002" +
    "\000\022\004\011\010\015\011\020\055\013\064\014\101" +
    "\021\107\016\110\012\001\002\000\022\004\011\010\015" +
    "\011\020\055\013\064\014\101\021\107\016\110\012\001" +
    "\002\000\022\004\011\010\015\011\020\055\013\064\014" +
    "\101\021\107\016\110\012\001\002\000\022\004\011\010" +
    "\015\011\020\055\013\064\014\101\021\107\016\110\012" +
    "\001\002\000\022\004\011\010\015\011\020\055\013\064" +
    "\014\101\021\107\016\110\012\001\002\000\022\004\011" +
    "\010\015\011\020\055\013\064\014\101\021\107\016\110" +
    "\012\001\002\000\022\004\011\010\015\011\020\055\013" +
    "\064\014\101\021\107\016\110\012\001\002\000\040\056" +
    "\uffef\063\033\064\024\065\030\066\036\067\025\070\035" +
    "\071\uffef\072\uffef\073\uffef\074\uffef\075\uffef\076\uffef\077" +
    "\uffef\100\uffef\001\002\000\040\056\uffed\063\033\064\024" +
    "\065\030\066\036\067\025\070\035\071\040\072\032\073" +
    "\027\074\031\075\026\076\034\077\023\100\uffed\001\002" +
    "\000\040\056\ufff6\063\ufff6\064\ufff6\065\ufff6\066\ufff6\067" +
    "\025\070\ufff6\071\ufff6\072\ufff6\073\ufff6\074\ufff6\075\ufff6" +
    "\076\ufff6\077\ufff6\100\ufff6\001\002\000\040\056\ufff4\063" +
    "\ufff4\064\ufff4\065\ufff4\066\ufff4\067\025\070\ufff4\071\ufff4" +
    "\072\ufff4\073\ufff4\074\ufff4\075\ufff4\076\ufff4\077\ufff4\100" +
    "\ufff4\001\002\000\040\056\ufff1\063\033\064\024\065\030" +
    "\066\036\067\025\070\035\071\ufff1\072\ufff1\073\ufff1\074" +
    "\ufff1\075\ufff1\076\ufff1\077\ufff1\100\ufff1\001\002\000\040" +
    "\056\ufff9\063\ufff9\064\ufff9\065\030\066\036\067\025\070" +
    "\035\071\ufff9\072\ufff9\073\ufff9\074\ufff9\075\ufff9\076\ufff9" +
    "\077\ufff9\100\ufff9\001\002\000\040\056\uffee\063\033\064" +
    "\024\065\030\066\036\067\025\070\035\071\uffee\072\uffee" +
    "\073\uffee\074\uffee\075\uffee\076\uffee\077\uffee\100\uffee\001" +
    "\002\000\040\056\ufff2\063\033\064\024\065\030\066\036" +
    "\067\025\070\035\071\ufff2\072\ufff2\073\ufff2\074\ufff2\075" +
    "\ufff2\076\ufff2\077\ufff2\100\ufff2\001\002\000\040\056\ufff7" +
    "\063\ufff7\064\ufff7\065\ufff7\066\ufff7\067\025\070\ufff7\071" +
    "\ufff7\072\ufff7\073\ufff7\074\ufff7\075\ufff7\076\ufff7\077\ufff7" +
    "\100\ufff7\001\002\000\040\056\ufff3\063\033\064\024\065" +
    "\030\066\036\067\025\070\035\071\ufff3\072\ufff3\073\ufff3" +
    "\074\ufff3\075\ufff3\076\ufff3\077\ufff3\100\ufff3\001\002\000" +
    "\040\056\ufff0\063\033\064\024\065\030\066\036\067\025" +
    "\070\035\071\ufff0\072\ufff0\073\ufff0\074\ufff0\075\ufff0\076" +
    "\ufff0\077\ufff0\100\ufff0\001\002\000\040\056\ufff5\063\ufff5" +
    "\064\ufff5\065\ufff5\066\ufff5\067\ufff5\070\ufff5\071\ufff5\072" +
    "\ufff5\073\ufff5\074\ufff5\075\ufff5\076\ufff5\077\ufff5\100\ufff5" +
    "\001\002\000\040\056\ufff8\063\ufff8\064\ufff8\065\030\066" +
    "\036\067\025\070\035\071\ufff8\072\ufff8\073\ufff8\074\ufff8" +
    "\075\ufff8\076\ufff8\077\ufff8\100\ufff8\001\002\000\040\056" +
    "\uffec\063\033\064\024\065\030\066\036\067\025\070\035" +
    "\071\040\072\032\073\027\074\031\075\026\076\034\077" +
    "\uffec\100\uffec\001\002\000\010\002\ufffc\027\ufffc\062\060" +
    "\001\002\000\006\002\ufffd\027\ufffd\001\002\000\040\056" +
    "\ufffb\063\ufffb\064\ufffb\065\ufffb\066\ufffb\067\ufffb\070\ufffb" +
    "\071\ufffb\072\ufffb\073\ufffb\074\ufffb\075\ufffb\076\ufffb\077" +
    "\ufffb\100\ufffb\001\002\000\040\056\063\063\033\064\024" +
    "\065\030\066\036\067\025\070\035\071\040\072\032\073" +
    "\027\074\031\075\026\076\034\077\023\100\037\001\002" +
    "\000\040\056\uffe6\063\uffe6\064\uffe6\065\uffe6\066\uffe6\067" +
    "\uffe6\070\uffe6\071\uffe6\072\uffe6\073\uffe6\074\uffe6\075\uffe6" +
    "\076\uffe6\077\uffe6\100\uffe6\001\002\000\004\002\001\001" +
    "\002\000\006\002\uffff\027\uffff\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\063\000\010\002\005\003\003\004\004\001\001\000" +
    "\004\004\064\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\005\016\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\005\061\001\001\000\004" +
    "\005\060\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\005\021\001\001" +
    "\000\002\001\001\000\004\005\055\001\001\000\004\005" +
    "\054\001\001\000\004\005\053\001\001\000\004\005\052" +
    "\001\001\000\004\005\051\001\001\000\004\005\050\001" +
    "\001\000\004\005\047\001\001\000\004\005\046\001\001" +
    "\000\004\005\045\001\001\000\004\005\044\001\001\000" +
    "\004\005\043\001\001\000\004\005\042\001\001\000\004" +
    "\005\041\001\001\000\004\005\040\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Sintactico$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Sintactico$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Sintactico$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


 
    
    public Arbol AST;

    public Arbol getAST() {
        return AST;
    }

    public void syntax_error(Symbol s){ 
            System.err.println("Error Sintáctico en la Línea " + (s.left) +" Columna "+s.right+ ". No se esperaba este componente: " +s.value+"."); 
    } 
    
    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception{ 
            System.err.println("Error síntactico irrecuperable en la Línea " + (s.left)+ " Columna "+s.right+". Componente " + s.value + " no reconocido."); 
    }    


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Sintactico$actions {
  private final Sintactico parser;

  /** Constructor */
  CUP$Sintactico$actions(Sintactico parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Sintactico$do_action_part00000000(
    int                        CUP$Sintactico$act_num,
    java_cup.runtime.lr_parser CUP$Sintactico$parser,
    java.util.Stack            CUP$Sintactico$stack,
    int                        CUP$Sintactico$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Sintactico$result;

      /* select the action based on the action number */
      switch (CUP$Sintactico$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= inicio EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		RESULT = start_val;
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Sintactico$parser.done_parsing();
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // inicio ::= instrucciones 
            {
              Object RESULT =null;
		int insleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int insright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		LinkedList<Instruccion> ins = (LinkedList<Instruccion>)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 parser.AST = new Arbol(ins); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("inicio",0, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // instrucciones ::= instrucciones instruccion 
            {
              LinkedList<Instruccion> RESULT =null;
		int inssleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int inssright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		LinkedList<Instruccion> inss = (LinkedList<Instruccion>)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		int insleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int insright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion ins = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = inss; RESULT.add(ins); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("instrucciones",1, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // instrucciones ::= instruccion 
            {
              LinkedList<Instruccion> RESULT =null;
		int insleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int insright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion ins = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new LinkedList<>(); RESULT.add(ins); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("instrucciones",1, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // instruccion ::= RPRINT PIZQ expresion PDER PUNTOCOMA 
            {
              Instruccion RESULT =null;
		int expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion exp = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		 RESULT = new Print(exp,expleft,expright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("instruccion",2, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-4)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // instruccion ::= RPRINT PIZQ expresion PDER 
            {
              Instruccion RESULT =null;
		int expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Instruccion exp = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		 RESULT = new Print(exp,expleft,expright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("instruccion",2, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-3)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // expresion ::= MENOS expresion 
            {
              Instruccion RESULT =null;
		int expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion exp = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.MENOS_UNARIO, exp, expleft, expright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // expresion ::= NOT expresion 
            {
              Instruccion RESULT =null;
		int expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion exp = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.NOT, exp, expleft, expright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // expresion ::= expresion MAS expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.SUMA,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // expresion ::= expresion MENOS expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.RESTA,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // expresion ::= expresion MULTIPLICACION expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.MULTIPLICACION,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // expresion ::= expresion DIVISION expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.DIVISION,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // expresion ::= expresion POTENCIA expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.POTENCIA,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // expresion ::= expresion MODULO expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.MODULO,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // expresion ::= expresion MENORQUE expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.MENOR_QUE,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // expresion ::= expresion MAYORQUE expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.MAYOR_QUE,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // expresion ::= expresion MENORIGUALQUE expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.MENOR_IGUAL_QUE,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // expresion ::= expresion MAYORIGUALQUE expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.MAYOR_IGUAL_QUE,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // expresion ::= expresion IGUALQUE expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.IGUAL_QUE,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // expresion ::= expresion DISTINTOQUE expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.DISTINTO_QUE,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // expresion ::= expresion OR expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.OR,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // expresion ::= expresion AND expresion 
            {
              Instruccion RESULT =null;
		int izqleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).left;
		int izqright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).right;
		Instruccion izq = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)).value;
		int derleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int derright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		Instruccion der = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(tipo_operacion.AND,izq,der,izqleft,izqright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // expresion ::= ENTERO 
            {
              Instruccion RESULT =null;
		int expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String exp = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(Integer.parseInt(exp),expleft,expright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // expresion ::= DECIMAL 
            {
              Instruccion RESULT =null;
		int expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String exp = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(new Double(exp),expleft,expright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // expresion ::= CADENA 
            {
              Instruccion RESULT =null;
		int expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String exp = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(exp,expleft,expright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // expresion ::= RTRUE 
            {
              Instruccion RESULT =null;
		int expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String exp = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(true,expleft,expright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // expresion ::= RFALSE 
            {
              Instruccion RESULT =null;
		int expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).left;
		int expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()).right;
		String exp = (String)((java_cup.runtime.Symbol) CUP$Sintactico$stack.peek()).value;
		 RESULT = new Operacion(false, expleft, expright); 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // expresion ::= PIZQ expresion PDER 
            {
              Instruccion RESULT =null;
		int expleft = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).left;
		int expright = ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).right;
		Instruccion exp = (Instruccion)((java_cup.runtime.Symbol) CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-1)).value;
		 RESULT = exp; 
              CUP$Sintactico$result = parser.getSymbolFactory().newSymbol("expresion",3, ((java_cup.runtime.Symbol)CUP$Sintactico$stack.elementAt(CUP$Sintactico$top-2)), ((java_cup.runtime.Symbol)CUP$Sintactico$stack.peek()), RESULT);
            }
          return CUP$Sintactico$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Sintactico$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Sintactico$do_action(
    int                        CUP$Sintactico$act_num,
    java_cup.runtime.lr_parser CUP$Sintactico$parser,
    java.util.Stack            CUP$Sintactico$stack,
    int                        CUP$Sintactico$top)
    throws java.lang.Exception
    {
              return CUP$Sintactico$do_action_part00000000(
                               CUP$Sintactico$act_num,
                               CUP$Sintactico$parser,
                               CUP$Sintactico$stack,
                               CUP$Sintactico$top);
    }
}

}