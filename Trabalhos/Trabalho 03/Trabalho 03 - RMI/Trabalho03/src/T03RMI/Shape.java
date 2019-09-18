package T03RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Shape extends Remote {

    Pessoa getAll() throws RemoteException;
}
