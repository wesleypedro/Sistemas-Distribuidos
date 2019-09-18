import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPServer {
	public static void main (String args[]) {
		ServerSocket listenSocket = null;
		Connection c;
		try{
			int serverPort = 7896;
			listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				c = new Connection(clientSocket);
			}
		} catch(IOException e) {
			System.out.println("Listen socket:"+e.getMessage());
		}
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
			System.out.print("Digite o seu nome: ");
			name = en.nextLine();
			while(true){
				msgIn = in.readUTF();
				System.out.print(msgIn + "\n" + name + ": ");
				msgOu = en.nextLine();
				out.writeUTF(name + ": " + msgOu);
			}
		} catch(EOFException e){System.out.println("EOF: "+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ 
			try {
				en.close();
				clientSocket.close();
			}catch (IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
}

