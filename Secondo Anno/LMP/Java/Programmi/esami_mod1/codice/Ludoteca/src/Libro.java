public class Libro extends Prodotto{
    private final int numeroPagine;

    public Libro(String titolo, String autore, String casaEditrice, int annoPubblicazione, int numeroPagine) {
        super(titolo, autore, casaEditrice, annoPubblicazione);
        this.numeroPagine = numeroPagine;
    }
}
