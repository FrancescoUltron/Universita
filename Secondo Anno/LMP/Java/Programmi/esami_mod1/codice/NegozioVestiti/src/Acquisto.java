import java.util.HashMap;

public class Acquisto {

    private Cliente acquirente;
    private Date data;
    private HashMap<Prodotto, Integer> listaProdottoAcquistati;

    public Acquisto(Cliente acquirente, Date data, HashMap<Prodotto, Integer> listaProdottoAcquistati) {
        this.acquirente = acquirente;
        this.data = data;
        this.listaProdottoAcquistati = listaProdottoAcquistati;
    }

    public Cliente getAcquirente() {
        return acquirente;
    }

    public Date getData() {
        return data;
    }

    public HashMap<Prodotto, Integer> getListaProdottoAcquistati() {
        return listaProdottoAcquistati;
    }
}
