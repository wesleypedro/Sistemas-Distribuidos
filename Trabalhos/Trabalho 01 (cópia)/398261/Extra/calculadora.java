public enum calculadora{
	INSTACE;

	public static String res(String text){
		String sep[] = new String[3];
		sep = text.split(";");
		double op1 = Double.parseDouble(sep[0]);
		char operacao = sep[1].charAt(0);
		double op2 = Double.parseDouble(sep[2]);

		switch (operacao) {
			case '+':
				return String.valueOf(op1+op2);
			
			case '-':
				return String.valueOf(op1-op2);

			case '*':
				return String.valueOf(op1*op2);
			
			case '/':
				if(op2 == 0){
					return "Divisão inválida";
				}
				return String.valueOf(op1/op2);
		
			default:
				return "Operação inválida!";
		}
	}
}