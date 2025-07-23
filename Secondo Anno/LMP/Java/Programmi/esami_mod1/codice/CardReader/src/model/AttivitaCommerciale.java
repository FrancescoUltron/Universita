package model;

public abstract class AttivitaCommerciale extends Attivita {
    public String partitaIva;
    public AttivitaCommerciale(int inAttivitaDal, String sede, String partitaIva) throws AnnoOutOfBoundException {
        super(inAttivitaDal, sede);
        this.partitaIva = partitaIva;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    @Override
    public String toString() {
        return "Societa{" +
                "partitaIva='" + partitaIva + '\'' +
                "} " + super.toString();
    }
}
