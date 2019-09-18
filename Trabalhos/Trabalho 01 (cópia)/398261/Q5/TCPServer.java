import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPServer{
	public static void main(String []args){
		Socket s = null;
		try {
			int serverPort = 7896;
			ServerSocket ss = new ServerSocket(serverPort);
			System.out.println("Servidor rodando...");
			while(true){
				s = ss.accept();
				System.out.println("Nova conecção com "+s.getInetAddress()+":" + s.getPort());
				Connection c = new Connection(s);
			}
		} catch (IOException e) {
			System.out.println("Listen Socket: " + e.getMessage());
		}
	}
}

class Connection extends Thread{
	Socket cliente = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;

	public Connection(Socket s){
		try {
			cliente = s;
			dis = new DataInputStream(cliente.getInputStream());
			dos = new DataOutputStream(cliente.getOutputStream());
			this.start();
		} catch(IOException e) {
			System.out.println("Connection: "+e.getMessage());
		}
		
	}

	@Override
	public void run(){
		try {
			SendClient send = new SendClient(dos);
			Thread s = new Thread(send);
			s.start();

			ReadClient read = new ReadClient(dis);
			Thread r = new Thread(read);
			r.start();
			r.join();
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally{
			try {
				System.out.println("Conexão com "+cliente.getInetAddress()+":" + cliente.getPort()+" encerrada.\n");
				dis.close();
				dos.close();
				cliente.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}


class ReadClient implements Runnable{
	DataInputStream dis = null;

	public ReadClient(DataInputStream in){
		dis = in;
	}

	@Override
	public void run(){
		String msg;
		try {
			while(true){
				msg = dis.readUTF();
				if(msg.equals("Logout_")){
					System.out.println("Conexão encerrada!");
					break;
				}
				System.out.println("<< " + msg);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally{
			try {
				dis.close();
			} catch (IOException e) {
				System.out.println("Connection: "+e.getMessage());
			}
		}
	}
}



class SendClient implements Runnable{
	Scanner en = new Scanner(System.in);
	DataOutputStream dos = null;

	public SendClient(DataOutputStream ou){
		dos = ou;
	}

	public void run(){
		String msg = null;
		try {
			while(true){
				msg = en.nextLine();
				if(msg.trim().length() > 0){
					dos.writeUTF(msg);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			try {
				dos.close();
			} catch (IOException e) {
				System.out.println("Connection: "+e.getMessage());
			}
		}
	}
}