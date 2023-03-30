import java.io.*;
PARSER_BEGIN(LugosiLexer)
import java.io.StringReader;

public class LugosiLexer {

  public static void main(String[] args) {
    String input = "main var int bool if while do return print function true false";
    LugosiLexer lexer = new LugosiLexer(new StringReader(input));
    Token t;
    do {
      t = lexer.getNextToken();
      System.out.println(t);
    } while (t.kind != LugosiConstants.EOF);
  }

}

PARSER_END(LugosiLexer)

SKIP: { " " | "\t" | "\r" | "\n" }
TOKEN : { <MAIN : "main"> }
TOKEN : { <VAR : "var"> }
TOKEN : { <INT : "int"> }
TOKEN : { <BOOL : "bool"> }
TOKEN : { <IF : "if"> }
TOKEN : { <WHILE : "while"> }
TOKEN : { <DO : "do"> }
TOKEN : { <RETURN : "return"> }
TOKEN : { <PRINT : "print"> }
TOKEN : { <FUNCTION : "function"> }
TOKEN : { <TRUE : "true"> }
TOKEN : { <FALSE : "false"> }
TOKEN : { <IDENTIFIER : (["a"-"z","A"-"Z"])(["a"-"z","A"-"Z","0"-"9","_"])* > }
TOKEN : { <NUM_LITERAL : (["0"-"9"])+("."(["0"-"9"])+)?(("E"|"e")("+"|"-")?(["0"-"9"])+)? > }

MORE: {
  <IDENTIFIER: (["a"-"z","A"-"Z","0"-"9","_"])+ >
  | <NUM_LITERAL: "."(["0"-"9"])+(("E"|"e")("+"|"-")?(["0"-"9"])+)? >
}

MORE: {
  <SINGLE_LINE_COMMENT : "//" (~["\r","\n"])* ("\r"? "\n"|"\r"|"\u2028"|"\u2029")>
  | <MULTI_LINE_COMMENT : "/*" (~["*"])* "*" ("*"+ (~["/"] (~["*"])* "*")?)* "/" >  
}
