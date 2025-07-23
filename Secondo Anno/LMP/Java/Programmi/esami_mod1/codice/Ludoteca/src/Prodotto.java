public abstract class Prodotto {
    private final String titolo;
    private final String autore;
    private final String casaEditrice;
    private final int annoPubblicazione;

    private boolean inNoleggio = false;

    public Prodotto(String titolo, String autore, String casaEditrice, int annoPubblicazione) {
        this.titolo = titolo;
        this.autore = autore;
        this.casaEditrice = casaEditrice;
        this.annoPubblicazione = annoPubblicazione;
    }

    public boolean isInNoleggio() {
        return inNoleggio;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public String getCasaEditrice() {
        return casaEditrice;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setInNoleggio(boolean inNoleggio) {
        this.inNoleggio = inNoleggio;
    }
}
