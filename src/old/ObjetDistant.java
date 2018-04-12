package old;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjetDistant extends UnicastRemoteObject implements Distante {

    public ObjetDistant() throws RemoteException {}

    @Override
    public void echo() {
        System.out.println("lol");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resultat printInt(int number) {
        return new Resultat(number);
    }

    public static void main(String[] args) {
        try {
            ObjetDistant objetDistant = new ObjetDistant();
            Naming.rebind("rmi://localhost:2001/objetDistant", objetDistant);
        } catch (MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
