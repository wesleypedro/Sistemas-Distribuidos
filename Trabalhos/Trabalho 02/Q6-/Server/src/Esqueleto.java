public class Esqueleto{
    Calc calc;

    public Esqueleto(){
        calc = new Calc();
    }

    public String add(String add){
        String separado[] = new String[2];
        separado= add.split(";");
        double op1 = Double.parseDouble(separado[0]);
        double op2 = Double.parseDouble(separado[1]);
        double reply = calc.add(op1, op2);
        return String.valueOf(reply);
    }

    public String sub(String sub){
        String separado[] = new String[2];
        separado= sub.split(";");
        double op1 = Double.parseDouble(separado[0]);
        double op2 = Double.parseDouble(separado[1]);
        double reply = calc.sub(op1, op2);
        return String.valueOf(reply);
    }

    public String mul(String mul){
        String separado[] = new String[2];
        separado= mul.split(";");
        double op1 = Double.parseDouble(separado[0]);
        double op2 = Double.parseDouble(separado[1]);
        double reply = calc.mul(op1, op2);
        return String.valueOf(reply);
    }

    public String div(String div){
        String separado[] = new String[2];
        separado= div.split(";");
        double op1 = Double.parseDouble(separado[0]);
        double op2 = Double.parseDouble(separado[1]);
        double reply = calc.div(op1, op2);
        return String.valueOf(reply);
    }
}