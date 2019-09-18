package T03RMI;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try {
            ShapeList aShapeList = new ShapeListServant();
            ShapeList teste = (ShapeList) UnicastRemoteObject.exportObject(aShapeList, 0);
            Naming.rebind("CRUD", aShapeList);
            System.out.println("Servidor de CRUD está rodando");
        } catch (Exception e) {
            System.out.println("CRD server main " + e.getMessage());
        }
    }
}
