import java.net.*;
import java.io.*;
public class UDPServer{

	public static String res(String text){
		String sep[] = new String[3];
		sep = text.split(";");
		double op1 = Double.parseDouble(sep[0]);
		char operacao = sep[1].charAt(0);
		double op2 = Double.parseDouble(sep[2]);

		switch (operacao) {
			case '+':
				return String.valueOf(op1+op2);
			
			case '-':
				return String.valueOf(op1-op2);

			case '*':
				return String.valueOf(op1*op2);
			
			case '/':
				if(op2 == 0){
					return "Divisão inválida";
				}
				return String.valueOf(op1/op2);
		
			default:
				return "Operação inválida!";
		}
	}


	public static void main(String args[]){ 
		DatagramSocket aSocket = null;
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

				buffer = res(received).getBytes();
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