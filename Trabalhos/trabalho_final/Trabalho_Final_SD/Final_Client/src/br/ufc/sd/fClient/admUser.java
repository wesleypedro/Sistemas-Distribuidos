package br.ufc.sd.fClient;

import br.ufc.sd.fConnection.Client;
import br.ufc.sd.fController.admController;
import java.util.Scanner;

/**
 *
 * @author Wesley Pedro
 */
public class admUser {
    Scanner entrada;
    admController adm;
    Client client;
    
    public admUser(Client client){
        entrada = new Scanner(System.in);
        adm = new admController();
        this.client = client;
    }
    
    public void adm(){
        int opcao = 0;
        do{
            System.out.println("\nESCOLHA UMA OPÇÃO:\n");
            System.out.println("[1] Cadastrar Ingresso");
            System.out.println("[2] Vender Ingresso");
            System.out.println("[3] Listar Ingressos Disponíveis");
            System.out.println("[4] Pesquisar por Ingresso");
            System.out.println("[5] Atualizar Ingressos");
            System.out.println("[6] Remover Ingresso");
            System.out.println("[7] Cadastrar Usuario");
            System.out.println("[8] Remover Usuario");
            System.out.println("[9] Alterar Senha");
            System.out.println("[0] Sair");
            opcao = Integer.parseInt(entrada.nextLine());
            
            switch (opcao){
                case 1:
                    adm.cadastrarIngresso(client);
                    break;
                    
                case 2:
                    adm.venderIngresso(client);
                    break;
                    
                case 3:
                    adm.listarIngressos(client);
                    break;
                case 4:
                    adm.pesquisarIngresso(client);
                    break;
                    
                case 5:
                    adm.atualizarIngresso(client);
                    break;
                    
                case 6:
                    adm.removerIngresso(client);
                    break;
                    
                case 7:
                    adm.cadastrarUsuario(client);
                    break;
                    
                case 8:
                    adm.removerUsuario(client);
                    break;
                    
                case 9:
                    adm.alterarSenha(client);
                    break;
                    
                case 0:
                    client.sair();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
        }while(opcao != 0);
    }
}
