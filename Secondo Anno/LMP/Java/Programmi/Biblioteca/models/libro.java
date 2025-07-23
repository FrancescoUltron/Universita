package models;
// Libro.java

public class libro extends Supporto {
    
    private int numeroPagine;

    public libro(String titolo, String entePubblicante, int annoPubblicazione, int numeroPagine) {
        super(titolo, entePubblicante, annoPubblicazione);
        this.numeroPagine = numeroPagine;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

}
