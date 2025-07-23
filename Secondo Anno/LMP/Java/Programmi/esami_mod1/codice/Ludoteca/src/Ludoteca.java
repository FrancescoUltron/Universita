import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Ludoteca {
    ArrayList<Prodotto> prodotti = new ArrayList<>();
    ArrayList<Uso> noleggi = new ArrayList<>();

    public void insertProdotto(Prodotto prodotto) {
        prodotti.add(prodotto);
    }

    private Prodotto getProdotto(String titolo, String autore, String casaEditrice, int annoPubblicazione) throws ProdottoNotFoundException {
        Prodotto prodotto = null;
        for (Prodotto p :
                prodotti) {
            if (p.getAutore().equals(autore) && p.getTitolo().equals(titolo) && p.getAnnoPubblicazione() == annoPubblicazione && p.getCasaEditrice().equals(casaEditrice) && p.isInNoleggio() == false)
                prodotto = p;
        }
        if (prodotto == null) {
            throw new ProdottoNotFoundException(titolo,autore);
        }
        prodotto.setInNoleggio(true);
        return prodotto;
    }

    public Uso noleggiaLibro(String nome, String cognome, String titolo, String autore, String casaEditrice, int annoPubblicazione) {
        try {
            return new Uso(getProdotto(titolo, autore, casaEditrice, annoPubblicazione), new Date(), nome, cognome);
        } catch (ProdottoNotFoundException e) {
            System.out.println("Il prodotto non è disponibile!");
            return null;
        }
    }

    public Uso noleggiaGioco(String nome, String cognome, String titolo, String autore, String casaEditrice, int annoPubblicazione) {
        try {
            return new Uso(getProdotto(titolo, autore, casaEditrice, annoPubblicazione), new Date(), nome, cognome);
        } catch (ProdottoNotFoundException e) {
            System.out.println("Gioco attualmente non disponibile.");
            stimaAttesa(titolo,autore,casaEditrice,annoPubblicazione);
            return null;
        }
    }

    public void fineNoleggioProdotto(Uso uso) {
        uso.setDataUso(new Date());
        noleggi.add(uso);
        uso.getProdotto().setInNoleggio(false);

    }

    private void stimaAttesa(String titolo, String autore, String casaEditrice, int annoPubblicazione) {
        Gioco gioco = null;
        long tempo = new Date().getTime();
        int minuti = (int) TimeUnit.MILLISECONDS.toMinutes(tempo);
        int stima = 0;
        for (Prodotto p :
                prodotti) {
           if (p.getAutore().equals(autore) && p.getTitolo().equals(titolo) && p.getAnnoPubblicazione() == annoPubblicazione && p.getCasaEditrice().equals(casaEditrice) && p.isInNoleggio() == true)
               gioco = (Gioco) p;
                if ( (minuti- gioco.getDurataMedia()) < stima)
                stima = minuti - gioco.getDurataMedia();
        }
        System.out.println("La stima di attesa per il gioco " + gioco.getTitolo() + " è di " + stima + " minuti.");
    }

    public void longestNoleggio(String titolo, String autore, String casaEditrice, int annoPubblicazione) {
        long usoMax = 0;
        for (Uso u :
                noleggi) {
            if (u.getProdotto().getTitolo().equals(titolo) && u.getProdotto().getAutore().equals(autore) && u.getProdotto().getCasaEditrice().equals(casaEditrice) && u.getProdotto().getAnnoPubblicazione() == annoPubblicazione) {
                long utilizzo = u.getOraRiconsegna() - u.getOraInizio().getTime();
                if (utilizzo > usoMax) usoMax = utilizzo;
            }
        }
        int minutiUso = (int) TimeUnit.MILLISECONDS.toMinutes(usoMax);
        System.out.println("Il prodotto con titolo: " + titolo + ", autore: " + autore + ", ha periodo di noleggio più lungo pari a " + minutiUso + " minuti.");
    }

}
