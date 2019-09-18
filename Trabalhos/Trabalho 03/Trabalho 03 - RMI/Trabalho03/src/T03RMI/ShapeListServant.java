package T03RMI;

import java.rmi.RemoteException;
import java.util.Vector;

public class ShapeListServant implements ShapeList {

    private Vector clientList;
    
    public ShapeListServant(){
        clientList = new Vector();
    }

    public boolean cadastrar(Pessoa pessoa) throws RemoteException {
        if(consultar(pessoa.getCpf()) == null){
            Shape s = new ShapeServant(pessoa);
            clientList.addElement(s);
            return true;
        }
        
        return false;
    }

    public Vector listar() throws RemoteException {
        return clientList;
    }

    public Pessoa consultar(String cpf) throws RemoteException {
        Pessoa pessoa = new Pessoa(cpf);
        for (int i = 0; i < clientList.size(); i++) {
            Pessoa p = ((Shape) clientList.elementAt(i)).getAll();
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }

        return null;
    }

    public boolean atualizar(Pessoa pessoa) throws RemoteException {
        for (int i = 0; i < clientList.size(); i++) {
            Pessoa p = ((Shape) clientList.elementAt(i)).getAll();
            if (p.getCpf().equals(pessoa.getCpf())) {
                p.setNome(pessoa.getNome());
                p.setEmail(pessoa.getEmail());
                p.setTelefone(pessoa.getTelefone());
                return true;
            }
        }

        return false;
    }

    public boolean apagar(Pessoa pessoa) throws RemoteException {
        for (int i = 0; i < clientList.size(); i++) {
            Pessoa p = ((Shape) clientList.elementAt(i)).getAll();
            if (p.getCpf().equals(pessoa.getCpf())) {
                clientList.remove(i);
                return true;
            }
        }
        return false;
    }
}
