import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
	public void run(){
		Scanner en = new Scanner(System.in);
		try {
			String msgIn, msgOu, name;


			msgIn = in.readUTF();
			if(msgIn.equals("Chat")){
				out.writeUTF("ChatAccepted");
				System.out.print("Digite o seu nome: ");
				name = en.nextLine();
				while(true){
					msgIn = in.readUTF();
					if(msgIn.equals("Logout_")){
						break;
					}
					System.out.print(msgIn + "\n" + name + ": ");
					msgOu = en.nextLine();
					out.writeUTF(name + ": " + msgOu);
				}
			}
			else if (msgIn.equals("Calc")) {
				out.writeUTF("CalcAccepted");
				msgIn = in.readUTF();
				String sep[] = new String[3];
				sep = msgIn.split(";");
				double op1 = Double.parseDouble(sep[0]);
				char operacao = sep[1].charAt(0);
				double op2 = Double.parseDouble(sep[2]);

				switch (operacao) {
					case '+':
						msgOu = String.valueOf(op1+op2);
						break;

					case '-':
						msgOu = String.valueOf(op1-op2);
						break;

					case '*':
						msgOu = String.valueOf(op1*op2);
						break;
					
					case '/':
						if(op2 == 0){
							msgOu = "Divisão inválida";
						}
						msgOu = String.valueOf(op1/op2);
						break;
				
					default:
						msgOu = "Operação inválida!";
						break;
				}
				out.writeUTF(msgOu);
			} 
			else {
				out.writeUTF("Erro!!!");
			}
			
		} catch(EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ 
			try {
				clientSocket.close();
			} catch (IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
}

