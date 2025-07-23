public class Professionista extends ClienteRegistrato {
    private String nome;

    public Professionista(String partitaIva, String indirizzo, String nome) {
        super(partitaIva, indirizzo);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
