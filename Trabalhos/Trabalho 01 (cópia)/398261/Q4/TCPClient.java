import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient {
	public static void main (String args[]) {
		Socket s = null;
		Scanner en = null;
		try{
			en = new Scanner(System.in);
			int serverPort = 7896;
			String user, msgIn, msgOu;
			s = new Socket("localhost", serverPort);
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());

			System.out.println("Conectando...");
			System.out.print("Qual o seu nome? ");
			user = en.nextLine();
			while(true){
				System.out.print(user+": ");
				msgOu = en.nextLine();
				out.writeUTF(user + ": " + msgOu);
				msgIn = in.readUTF();
				System.out.println(msgIn);
			}
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {
			if(s!=null){ 
				try {
					en.close();
					s.close();
				}catch (IOException e){
					System.out.println("close:"+e.getMessage());
				}
			}
		}
  }
}

