import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerDistant extends UnicastRemoteObject implements Server {

    private List<Client> clients;

    protected ServerDistant() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override
    public void subscribe(Client client) throws RemoteException {
        this.clients.add(client);
    }

    private void notifyAllClients() {
        for (Client c : clients) {
            try {
                c.notify("Test");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServerDistant server = new ServerDistant();
            Naming.rebind("rmi://localhost:2001/serverDistant", server);
            while (true) {
                Thread.sleep(1000);
                server.notifyAllClients();
            }
        } catch (MalformedURLException | RemoteException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
