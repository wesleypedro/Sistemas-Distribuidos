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
public class admController {
    Scanner entrada;
    UserJson userJson;
    
    public admController(){
        entrada = new Scanner(System.in);
        userJson = UserJson.INSTANCE;
    }

    //methodId = 1
    public void cadastrarIngresso(Client client) {
        String nome;
        int quantidade;
        String horarioInicio;
        String horarioTermino;
        String data;
        String endereco;
        double preco;
        
        System.out.println("Entre com o nome do evento: ");
        nome = entrada.nextLine();
        System.out.println("Entre com a quantidade de ingressos disponiveis: ");
        quantidade = Integer.parseInt(entrada.nextLine());
        System.out.println("Entre com o horario de inicio do evento: ");
        horarioInicio = entrada.nextLine();
        System.out.println("Entre com o horario de termino do evento: ");
        horarioTermino = entrada.nextLine();
        System.out.println("Entre com a data do evento: ");
        data = entrada.nextLine();
        System.out.println("Entre com o endereco do local do evento");
        endereco = entrada.nextLine();
        System.out.println("Entre com o valor do ingresso: ");
        preco = Double.parseDouble(entrada.nextLine());
        
        Ingresso tjIngresso = new Ingresso(nome, quantidade, horarioInicio, horarioTermino, data, endereco, preco);
        String request = userJson.IngressoToGson(tjIngresso);
        
        String reply = client.doOperation("admController", 1, request);
        Ingresso fjIngresso = userJson.IngressoFromGson(reply);
        
        if(fjIngresso == null){
            System.out.println("Erro ao cadastrar o ingresso!");
        }
        else{
            System.out.println("Ingresso cadastrado com sucesso\n");
            System.out.println(fjIngresso.toString());
        }
    }

    //methodId = 2
    public void venderIngresso(Client client) {
        int codigo;
        int quantidade = -1;
        int confirmar = 0;
        
        System.out.println("Entre com o codigo do ingresso: ");
        codigo = Integer.parseInt(entrada.nextLine());
        
        String query = "{\"query\":\"codigo = " + codigo + "\"}";
        
        String reply = client.doOperation("admController", 4, query);
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

                String replyComprar = client.doOperation("admController", 2, requestComprar);
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

    //methodId = 3
    public void listarIngressos(Client client) {
        String arguments = "{\"request\":\"all\"}";
        
        String jsonArray = client.doOperation("admController", 3, arguments);
        
        List lista = userJson.IngressoListFromGson(jsonArray);
        
        for(int i = 0; i < lista.size(); i ++){
            System.out.println(lista.get(i).toString());
        }
    }

    //methodId = 4
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
        
        String reply = client.doOperation("admController", 4, query);
        List lista = userJson.IngressoListFromGson(reply);
        
        for(int i = 0; i < lista.size(); i ++){
            System.out.println(lista.get(i).toString());
        }
    }

    //methodId = 5
    public void atualizarIngresso(Client client) {
        int codigo;
        String query;
        
        System.out.println("Entre com o codigo do evento que voce quer atualizar: ");
        codigo = Integer.parseInt(entrada.nextLine());
        
        query = "{\"query\":\"codigo = " + codigo + "\"}";
        
        String reply = client.doOperation("admController", 4, query);
        System.out.println("Reply: "+reply);
        
        List <Ingresso> ingresso = userJson.IngressoListFromGson(reply);
        Ingresso fjIngresso = ingresso.get(0);
        
        if(fjIngresso.getCodigo() != -1){
            System.out.println("Objeto: "+fjIngresso.toString());
            
            System.out.println("Entre com os novos dados do evento!\n");
            
            String nome;
            int quantidade;
            String horarioInicio;
            String horarioTermino;
            String data;
            String endereco;
            double preco;
            
            System.out.println("Entre com o nome do evento: ");
            nome = entrada.nextLine();
            System.out.println("Entre com a quantidade de ingressos disponiveis: ");
            quantidade = Integer.parseInt(entrada.nextLine());
            System.out.println("Entre com o horario de inicio do evento: ");
            horarioInicio = entrada.nextLine();
            System.out.println("Entre com o horario de termino do evento: ");
            horarioTermino = entrada.nextLine();
            System.out.println("Entre com a data do evento: ");
            data = entrada.nextLine();
            System.out.println("Entre com o endereco do local do evento");
            endereco = entrada.nextLine();
            System.out.println("Entre com o valor do ingresso: ");
            preco = Double.parseDouble(entrada.nextLine());
            
            Ingresso ingressoAtualizar = new Ingresso(fjIngresso.getCodigo() ,nome, quantidade, horarioInicio, horarioTermino, data, endereco, preco);
            String request = userJson.IngressoToGson(ingressoAtualizar);
            
            String jatualizado = client.doOperation("admController", 5, request);
            
            Ingresso atualizado = userJson.IngressoFromGson(jatualizado);
            System.out.println(atualizado.toString());
        }
        
        else{
            System.out.println("Ingresso nao encontrado!");
        }
    }

    //methodId = 6
    public void removerIngresso(Client client) {
        int codigo;
        
        System.out.println("Entre com o codigo do evento que voce quer remover: ");
        codigo = Integer.parseInt(entrada.nextLine());
        
        String query = "{\"query\":\"codigo = " + codigo + "\"}";
        
        String reply = client.doOperation("admController", 4, query);
        List <Ingresso> ingresso = userJson.IngressoListFromGson(reply);
        Ingresso fjIngresso = ingresso.get(0);
        
        if(fjIngresso.getCodigo() != -1){
            
            System.out.println("Evento encontrado:\n");
            System.out.println(fjIngresso.toString());
            
            int confirmar = 0;
            while(confirmar < 1 || confirmar > 2){
                System.out.println("Deseja realmente confirmar a remocao? ");
                System.out.println("[1] Confirmar\n[2] Nao");
                confirmar = Integer.parseInt(entrada.nextLine());

                if(confirmar < 1 || confirmar > 2){
                    System.out.println("Opcao invalida");
                }
            }
            
            if(confirmar == 1){
                Ingresso deleteIngresso = new Ingresso(codigo);
                String jDeleteIngresso = userJson.IngressoToGson(deleteIngresso);
                String result = client.doOperation("admController", 6, jDeleteIngresso);
                Ingresso confirma = userJson.IngressoFromGson(result);
                
                if(confirma.getCodigo() == -1){
                    System.out.println("Evento removido com sucesso!");
                }
                else{
                    System.out.println("Erro ao remover evento!");
                }
            }
            else{
                System.out.println("Remocao cancelada!");
            }
            return;
        }
        System.out.println("Codigo invalido!");
    }

    //methodId = 7
    public void cadastrarUsuario(Client client) {
        String cpf;
        String username;
        String password;
        String category;
        int opcaoCategory = 0;
        
        System.out.println("Entre com o cpf do novo usuario: ");
        cpf = entrada.nextLine();
        System.out.println("Entre com o username do novo usuario: ");
        username = entrada.nextLine();
        System.out.println("Entre com a senha do novo usuario: ");
        password = entrada.nextLine();
        
        while(opcaoCategory < 1 || opcaoCategory > 2){
            System.out.println("Escolha uma categoria para o novo usuario:");
            System.out.println("[1] Administrador\n[2] Vendedor");
            opcaoCategory = Integer.parseInt(entrada.nextLine());
            
            if(opcaoCategory < 1 || opcaoCategory > 2){
                System.out.println("Opcao invalida");
            }
        }
        
        if(opcaoCategory == 1) category = "adm";
        else category = "vend";
        
        
        UserAtt user = new UserAtt(cpf, username, password, category);
        String jUser = userJson.UserAttToGson(user);
        String jReply = client.doOperation("admController", 7, jUser);
        UserAtt reply = userJson.UserAttFromGson(jReply);
        
        if(reply.getCpf().equals("0")) System.out.println("Erro! CPF ja cadastrado no sistema");
        else System.out.println("Usuario cadastrado com sucesso!");
    }

    //methodId = 8
    public void removerUsuario(Client client) {
        String cpf;
        int confirmar = 0;
        
        System.out.println("Entre com o CPF da pessoa que voce quer remover: ");
        cpf = entrada.nextLine();
        
        UserAtt user = new UserAtt(cpf);
        String jUser = userJson.UserAttToGson(user);
        String jReply = client.doOperation("admController", 0, jUser);
        UserAtt reply = userJson.UserAttFromGson(jReply);
        
        if(reply.getCpf().equals("-1")) System.out.println("Erro! CPF nao cadastrado no sistema");
        
        else{
            System.out.println(reply.toString());
            
            while(confirmar < 1 || confirmar > 2){
                System.out.println("Deseja realmente confirmar a remocao? ");
                System.out.println("[1] Confirmar\n[2] Nao");
                confirmar = Integer.parseInt(entrada.nextLine());

                if(confirmar < 1 || confirmar > 2){
                    System.out.println("Opcao invalida");
                }
            }
            
            if(confirmar == 1){
                UserAtt userRemover = new UserAtt(cpf);
                String jUserRemover = userJson.UserAttToGson(userRemover);
                String jReplyRemover = client.doOperation("admController", 8, jUserRemover);
                UserAtt replyRemover = userJson.UserAttFromGson(jReplyRemover);

                
                if(replyRemover.getCpf().equals("removido"))    System.out.println("Removido com sucesso!");
                else    System.out.println("Erro ao remover usuario");
                
                return;
            }
            System.out.println("Remocao cancelada!");
        }
    }

    //methodId = 9
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
        String jReply = client.doOperation("admController", 11, jUser);
        UserAtt reply = userJson.UserAttFromGson(jReply);
        
        if(reply.getCpf().equals("-1")) System.out.println("Erro! CPF e/ou senha antiga invalido");
        else{
            UserAtt passAlterar = new UserAtt(cpf, senhaAntiga, novaSenha);
            String jPassAlterar = userJson.UserAttToGson(passAlterar);
            String jReplyAlterar = client.doOperation("admController", 9, jPassAlterar);
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
