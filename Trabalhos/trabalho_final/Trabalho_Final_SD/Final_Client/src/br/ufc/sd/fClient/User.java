package br.ufc.sd.fClient;

import br.ufc.sd.fConnection.Client;
import br.ufc.sd.fController.Login;
import br.ufc.sd.fModel.LoginAtt;

/** 
 *
 * @author Wesley Pedro
 */
public class User {
    private static Client client = null;
    
    public static void run(){
        client = new Client();
        admUser adm;
        vendUser vend;
        
        Login login = new Login(client);
        
        System.out.println("SISTEMA DE VENDAS DE INGRESSOS");
        
        LoginAtt att = login.Logar();
        System.out.println("\n\n");
        if(att == null){
            System.out.println("Não foi possível conectar...");
        }
        else if(att.getStat()){
            switch (att.getCate()) {
                case "adm":
                    adm = new admUser(client);
                    System.out.println("Bem-vindo "+att.getUser());
                    adm.adm();
                    break;
                case "vend":
                    vend = new vendUser(client);
                    System.out.println("Bem-vindo "+att.getUser());
                    vend.vend();
                    break;
                default:
                    System.out.println("Erro ao acessar seus dados!");
                    client.sair();
                    break;
            }
        }
        else{
            System.out.println("Usuário ou senha incorreto!");
        }
    }
    
    
    public static void main(String[] args) {
        run();
    }
}
