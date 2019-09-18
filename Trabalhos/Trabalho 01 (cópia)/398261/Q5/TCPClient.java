import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient{
    public static void main(String []args){
        Scanner scn = new Scanner(System.in);
        Socket socket = null;
        DataInputStream dis;
        DataOutputStream dos;

        try{
            socket = new Socket("localhost", 7896);

            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            readServer read = new readServer(dis);
            Thread t = new Thread(read);
            t.start();

            System.out.println("Cliente conectado!\nPara sair, digite [Logout_]\n");

            String msg;

            while(true){
                msg = scn.nextLine();

                if(msg.trim().length() > 0){
                    if(msg.equals("Logout_")){
                        dos.writeUTF(msg);
                        break;
                    }
                    dos.writeUTF(msg);
                }
            }
        } catch (UnknownHostException e){
            System.out.println("Socket: "+e.getMessage());
        } catch (EOFException e){
            System.out.println("EOF: "+e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

class readServer implements Runnable{
    DataInputStream dis;
    public readServer(DataInputStream in){
        this.dis = in;
    }

    @Override
    public void run(){
        String msg;
        try{
            while(true){
                msg = this.dis.readUTF();
                System.out.println("<< " + msg);
            }
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}