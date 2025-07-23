import java.util.HashMap;
import java.util.List;

public class Legge {
    private Tipo tipo;
    private Date data;
    private String intestazione;
    private List<Articolo> parteArticolata;
    private List<Object> allegati;
    private String conclusione;

    public Legge(Tipo tipo, Date data, String intestazione, List<Articolo> parteArticolata, String conclusione) {
        this.tipo = tipo;
        this.data = data;
        this.intestazione = intestazione;
        this.parteArticolata = parteArticolata;
        this.conclusione = conclusione;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Date getData() {
        return data;
    }

    public String getIntestazione() {
        return intestazione;
    }

    public List<Articolo> getParteArticolata() {
        return parteArticolata;
    }

    public String getConclusione() {
        return conclusione;
    }

    public void setAllegati(List<Object> allegati) {
        this.allegati = allegati;
    }

    @Override
    public String toString() {
        return "Legge: " + tipo + "-" + data + ", numero di articoli: " + parteArticolata.size() +
                ", allegati=" + allegati +
                ", conclusione='" + conclusione;
    }
}
