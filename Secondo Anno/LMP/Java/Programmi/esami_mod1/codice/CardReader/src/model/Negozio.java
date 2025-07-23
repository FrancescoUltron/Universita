package model;

public class Negozio extends AttivitaCommerciale {

    private String merceVenduta;

    public Negozio(int inAttivitaDal, String sede, String partitaIva, String merceVenduta) throws AnnoOutOfBoundException {
        super(inAttivitaDal, sede, partitaIva);
        this.merceVenduta = merceVenduta;
    }

    public String getMerceVenduta() {
        return merceVenduta;
    }

    @Override
    public String toString() {
        return "Negozio{" +
                "merceVenduta='" + merceVenduta + '\'' +
                "} " + super.toString();
    }
}
