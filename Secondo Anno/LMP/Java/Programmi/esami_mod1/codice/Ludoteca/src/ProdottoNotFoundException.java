public class ProdottoNotFoundException extends Exception {
    public ProdottoNotFoundException(String titolo, String autore) {
        super("Il prodotto con titolo: " + titolo + ", autore: " + autore + " non è presente.");
    }
}
