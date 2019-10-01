import java.util.Scanner;

public class User{
    public static void main(String []args){
        Scanner entrada = new Scanner(System.in);
        Proxy proxy = new Proxy();

        int opcao;

        System.out.println("Entre com  uma opção:");
        System.out.println("[1] Calculadora\n[2] Chat");    
        opcao = Integer.parseInt(entrada.nextLine());

        //  Começa a calculadora
        if(opcao == 1){
            double op1, op2, resultado = 0;
            int operacao;
            System.out.println("Entre com o primeiro operador: ");
            op1 = Double.parseDouble(entrada.nextLine());
            System.out.println("Escolha uma operação\n[1] Soma\n[2] Subtração\n[3] Multiplicação\n[4] Divisão");
            operacao = Integer.parseInt(entrada.nextLine());
            System.out.println("Entre com o segundo operador: ");
            op2 = Double.parseDouble(entrada.nextLine());

            //  Faz uso da operação escolhida
            switch(operacao){
                case 1:
                    resultado = proxy.add(op1,op2);
                    break;
                case 2:
                    resultado = proxy.sub(op1,op2);
                    break;
                case 3:
                    resultado = proxy.mul(op1,op2);
                    break;
                case 4:
                    resultado = proxy.div(op1,op2);
                    break;
                default:
                    System.out.println("Operação inválida!");
                    break;
            }
            System.out.println(resultado);
            proxy.close();
        }

        //  Começa o Chat
        else if(opcao == 2){
            String user, msgIn = "", msgOu;

            System.out.println("Conectando...");
            System.out.println("Qual o seu nome? ");
            user = entrada.nextLine();
            System.out.println("Para encerrar, digite [Logout]");
            while(true){
                System.out.print(user+": ");
                msgOu = entrada.nextLine();
                if(msgOu.equals("Logout")){
                    System.out.println("Conexão encerrada!");
                    proxy.close();
                    break;
                }
                proxy.sendMessage("["+user+"] "+msgOu);
                msgIn = proxy.getMessage();
                System.out.println(msgIn);
            }
            proxy.close();
        }
        else{
            System.out.println("Opção inválida!");
        }
        entrada.close();
    }
}