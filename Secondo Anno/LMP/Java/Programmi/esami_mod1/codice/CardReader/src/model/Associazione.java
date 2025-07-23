package model;

public class Associazione extends Attivita {
    Scopo scopo;
    public Associazione(int inAttivitaDal, String sede, Scopo scopo) throws AnnoOutOfBoundException {
        super(inAttivitaDal, sede);
        this.scopo = scopo;

    }
    public static enum Scopo {
        RICREATIVO,
        CULTURALE,
        VOLONTARIATO
    }

    public Scopo getScopo() {
        return scopo;
    }

    @Override
    public String toString() {
        return "Associazione{" +
                "scopo=" + scopo +
                "} " + super.toString();
    }
}
