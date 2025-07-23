import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Negoziante {
    private HashSet<ClienteRegistrato> listaClienti = new HashSet<>();
    private ArrayList<Acquisto> listaAcquisti = new ArrayList<>();
    private HashSet<Prodotto> listaProdotti = new HashSet<>();

    public void insertProdotto(String nome, int prezzo, Prodotto.Categoria categoria, Prodotto.Destinatario destinatario) {
        Prodotto p = new Prodotto(nome,prezzo,categoria,destinatario);
        listaProdotti.add(p);
    }

    public void insertAcquisto(Cliente clienteAcquisto, Date data, HashMap<Prodotto, Integer> listaProdotti) {
        Acquisto a = new Acquisto(clienteAcquisto,data,listaProdotti);
        if (clienteAcquisto instanceof ClienteRegistrato) {
            listaClienti.add((ClienteRegistrato) clienteAcquisto);
        }
        listaAcquisti.add(a);
    }

    public HashMap<Prodotto, Integer> tuttiProdottiVendutiInData(Date data) {
        HashMap<Prodotto,Integer> listaProd = new HashMap<>();
        for (Acquisto a :
                listaAcquisti) {
            if (a.getData().equals(data)) {
                for (Prodotto p :
                        a.getListaProdotti().keySet()) {
                    if (a.getListaProdotti().containsKey(p)) {
                        listaProd.put(p,(a.getListaProdotti().get(p) + listaProd.get(p)));
                    }
                    listaProd.put(p,a.getListaProdotti().get(p));
                }
            }
        }
        return listaProd;
    }

    public int clientiCompratoriInMeseAnno(int mese, int anno) {
        int nroClienti = 0;
        ArrayList<Cliente> listaClienti = new ArrayList<>();
        for (Acquisto a :
                listaAcquisti) {
            if(a.getData().getYear() == anno && a.getData().getMonth() == mese) {
                if (! listaClienti.contains(a.getClienteAcquisto())) {
                    listaClienti.add(a.getClienteAcquisto());
                }
            }
        }
        nroClienti = listaClienti.size();
        return nroClienti;
    }

    public int quantoVenduto(String nome) {
        Prodotto prod = null;
        int volteVenduto = 0;
        for (Prodotto p : listaProdotti) {
            if(p.getNome().equals(nome)) prod = p;
        }
        for (Acquisto a : listaAcquisti) {
             volteVenduto += a.getListaProdotti().get(prod);
        }
        return volteVenduto;
    }

    public void consumatoDa(int mese, int anno, String nome) {
        Prodotto prod = null;
        int nroVenduti = 0;
        int nroPrivati = 0;
        int nroSocetari = 0;
        int nroProf = 0;

        for (Prodotto p :
                listaProdotti) {
            if(p.getNome().equals(nome)) prod = p;
        }
        for (Acquisto a :
                listaAcquisti) {
            if(a.getData().getYear() == anno && a.getData().getMonth() == mese) {
                if(a.getClienteAcquisto() instanceof Cliente) {
                    for (Prodotto p:
                            a.getListaProdotti().keySet()) {
                        if (prod.equals(p)) {
                            nroVenduti++;
                            nroPrivati++;
                        }
                    }
                } else if (a.getClienteAcquisto() instanceof Professionista) {
                    for (Prodotto p:
                            a.getListaProdotti().keySet()) {
                        if (prod.equals(p)) {
                            nroVenduti++;
                            nroProf++;
                        }
                    }
                } else {
                    for (Prodotto p:
                            a.getListaProdotti().keySet()) {
                        if (prod.equals(p)) {
                            nroVenduti++;
                            nroSocetari++;
                        }
                    }
                }
            }
        }
       int percentualePrivati = (nroPrivati*100)/nroVenduti;
       int percentualeSocetari = (nroSocetari*100)/nroVenduti;
       int percentualeProf = (nroProf*100)/nroVenduti;
        System.out.println("Prodotto: " +  prod.getNome()
                + ", Percentuale privati: " + percentualePrivati
                + ", percentuale socetari: " + percentualeSocetari
                + ", percentuale Professionisti: " + percentualeProf);
    }


}
