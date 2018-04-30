package assignment5.exercise3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FacServiceImpl extends UnicastRemoteObject implements IFacService {

    FacServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int fac(int number) throws RemoteException {
        if (number == 1)
            return 1;
        return fac(number - 1) * number;

    }
}
