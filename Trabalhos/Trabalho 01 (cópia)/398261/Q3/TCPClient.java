import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClient {
	public static void main (String args[]) {
		Scanner en = new Scanner(System.in);
		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket("localhost", serverPort, InetAddress.getLocalHost(), 9875);
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());

			out.writeUTF("start");
			int ite = Integer.parseInt(in.readUTF());
			while(ite > 0){
				System.out.println(in.readUTF());
				out.writeUTF(en.nextLine());
				ite --;
			}

			String data = in.readUTF();
			System.out.println("Resultado: "+ data) ;

		}catch (UnknownHostException e){
			System.out.println("Socket: "+e.getMessage());
		}catch (EOFException e){
			System.out.println("EOF: "+e.getMessage());
		}catch (IOException e){
			System.out.println("readline: "+e.getMessage());
		}finally {
			if(s!=null){
				try {
					s.close();
				}catch (IOException e){
					System.out.println("close: "+e.getMessage());
				}
			}
		}
		en.close();
     }
}

