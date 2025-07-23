import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Portale {
    private ArrayList<Legge> listaLeggi = new ArrayList<>();

    public Legge addLegge(Tipo tipo, Date data, String intestazione, List<Articolo> parteArticolata, String conclusione) {
        Legge l = new Legge(tipo, data, intestazione, parteArticolata, conclusione);
        listaLeggi.add(l);
        return l;
    }

    public Legge getLegge(Tipo tipo, Date data) throws LeggeNotFoundException{
        Legge legge = null;
        for (Legge l : listaLeggi) {
            if (tipo.equals(l.getTipo()) && data.equals(l.getData())) {
                System.out.println("La legge cercata è disponibile: " + tipo.getNome() + "-" + data);
                legge = l;
            }
        }
        if (legge == null) {
            throw new LeggeNotFoundException(tipo,data);
        }
        return legge;
    }

    public void leggiStessoTipoStessoAnno(Tipo tipo, int anno) {
        for (Legge l :
                listaLeggi) {
            if (tipo.equals(l.getTipo()) && l.getData().getYear() == anno) {
                System.out.println("legge:"+tipo.getNome()+":"+l.getData());
            }
        }
    }

    public void leggiBrutte() {
        for (Legge l :
                listaLeggi) {
            if (l.getParteArticolata().size() > 20) {
                System.out.println(l);
            }
        }
    }
}
