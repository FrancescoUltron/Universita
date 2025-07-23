public class Prodotto {
    private String nome;
    private int prezzo;
    private Categoria categoria;
    private Destinatario destinatario;

    public Prodotto(String nome, int prezzo, Categoria categoria, Destinatario destinatario) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.categoria = categoria;
        this.destinatario = destinatario;
    }

    public String getNome() {
        return nome;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Destinatario getDestinatario() {
        return destinatario;
    }

    static enum Categoria {
        calzatura,maglietta,intimo
    }

    static enum Destinatario {
        uomo,donna,unisex
    }
}

