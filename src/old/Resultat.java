package old;

import java.io.Serializable;

public class Resultat implements Serializable {

    private int number;
    public Resultat(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.number;
    }
}
