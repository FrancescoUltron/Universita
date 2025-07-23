import java.util.Date;

public class Uso {
    private Prodotto prodotto;
    private Date oraInizio;
    private String nomeCliente;
    private String cognomeCliente;
    private long oraRiconsegna;
    private Date dataUso;

    public Uso(Prodotto prodotto, Date oraInizio, String nomeCliente, String cognomeCliente) {
        this.prodotto = prodotto;
        this.oraInizio = oraInizio;
        this.nomeCliente = nomeCliente;
        this.cognomeCliente = cognomeCliente;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public Date getOraInizio() {
        return oraInizio;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getCognomeCliente() {
        return cognomeCliente;
    }

    public long getOraRiconsegna() {
        return oraRiconsegna;
    }

    public Date getDataUso() {
        return dataUso;
    }

    public void setDataUso(Date dataUso) {
        this.dataUso = dataUso;
        this.oraRiconsegna = dataUso.getTime();
    }
}
