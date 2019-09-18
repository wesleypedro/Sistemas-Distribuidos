import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient {

	public static String calc(){
		Scanner e = new Scanner(System.in);	
		
		double operador1, operador2;
		char operacao;

		System.out.println("Entre com o primeiro termo da operação: ");
		operador1 = e.nextDouble();
		System.out.println("Entre com o operador:\n+ -> Soma\n- -> Subtração\n* -> Multiplicação\n/ -> Divisão");
		operacao = e.next().charAt(0);
		System.out.println("Entre com o segundo termo da operação: ");
		operador2 = e.nextDouble();

		String retorno = operador1 +";"+ operacao +";"+ operador2;

		e.close();
		return retorno;
	}


	public static void main (String args[]) {
		Scanner en = new Scanner(System.in);
		Socket s = null;
		String option;

		System.out.println("Digite uma opção:\n[1 - Chat, 2 - Calculadora]");
		option = en.nextLine();


		try{
			int serverPort = 7896;
			String user, msgIn, msgOu, calculadora;
			s = new Socket("localhost", serverPort);
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());

			if(option.equals("Chat") || option.equals("chat") || option.equals("1")){
				out.writeUTF("Chat");
				msgIn = in.readUTF();
				if(msgIn.equals("ChatAccepted")){
					System.out.println("Conectando...");
					System.out.print("Qual o seu nome? ");
					user = en.nextLine();
					System.out.println("Para encerrar, digite [Logout_]");
					while(true){
						System.out.print(user+": ");
						msgOu = en.nextLine();
						if(msgIn.equals("Logout_")){
							out.writeUTF("Logout_");
							break;
						}
						out.writeUTF(user + ": " + msgOu);
						msgIn = in.readUTF();
						System.out.println(msgIn);
					}
				}
				else{
					System.out.println("Erro durante a conexão!!!");
				}
			}
	
			else if(option.equals("Calculadora") || option.equals("calculadora") || option.equals("2")){
				out.writeUTF("Calc");
				msgIn = in.readUTF();
				if(msgIn.equals("CalcAccepted")){
					calculadora = calc();
					out.writeUTF(calculadora);
					msgIn = in.readUTF();
					System.out.println(msgIn);
					System.out.println("Encerrando...");
				}
				else{
					System.out.println("Erro durante a conexão!!!");
				}
			}
			else{
				System.out.println("Opção inválida");
			}

		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}

