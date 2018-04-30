package assignment5.exercise3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFacService extends Remote {

    String IFAC_SERVICE_NAME = "FacService";

    int fac(int number) throws RemoteException;
}
