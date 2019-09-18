package br.ufc.sd.fClient;

import br.ufc.sd.fConnection.Client;
import br.ufc.sd.fController.vendController;
import java.util.Scanner;

/**
 *
 * @author Wesley Pedro
 */
public class vendUser {
    Scanner entrada;
    vendController vend;
    Client client;
        

    public vendUser(Client client) {
        entrada = new Scanner(System.in);
        vend = new vendController();
        this.client = client;
    }
    
    public void vend(){
        int opcao = 0;
        do{            
            System.out.println("ESCOLHA UMA OPÇÃO:\n");
            System.out.println("[1] Vender Ingresso");
            System.out.println("[2] Listar Ingressos Disponíveis");
            System.out.println("[3] Pesquisar por um Ingresso");
            System.out.println("[4] Alterar Senha");
            System.out.println("[5] Sair");
            opcao = Integer.parseInt(entrada.nextLine());
            
            switch (opcao){
                case 1:
                    vend.venderIngresso(client);
                    break;
                    
                case 2:
                    vend.listarIngressos(client);
                    break;
                    
                case 3:
                    vend.pesquisarIngresso(client);
                    break;
                    
                case 4:
                    vend.alterarSenha(client);
                    break;
                    
                case 5:
                    client.sair();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
        }while(opcao != 5);
    }
}
