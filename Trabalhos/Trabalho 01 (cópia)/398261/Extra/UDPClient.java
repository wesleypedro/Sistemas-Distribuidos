import java.net.*;
import java.io.*;

import java.util.Scanner;

public class UDPClient{
	
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


	public static void main(String args[]){ 
	
	DatagramSocket aSocket = null;
	try {
		aSocket = new DatagramSocket();
		byte [] m = new byte[256];
		InetAddress aHost = InetAddress.getLocalHost();
		int serverPort = 6789;

		m = calc().getBytes();
		
		DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
		aSocket.send(request);	                        
		byte[] buffer = new byte[256];
		DatagramPacket reply = new DatagramPacket(buffer, buffer.length);	
		aSocket.receive(reply);

		System.out.println("Resultado: " + new String(reply.getData()));	

		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {
		if(aSocket != null) 
			aSocket.close();
		}

	}		      	
}