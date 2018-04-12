import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientDistant extends UnicastRemoteObject implements Client {

    protected ClientDistant() throws RemoteException {
    }

    @Override
    public void notify(String s) throws RemoteException {
        System.out.println(s);
    }

    public static void main(String[] args) {
        try {
            Client client = new ClientDistant();
            Server server = (Server) Naming.lookup("rmi://localhost:2001/serverDistant");
            server.subscribe(client);

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
