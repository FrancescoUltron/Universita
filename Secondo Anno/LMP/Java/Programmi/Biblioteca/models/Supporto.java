package models;
import java.util.ArrayList;
import java.time.LocalDate;

public abstract class Supporto {

    private String titolo;
    private String entePubblicante;
    private int annoPubblicazione;
    private ArrayList<prestiti> seqPrestiti;

    public Supporto(String titolo, String entePubblicante, int annoPubblicazione) {
        this.titolo = titolo;
        this.entePubblicante = entePubblicante;
        this.annoPubblicazione = annoPubblicazione;
        this.seqPrestiti = new ArrayList<>();
    }

    public void addPrestito(LocalDate dataInizio, LocalDate dataConsegna, LocalDate dataConsegnaPrevista, String nome, String cognome, double prezzo){
        this.seqPrestiti.add(new prestiti(dataInizio, dataConsegna, dataConsegnaPrevista, nome, cognome, prezzo));
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setEntePubblicante(String entePubblicante) {
        this.entePubblicante = entePubblicante;
    }

    public void setAnno(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public void setSeqPrestiti(ArrayList<prestiti> seqPrestiti) {
        this.seqPrestiti = seqPrestiti;
    }

    public String getTitolo() {
        return titolo;
    }


    public String getEntePubblicante() {
        return entePubblicante;
    }


    public int getAnno() {
        return annoPubblicazione;
    }


    public ArrayList<prestiti> getSeqPrestiti() {
        
        return new ArrayList<>(seqPrestiti);
    }
}
