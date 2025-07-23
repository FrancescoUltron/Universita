package model;

public class Ristorante extends AttivitaCommerciale {
    private Categoria categoria;
    public Ristorante(int inAttivitaDal, String sede, String partitaIva, Categoria categoria) throws AnnoOutOfBoundException {
        super(inAttivitaDal, sede, partitaIva);
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public static enum Categoria {
        PIZZERIA,ITALIANO,ETNICO
    }

}
