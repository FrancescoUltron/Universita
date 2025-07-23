public class Dipendente {
    private String nome;
    private String cognome;
    private Date data_nascita;
    private Date data_assunzione;
    private String matricola;
    private Dipartimento dipartimento;
    private String mansione;
    private int livello;
    private Dipendente superiore;


    public Dipendente(String nome, String cognome, Date data_nascita, Date data_assunzione) {
        this.nome = nome;
        this.cognome = cognome;
        this.data_nascita = data_nascita;
        this.data_assunzione = data_assunzione;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public void setDipartimento(Dipartimento dipartimento) {
        this.dipartimento = dipartimento;
    }

    public void setMansione(String mansione) {
        this.mansione = mansione;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    public void setSuperiore(Dipendente superiore) {
        this.superiore = superiore;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Date getData_nascita() {
        return data_nascita;
    }

    public Date getData_assunzione() {
        return data_assunzione;
    }

    public String getMatricola() {
        return matricola;
    }

    public Dipartimento getDipartimento() {
        return dipartimento;
    }

    public String getMansione() {
        return mansione;
    }

    public int getLivello() {
        return livello;
    }

    public Dipendente getSuperiore() {
        return superiore;
    }

    static enum Dipartimento {
        MARKETING,RISORSE_UMANE,SVILUPPO
    }

    @Override
    public String toString() {
        return "Dipendente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data_nascita=" + data_nascita +
                ", data_assunzione=" + data_assunzione +
                ", matricola='" + matricola + '\'' +
                ", dipartimento=" + dipartimento +
                ", mansione='" + mansione + '\'' +
                ", livello=" + livello +
                ", superiore=" + superiore +
                '}';
    }
}
