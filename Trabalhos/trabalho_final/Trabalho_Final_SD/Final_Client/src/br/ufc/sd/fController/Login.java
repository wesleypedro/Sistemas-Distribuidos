package br.ufc.sd.fController;

import br.ufc.sd.fConnection.Client;
import br.ufc.sd.fJson.UserJson;
import br.ufc.sd.fModel.LoginAtt;
import java.util.Scanner;

/**
 *
 * @author Wesley Pedro
 */
public class Login {
    Scanner entrada = new Scanner(System.in);
    private static Client cliente = null;
    
    public Login(Client client){
        cliente = client;
    }
    
    public LoginAtt Logar(){
        
        String cpf;
        String pass;
        String json;

        System.out.print("CPF: ");
        cpf = entrada.nextLine();
        System.out.print("Senha: ");
        pass = entrada.nextLine();
        
        LoginAtt att = new LoginAtt(cpf, pass);
        
        UserJson userJson = UserJson.INSTANCE;
        json = userJson.LoginToGson(att);
        
        String reply = cliente.doOperation("login", 1, json);
        
        if(reply == null){
            return null;
        }
        
        return userJson.LoginFromGson(reply);
    }
}
