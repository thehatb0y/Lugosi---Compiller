class Compilador{

	public static void main(String[]args)
	{	
		ArvoreSintatica arv=null;
	
		try{

			AnaliseLexica al = new AnaliseLexica(args[0]);
			Parser as = new Parser(al);
		
			arv = as.parseProg();
		
			
			CodeGen backend = new CodeGen();
			String codigo = backend.geraCodigo(arv);
			System.out.println("Compilador:");
			System.out.println(codigo);

			Interpreter backend1 = new Interpreter();
			int codigo1 = backend1.eval(arv);
			System.out.println("Interpretador:");
			System.out.println(codigo1);

		}catch(Exception e)
		{			
			System.out.println("Erro de compilação:\n" + e);
		}

	}
}
