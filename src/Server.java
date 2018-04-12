import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {

    void subscribe(Client client) throws RemoteException;
}
