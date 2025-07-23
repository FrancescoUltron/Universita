public class Componente {
    private String nome;
    private String provenienza;
    private int tempoOrdinazione;
    private int costo;

    private boolean privato = false;

    public Componente(String nome, String provenienza, int tempoOrdinazione, int costo) {
        this.nome = nome;
        this.provenienza = provenienza;
        this.tempoOrdinazione = tempoOrdinazione;
        this.costo = costo;
        if (provenienza.equals("locale"))
            privato = true;
    }

    public String getNome() {
        return nome;
    }

    public String getProvenienza() {
        return provenienza;
    }

    public int getTempoOrdinazione() {
        return tempoOrdinazione;
    }

    public int getCosto() {
        return costo;
    }

    @Override
    public String toString() {
        if(privato == true) return "";
        else {
            return "Componente{" +
                    "nome='" + nome + '\'' +
                    ", provenienza='" + provenienza + '\'' +
                    '}';
        }
    }
}
