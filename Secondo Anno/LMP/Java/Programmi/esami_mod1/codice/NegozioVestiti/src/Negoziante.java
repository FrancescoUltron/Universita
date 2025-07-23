import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Negoziante {
    private HashSet<Prodotto> listaProdotti = new HashSet<>();
    private ArrayList<Acquisto> listaAcquisti = new ArrayList<>();

    public Acquisto createAcquisto(Cliente c, HashMap<Prodotto, Integer> prodotti, Date data) throws ProdottoNotFoundException {
        for (Prodotto p :
                prodotti.keySet()) {
            if (! listaProdotti.contains(p)) {
                throw new ProdottoNotFoundException(p);
            }
            p.incrementNroVolteAcquistato();

        }
        Acquisto a = new Acquisto(c, data, prodotti);
        listaAcquisti.add(a);
        return a;
    }

    public ArrayList<Acquisto> prodottiVendutiInDataCliente(Date data, Cliente c) {
        ArrayList<Acquisto> acquisti = new ArrayList<>();
        for (Acquisto a :
                listaAcquisti) {
            if (a.getData().equals(data) && a.getAcquirente().equals(c)) {
                acquisti.add(a);
            }
        }
        return acquisti;
    }

    public int quanteVolteVenduto(String name) {
        int volte = 0;
        Prodotto prodotto = null;
        for (Prodotto p:
             listaProdotti) {
            if(p.getNome().equals(name)) {
                prodotto = p;
            }
        }
        for (Acquisto a :
                listaAcquisti) {
            volte += a.getListaProdottoAcquistati().get(prodotto);
        }
        return volte;
    }

    public void fornitoreVincente() {
        HashMap<String,Integer> fornitori = new HashMap<>();
        HashMap<String,Integer> fornitoriProd = new HashMap<>();
        for (Prodotto p:
             listaProdotti) {
            if(! fornitori.containsKey(p.getFornitore()))
                fornitori.put(p.getFornitore(),p.getNroVolteAcquistato());
            else
                fornitori.put(p.getFornitore(),(fornitori.get(p.getFornitore()) + p.getNroVolteAcquistato()));
        }
        for (Prodotto p:
                listaProdotti) {
            if(! fornitoriProd.containsKey(p.getFornitore()))
                fornitoriProd.put(p.getFornitore(),1);
            else
                fornitori.put(p.getFornitore(),(fornitori.get(p.getFornitore()) + 1));
        }
        int fornitoreTopValue = 0;
        String fornitoreTop = null;
        for (String f :
                fornitori.keySet()) {
            int value = fornitori.get(f)/ fornitoriProd.get(f);
            if(value > fornitoreTopValue) {
                fornitoreTopValue = value;
                fornitoreTop = f;
            }
        }
        System.out.println("Il fornitore che vende più prodotti è " +  fornitoreTop);
    }
}
