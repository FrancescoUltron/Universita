import java.util.ArrayList;
import java.util.HashMap;

public class Gestore {
    private HashMap<String, Prodotto> listaProdotti = new HashMap<>();
    private int costoGiornalieroManodopera = 10;
    private double fattoreGuadagno = 0.2;

    public void insertProdotto(String identificativo, String nome, ArrayList<Componente> listaComponenti, int tempoRealizzazione) {
        Prodotto p = new Prodotto(identificativo,nome,listaComponenti,tempoRealizzazione);
        p.setCostoManodopera(calcolaCostoManodopera(p));
        p.setCostoProduzione(calcolaCostoProduzione(p));
        p.setPrezzo(calcolaPrezzo(p));
        listaProdotti.put(identificativo,p);
    }

    private int calcolaCostoManodopera(Prodotto p) {
        return p.getTempoRealizzazione()*costoGiornalieroManodopera;
    }

    private int calcolaCostoProduzione(Prodotto p) {
        int costo = p.getCostoManodopera();
        for (Componente c :
                p.getListaComponenti()) {
            costo += c.getCosto();
        }
        return costo;
    }

    private double calcolaPrezzo(Prodotto p) {
        double guadagno = p.getCostoProduzione()*fattoreGuadagno;
        return p.getCostoProduzione() + guadagno;
    }

    public void setFattoreGuadagno(double fattoreGuadagno) {
        this.fattoreGuadagno = fattoreGuadagno;
    }

    public void setCostoGiornalieroManodopera(int costoGiornalieroManodopera) {
        this.costoGiornalieroManodopera = costoGiornalieroManodopera;
    }

    public void rankingProdotti() {
        for (Prodotto p :
                listaProdotti.values()) {
            double ranking = (p.getPrezzo()-p.getCostoProduzione())/(p.getTempoRealizzazione()+p.getTempoStimatoOrdinazioneComponenti());
            System.out.println("Prodotto: " + p.getIdentificativo() + ", ranking: " + ranking);
        }
    }

    public void showProdotti() {
        for (Prodotto p :
                listaProdotti.values()) {
            System.out.println(p.toString());
        }
    }

}
