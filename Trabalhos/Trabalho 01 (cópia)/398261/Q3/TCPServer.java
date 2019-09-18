import java.net.*;
import java.util.*;
import java.io.*;
public class TCPServer {
	public static void main (String args[]) {
		hosts h = null;
		HashMap<String, hosts> host = new HashMap<>();

		try{
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();

				if(host.containsKey(clientSocket.toString())){
					new Connection(host, clientSocket);
				}
				else{
					h = new hosts(clientSocket.toString(), 3, "");
					host.put(clientSocket.toString(), h);
					new Connection(host, clientSocket);
				}

			}
		} catch(IOException e) {
			System.out.println("Listen socket:"+e.getMessage());
		}
	}
}



class hosts{
	private String address;
	private int requisicao;
	private String string;

	public hosts(String add,int r, String s){
		address = add;
		requisicao = r;
		string = s;
	}

	public void setAddress(String a){
		address = a;
	}

	public void setRequisicao(int r){
		requisicao = r;
	}

	public void setString(String s){
		string = s;
	}

	public String getAddress(){
		return string;
	}

	public int getRequisicao(){
		return requisicao;
	}

	public String getString(){
		return string;
	}
}



// /**
//  * 	CLASSIFICAÇÃO DO ESTADO DE ACORDO COM O VALOR DO STATUS
//	*											Status refere-se ao número da requisição
//  * 		3	->	Falta receber o primeiro operando
//  * 		2	->	Falta receber o operando
//  * 		1	->	Falta receber o segundo operador
//  * 		0	->	Falta apenas imprimir o resultado para o cliente
//  */

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	hosts h;

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


	public Connection (HashMap<String, hosts> host, Socket aClientSocket){
		clientSocket = aClientSocket;
		h = host.get(clientSocket.toString());
		try {
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {
			System.out.println("Connection:"+e.getMessage());
		}
	}

	public void run(){
		try {
			int ite = h.getRequisicao();
			String str = h.getString();
			in.readUTF();
			out.writeUTF(String.valueOf(ite));
			while(ite > 0){
				if(ite == 3){
					out.writeUTF("Entre com o primeiro operando: ");
					str += in.readUTF() + ";";
				}
				else if(ite == 2){
					out.writeUTF("Entre com o operador: ");
					str += in.readUTF() + ";";
				}
				else if(ite == 1){
					out.writeUTF("Entre com o segundo operando: ");
					str += in.readUTF() + ";";
				}
				else{
					out.writeUTF(res(h.getString()));
				}
				
				h.setString(str);
				ite --;
				h.setRequisicao(ite);
			}

			out.writeUTF(res(h.getString()));
			h.setRequisicao(3);
			h.setString("");

		} catch (SocketTimeoutException s){
			System.out.println("Socket timed out");
		} catch (EOFException e){
			System.out.println("EOF: "+e.getMessage());
		} catch(IOException e) {
			System.out.println("readline: "+e.getMessage());
		} finally{ 
			try {
				clientSocket.close();
			}catch (IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
}