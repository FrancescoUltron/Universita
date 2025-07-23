public abstract class ClienteRegistrato extends Cliente {
    private String partitaIva;
    private String indirizzo;

    public ClienteRegistrato(String partitaIva, String indirizzo) {
        this.partitaIva = partitaIva;
        this.indirizzo = indirizzo;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public String getIndirizzo() {
        return indirizzo;
    }
}
