package br.ufc.sd.fConnection;

import br.ufc.sd.fJson.UserJson;
import br.ufc.sd.fModel.Message;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Wesley Pedro
 */
public class Client {

    private Socket s;
    UserJson userJson;

    int requestId = 0;
    
    BufferedReader in;
    BufferedWriter out;
    
    public Client() {
        userJson = UserJson.INSTANCE;
        
        int serverPort = 12001;
        String hostname = "localhost";
        try {

            s = new Socket(hostname, serverPort);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8));
    
        } catch (UnknownHostException e) {
            System.out.println("Socket: " + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("readline: " + e.getMessage());
            System.exit(0);
        }

    }

    public String doOperation(String objeto, int methodId, String arguments) {
        boolean controle = true;
        Message messageRetorno = null;
        int repeat = 0;
        
        try {
            Message message = new Message(0, requestId, objeto, methodId, arguments);
            String json = userJson.MessageToGson(message);
            
            out.write(json);
            out.flush();
            
            while(controle){
                String response = in.readLine();
                messageRetorno = userJson.MessageFromGson(response);
                
                if(messageRetorno.getRequestId() == requestId && messageRetorno.getMessageType() == 1){
                    requestId += 1;
                    controle = false;
                }
                else{
                    out.write(json);
                    out.flush();
                    repeat += 1;
                }
                
                if(repeat == 20){
                    return null;
                }
            }
            
            

        } catch (IOException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }

        return messageRetorno.getArguments();
    }

    public void sair() {
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
