package T03RMI;

import java.awt.BorderLayout;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Vector;

public class Client {

    public static void main(String[] args) throws RemoteException {
        Scanner entrada = new Scanner(System.in);
        boolean control = true;
        int option;

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        } else {
            System.out.println("Already has a security manager, so cant set RMI");
        }
        ShapeList aShapeList = null;

        try {
            aShapeList = (ShapeList) Naming.lookup("//localhost/CRUD");
            while (true) {
                System.out.println("\n\n");
                System.out.println("Entre com uma opção:");
                System.out.println("[1] Cadastrar pessoas");
                System.out.println("[2] Listar pessoas");
                System.out.println("[3] Atualizar pessoa");
                System.out.println("[4] Apagar pessoa");
                System.out.println("[5] Sair");
                option = Integer.parseInt(entrada.nextLine());
                System.out.println();

                Vector clientList = aShapeList.listar();

                switch (option) {
                    case 1:
                        String nome,
                         cpf,
                         email,
                         telefone;
                        System.out.println("Entre com o nome: ");
                        nome = entrada.nextLine();
                        System.out.println("Entre com o cpf: ");
                        cpf = entrada.nextLine();
                        System.out.println("Entre com o email: ");
                        email = entrada.nextLine();
                        System.out.println("Entre com o telefone: ");
                        telefone = entrada.nextLine();

                        Pessoa pessoa = new Pessoa(nome, cpf, email, telefone);
                        System.out.println("Objeto pessoa criada!");
                        if(aShapeList.cadastrar(pessoa)){
                            System.out.println("Pessoa cadastrada com sucesso!");
                            break;
                        }
                        System.out.println("Não foi possível cadastrar,\n"
                                + "CPF já consta no sistema!");
                        break;

                    case 2:
                        if(clientList.isEmpty()){
                            System.out.println("Sem pessoas cadastradas!");
                            break;
                        }
                        for (int i = 0; i < clientList.size(); i++) {
                            Pessoa p = ((Shape) clientList.elementAt(i)).getAll();
                            p.print();
                        }
                        break;

                    case 3:
                        System.out.println("Entre com o CPF da pessoa a ser alterada: ");
                        cpf = entrada.nextLine();
                        Pessoa atualizarRetornoPessoa = new Pessoa();
                        atualizarRetornoPessoa = aShapeList.consultar(cpf);
                        if (atualizarRetornoPessoa != null) {
                            System.out.println("Entre com o nome: ");
                            nome = entrada.nextLine();
                            System.out.println("Entre com o email: ");
                            email = entrada.nextLine();
                            System.out.println("Entre com o telefone: ");
                            telefone = entrada.nextLine();

                            Pessoa pessoaAtualizar = new Pessoa(nome, cpf, email, telefone);
                            boolean retornoAtualizar = aShapeList.atualizar(pessoaAtualizar);

                            if (retornoAtualizar) {
                                System.out.println("Pessoa atualizada com sucesso!");
                            } else {
                                System.out.println("Erro, não foi possível atualizar a pessoa.");
                            }
                            break;
                        }

                        System.out.println("Pessoa não encontrada!");
                        break;

                    case 4:
                        System.out.println("Entre com o CPF da pessoa a ser apagada: ");
                        cpf = entrada.nextLine();
                        Pessoa deletarRetornoPessoa = new Pessoa();
                        deletarRetornoPessoa = aShapeList.consultar(cpf);
                        if (deletarRetornoPessoa == null) {
                            System.out.println("Pessoa não consta no sistema!");
                        } else {
                            boolean retornoApagar;
                            Pessoa pessoaApagar = new Pessoa(cpf);
                            retornoApagar = aShapeList.apagar(pessoaApagar);

                            if (retornoApagar) {
                                System.out.println("Pessoa apagada com sucesso!");
                                break;
                            }

                            System.out.println("Erro, não foi possível apagar a pessoa.");
                        }

                        break;

                    case 5:
                        System.out.println("Bye");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opcão inválida");
                }
            }

        } catch (RemoteException e) {
            System.out.println("All people: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lookup: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
