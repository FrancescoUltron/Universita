import java.util.Date;
import java.util.HashMap;

public class Acquisto {
    private Cliente clienteAcquisto;
    private Date data;
    private HashMap<Prodotto,Integer> listaProdotti;

    public Acquisto(Cliente clienteAcquisto, Date data, HashMap<Prodotto, Integer> listaProdotti) {
        this.clienteAcquisto = clienteAcquisto;
        this.data = data;
        this.listaProdotti = listaProdotti;
    }

    public Cliente getClienteAcquisto() {
        return clienteAcquisto;
    }

    public Date getData() {
        return data;
    }

    public HashMap<Prodotto, Integer> getListaProdotti() {
        return listaProdotti;
    }
}
