import java.util.List;

public class Articolo {
    private int numeroId;
    private String introduzione;
    private List<Comma> listaCommi;

    public Articolo(int numeroId, String introduzione, List<Comma> listaCommi) {
        this.numeroId = numeroId;
        this.introduzione = introduzione;
        this.listaCommi = listaCommi;
    }
}