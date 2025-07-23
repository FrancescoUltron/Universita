public class Gioco extends Prodotto {
    private final int durataMedia;

    public Gioco(String titolo, String autore, String casaEditrice, int annoPubblicazione, int durataMedia) {
        super(titolo, autore, casaEditrice, annoPubblicazione);
        this.durataMedia = durataMedia;
    }

    public int getDurataMedia() {
        return durataMedia;
    }
}
