package T03RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ShapeList extends Remote {

    boolean cadastrar(Pessoa pessoa) throws RemoteException;

    Vector listar() throws RemoteException;

    Pessoa consultar(String cpf) throws RemoteException;

    boolean atualizar(Pessoa pessoa) throws RemoteException;

    boolean apagar(Pessoa pessoa) throws RemoteException;
}
