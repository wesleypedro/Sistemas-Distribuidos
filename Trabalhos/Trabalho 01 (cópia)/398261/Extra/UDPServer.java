import java.net.*;
import java.io.*;

public class UDPServer{
	public static void main(String args[]){ 
		DatagramSocket aSocket = null;
		calculadora calc = calculadora.INSTACE;
		try{
			System.out.println("Server is running...");
			aSocket = new DatagramSocket(6789);
			while(true){
				byte[] buffer = new byte[256];

				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);

				InetAddress address = request.getAddress();
				int port = request.getPort();

				System.out.println("Conexão com "+address+":"+port);
				
				String received = new String(request.getData(), 0, request.getLength());

				buffer = (calc.res(received)).getBytes();
				request = new DatagramPacket(buffer, buffer.length, address, port);
				aSocket.send(request);
			}
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
	}finally {
	if(aSocket != null) 
			aSocket.close();}
    }
}