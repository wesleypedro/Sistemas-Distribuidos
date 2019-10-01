public class Calc{
//    public Calc(){
//        
//    }

    public double add(double op1, double op2){
        return op1 + op2;
    }

    public double sub(double op1, double op2){
        return op1 - op2;
    }

    public double mul(double op1, double op2){
        return op1 * op2;
    }

    public double div(double op1, double op2){
        double resultado = 0;
        try{
            resultado = op1 / op2;
            return resultado;
        } catch(ArithmeticException arithmeticException){
            return resultado;
        }
    }
}