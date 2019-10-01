import java.net.*;
import java.io.*;

public class TCPClient{
    private Socket s;
    
    public TCPClient(){
        int serverPort = 7896;
        String hostname = "localhost";
        try {
            s = new Socket(hostname, serverPort);
        }catch (UnknownHostException e){
            System.out.println("Socket: "+e.getMessage());
        }catch (IOException e){
            System.out.println("readline: "+e.getMessage());
        }
    }

    public void sendRequest(String request){
        try{
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(request);
        }catch (IOException e){
//            System.out.println("readline: "+e.getMessage());
        }
    }

    public String getResponse() {
        String response = "";
        try {
            DataInputStream in = new DataInputStream(s.getInputStream());
            response = in.readUTF();
        }catch (IOException e){
//            System.out.println("readline: "+e.getMessage());
        }
        return response;
    }
	

    public void close() {
        try {
            sendRequest("Logout");
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}