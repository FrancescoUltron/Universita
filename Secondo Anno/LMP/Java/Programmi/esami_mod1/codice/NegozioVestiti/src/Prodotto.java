public class Prodotto {
    private String nome;
    private Categoria categoria;
    private Destinatario destinatario;
    private String fornitore;
    private int prezzo;

    private int nroVolteAcquistato = 0;

    public Prodotto(String nome, Categoria categoria, Destinatario destinatario, String fornitore, int prezzo) {
        this.nome = nome;
        this.categoria = categoria;
        this.destinatario = destinatario;
        this.fornitore = fornitore;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    public String getFornitore() {
        return fornitore;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void incrementNroVolteAcquistato() {
        this.nroVolteAcquistato++;
    }

    public int getNroVolteAcquistato() {
        return nroVolteAcquistato;
    }

    static enum Categoria {
        calzatura, maglietta, intimo
    }

    static enum Destinatario {
        uomo,donna,unisex
    }
}
