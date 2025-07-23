package model;

public abstract class Attivita {
    private int inAttivitaDal;
    private String sede;

    public Attivita(int inAttivitaDal, String sede) throws AnnoOutOfBoundException {
        if (inAttivitaDal < 1800 || inAttivitaDal > 2022) {
            throw new AnnoOutOfBoundException(inAttivitaDal);
        }
        this.inAttivitaDal = inAttivitaDal;
        this.sede = sede;
    }

    public int getInAttivitaDal() {
        return inAttivitaDal;
    }

    public String getSede() {
        return sede;
    }

    @Override
    public String toString() {
        return "AttivitaImpl{" +
                "inAttivitaDal=" + inAttivitaDal +
                ", sede='" + sede + '\'' +
                '}';
    }
}
