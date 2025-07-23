import java.util.ArrayList;

public class Prodotto {
    private String identificativo;
    private String nome;
    private int tempoRealizzazione;
    private ArrayList<Componente> listaComponenti;
    private int nroComponenti;
    private int tempoStimatoOrdinazioneComponenti;

    private int costoProduzione;
    private int costoManodopera;
    private double prezzo;

    public Prodotto(String identificativo, String nome, ArrayList<Componente> listaComponenti, int tempoRealizzazione) {
        this.identificativo = identificativo;
        this.nome = nome;
        this.listaComponenti = listaComponenti;
        this.tempoRealizzazione = tempoRealizzazione;
        this.nroComponenti = listaComponenti.size();
        this.tempoStimatoOrdinazioneComponenti = setTempoOrdinazione();

    }

    public String getIdentificativo() {
        return identificativo;
    }

    public String getNome() {
        return nome;
    }

    public int getCostoProduzione() {
        return costoProduzione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public ArrayList<Componente> getListaComponenti() {
        return listaComponenti;
    }

    public int getTempoRealizzazione() {
        return tempoRealizzazione;
    }

    public int getCostoManodopera() {
        return costoManodopera;
    }

    public int getTempoStimatoOrdinazioneComponenti() {
        return tempoStimatoOrdinazioneComponenti;
    }

    private int setTempoOrdinazione() {
        int maxTempo = 0;
        for (Componente c :
                listaComponenti) {
            int tempo = c.getTempoOrdinazione();
            if (tempo > maxTempo) maxTempo = tempo;
        }
        return maxTempo;
    }

    public void setCostoManodopera(int costoManodopera) {
        this.costoManodopera = costoManodopera;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setCostoProduzione(int costoProduzione) {
        this.costoProduzione = costoProduzione;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "identificativo='" + identificativo + '\'' +
                ", nome='" + nome + '\'' +
                ", listaComponenti=" + listaComponenti +
                ", prezzo=" + prezzo +
                '}';
    }
}
