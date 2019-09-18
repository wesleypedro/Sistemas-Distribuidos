package br.ufc.sd.fController;

import br.ufc.sd.fConnection.Client;
import br.ufc.sd.fJson.UserJson;
import br.ufc.sd.fModel.Ingresso;
import br.ufc.sd.fModel.UserAtt;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Wesley Pedro
 */
public class vendController {
    
    Scanner entrada;
    UserJson userJson;
    
    public vendController(){
        entrada = new Scanner(System.in);
        userJson = UserJson.INSTANCE;
    }
    

    //methodId = 1
    public void venderIngresso(Client client) {
        int codigo;
        int quantidade = -1;
        int confirmar = 0;
        
        System.out.println("Entre com o codigo do ingresso: ");
        codigo = Integer.parseInt(entrada.nextLine());
        
        String query = "{\"query\":\"codigo = " + codigo + "\"}";
        
        String reply = client.doOperation("vendController", 3, query);
        List <Ingresso> ingresso = userJson.IngressoListFromGson(reply);
        Ingresso fjIngresso = ingresso.get(0);
        
        if(fjIngresso.getQuantidade() > 0){
            System.out.println(fjIngresso.toString());
            while (true){
                System.out.println("Entre com a quantidade de ingressos: ");
                quantidade = Integer.parseInt(entrada.nextLine());

                if(quantidade > fjIngresso.getQuantidade()){
                    System.out.println("Quantidade invalida!!!");
                }
                else{
                    break;
                }
            }
            
            System.out.println("O valor total é de R$" + (quantidade * fjIngresso.getPreco()) + ".");
            
            while(confirmar < 1 || confirmar > 2){
                System.out.println("Deseja realmente confirmar a compra? ");
                System.out.println("[1] Confirmar\n[2] Nao");
                confirmar = Integer.parseInt(entrada.nextLine());

                if(confirmar < 1 || confirmar > 2){
                    System.out.println("Opcao invalida");
                }
            }
            
            if(confirmar == 1){
                Ingresso tjComprarIngresso = new Ingresso(fjIngresso.getCodigo(), fjIngresso.getQuantidade() - quantidade);
                String requestComprar = userJson.IngressoToGson(tjComprarIngresso);

                String replyComprar = client.doOperation("vendController", 1, requestComprar);
                Ingresso fjComprarIngresso = userJson.IngressoFromGson(replyComprar);
                
                if(fjComprarIngresso.getQuantidade() < fjIngresso.getQuantidade()){
                    System.out.println("Compra realizada com sucesso!");
                    return;
                }
                else{
                    System.out.println("Erro! Nao foi possivel concluir.");
                }
                return;
            }
            
            System.out.println("Compra cancelada!");
        }
        else if(fjIngresso.getQuantidade() == 0){
            System.out.println("Nao ha mais ingressos disponiveis!");
        }
        
        else{
            System.out.println("Nao foi possivel concluir a compra.\n"
                             + "Verifique o codigo do ingresso!");
        }
    }

    //methodId = 2
    public void listarIngressos(Client client) {
        String arguments = "{\"request\":\"all\"}";
        
        String jsonArray = client.doOperation("vendController", 2, arguments);
        
        List lista = userJson.IngressoListFromGson(jsonArray);
        
        for(int i = 0; i < lista.size(); i ++){
            System.out.println(lista.get(i).toString());
        }   
    }

    //methodId = 3
    public void pesquisarIngresso(Client client) {
        int opcao = 0;
        String query;
        
        while(opcao < 1 || opcao > 2){
            System.out.println("Pesquisar por:");
            System.out.println("[1] Codigo\t[2] Nome");
            opcao = Integer.parseInt(entrada.nextLine());
            
            if(opcao < 1 || opcao > 2){
                System.out.println("Opcao invalida");
            }
        }
        
        if(opcao == 1){
            int codigo;
            System.out.println("Entre com o codigo a ser pesquisado: ");
            codigo = Integer.parseInt(entrada.nextLine());
            query = "{\"query\":\"codigo = " + codigo + "\"}";
        }
        
        else{
            String nome;
            System.out.println("Entre com o nome a ser pesquisado: ");
            nome = entrada.nextLine();
            query = "{\"query\":\"nome like '%" + nome + "%'\"}";
        }
        
        String reply = client.doOperation("vendController", 3, query);
        List lista = userJson.IngressoListFromGson(reply);
        
        for(int i = 0; i < lista.size(); i ++){
            System.out.println(lista.get(i).toString());
        }
    }

    //methodId = 4
    public void alterarSenha(Client client) {
        String cpf;
        String senhaAntiga;
        String novaSenha = "-1";
        String confirmaSenha = "0";
        
        System.out.println("Entre com o CPF para atualizar a sua senha: ");
        cpf = entrada.nextLine();
        System.out.println("Entre com a sua senha antiga: ");
        senhaAntiga = entrada.nextLine();
        
        while(!novaSenha.equals(confirmaSenha)){
            System.out.println("Entre com a sua nova senha: ");
            novaSenha = entrada.nextLine();
            System.out.println("Confirme a sua senha: ");
            confirmaSenha = entrada.nextLine();

            if(!novaSenha.equals(confirmaSenha)){
                System.out.println("Senhas não conhecidem");
            }
        }
        
        System.out.println("Verificando...");
        
        UserAtt user = new UserAtt(cpf, senhaAntiga);
        String jUser = userJson.UserAttToGson(user);
        String jReply = client.doOperation("vendController", 5, jUser);
        UserAtt reply = userJson.UserAttFromGson(jReply);
        
        if(reply.getCpf().equals("-1")) System.out.println("Erro! CPF e/ou senha antiga invalido");
        else{
            UserAtt passAlterar = new UserAtt(cpf, senhaAntiga, novaSenha);
            String jPassAlterar = userJson.UserAttToGson(passAlterar);
            String jReplyAlterar = client.doOperation("vendController", 4, jPassAlterar);
            UserAtt replyAlterar = userJson.UserAttFromGson(jReplyAlterar);
            
            if(replyAlterar.getCpf().equals("-1") && replyAlterar.getUsername().equals("Erro")){
                System.out.println("Erro ao fazer alteracao");
            }
            else{
                System.out.println("Senha alterada com sucesso!");
            }
        }
    }
}
