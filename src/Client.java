import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) {

        try {
            Distante distant = (Distante) Naming.lookup("rmi://localhost:2001/objetDistant");
            System.out.println("Salut");
            distant.echo();
            System.out.println("Salut");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
