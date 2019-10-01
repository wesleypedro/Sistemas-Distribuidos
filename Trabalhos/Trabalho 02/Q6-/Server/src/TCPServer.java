import java.net.*;
import java.io.*;

public class TCPServer{
    public static void main(String args[]){
        new TCPServer();
    }

    private Socket clientSocket;
    private Despachante despachante;

    public TCPServer(){
        try{
            int serverPort = 7896;
            @SuppressWarnings("resource")
            ServerSocket listenSocket = new ServerSocket(serverPort);
            despachante = new Despachante();
            System.out.println("Servidor rodando...");
            while(true) {
                clientSocket = listenSocket.accept();
                while(true){
                    String request = getRequest();
                    if(request.equals("Logout")){
                        break;
                    }
                    String resultado = despachante.invoke(request);
                    sendResponse(resultado);
                }
            }
        } catch(IOException e) {
            System.out.println("Listen socket:"+e.getMessage());
        }
    }
    
    public String getRequest() {
        String expr = "";
        try {
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            expr = in.readUTF();	
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expr;
    }


    public void sendResponse(String response) {
        try {
            DataOutputStream out =new DataOutputStream(clientSocket.getOutputStream());
            out.writeUTF(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}