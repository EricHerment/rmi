import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerDistant extends UnicastRemoteObject implements Server {

    private List<Client> clients;
    private static final String FILENAME = "src/test.txt";

    protected ServerDistant() throws RemoteException {
        clients = new ArrayList<>();
    }

    @Override
    public void subscribe(Client client) throws RemoteException {
        this.clients.add(client);
    }

    private void notifyAllClients(String line) {
        for (Client c : clients) {
            try {
                c.notify(line);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {


        BufferedReader br = null;
        FileReader fr = null;


        try {
            ServerDistant server = new ServerDistant();
            Naming.rebind("rmi://localhost:2001/serverDistant", server);

            try {

                fr = new FileReader(FILENAME);
                br = new BufferedReader(fr);

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    System.out.println(sCurrentLine);
                    Thread.sleep(1000);
                    server.notifyAllClients(sCurrentLine);
                }

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                try {

                    if (br != null)
                        br.close();

                    if (fr != null)
                        fr.close();

                } catch (IOException ex) {

                    ex.printStackTrace();

                }

            }

            /*while (true) {
                Thread.sleep(1000);
                server.notifyAllClients();
            }*/
        } catch (MalformedURLException | RemoteException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
