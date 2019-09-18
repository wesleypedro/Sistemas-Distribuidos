package T03RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ShapeServant extends UnicastRemoteObject implements Shape {

    Pessoa pessoa;

    public ShapeServant(Pessoa p) throws RemoteException {
        pessoa = p;
    }

    public Pessoa getAll() throws RemoteException {
        return pessoa;
    }
}
