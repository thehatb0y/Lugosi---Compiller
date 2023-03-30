import java.io.*;

enum TokenType{ NUM,SOMA,MULT,SUB,DIV,APar,FPar,EOF}

class Token {
	String lexema;
	TokenType token;
  
	Token (String l, TokenType t) {
	  lexema = l;
	  token = t;
	}
	/* Token for Strings*/
	Token (char l, TokenType t) {
	  lexema = String.valueOf(l);
	  token = t;
	}
}

class AnaliseLexica {

	BufferedReader arquivo;

	AnaliseLexica(String a) throws Exception
	{
	 	this.arquivo = new BufferedReader(new FileReader(a));
	}

	Token getNextToken() throws Exception
	{	
		int eof = -1;

		char currchar;
		int currchar1;
		/* New Variable */
		char nextchar;
		int nextchar1;

			do{
				currchar1 =  arquivo.read();
				currchar = (char) currchar1;
			} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
			
			if(currchar1 != eof && currchar1 !=10)
			{
				if (currchar >= '0' && currchar <= '9')
				{
					/* Save File Current Position */ 
					arquivo.mark(1);
					nextchar1 =  arquivo.read();
					nextchar = (char) nextchar1;
					
					/* Check if Next Position is a number */ 
					if (nextchar >= '0' && nextchar <= '9')
					{
						return (new Token ("" + currchar + nextchar, TokenType.NUM));
					}
					/* If Next Position is Not a Number, Then Back to Mark and Return the Number*/ 
					else{
						arquivo.reset();
						return (new Token (currchar, TokenType.NUM));
					}
				}
				else{
					switch (currchar){
						case '(':
							return (new Token (currchar,TokenType.APar));
						case ')':
							return (new Token (currchar,TokenType.FPar));
						case '+':
							return (new Token (currchar,TokenType.SOMA));
						case '*':
							return (new Token (currchar,TokenType.MULT));
						case '/':
							return (new Token (currchar,TokenType.DIV));
						case '-':
							return (new Token (currchar,TokenType.SUB));
						default: throw (new Exception("Caractere inválido: " + ((int) currchar)));
					}
				}
			}
			arquivo.close();
		return (new Token(currchar,TokenType.EOF));
	}
}