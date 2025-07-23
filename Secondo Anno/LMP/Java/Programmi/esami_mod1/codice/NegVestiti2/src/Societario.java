public class Societario extends ClienteRegistrato {
    private String ragioneSociale;

    public Societario(String partitaIva, String indirizzo, String ragioneSociale) {
        super(partitaIva, indirizzo);
        this.ragioneSociale = ragioneSociale;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }
}
