import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        Gestore g = new Gestore();
        Componente c1 = new Componente("Gomma", "Guatemala", 3, 5);
        Componente c2 = new Componente("Cuoio", "locale", 1, 3);
        Componente c3 = new Componente("Colore", "Guatemala", 6, 6);
        ArrayList<Componente> comp = new ArrayList<>();
        comp.add(c1);
        comp.add(c2);
        comp.add(c3);
        g.insertProdotto("Pal0", "Pallone", comp, 3);
        g.insertProdotto("Pal1", "Pallone", comp, 1);
        g.showProdotti();
        g.rankingProdotti();
    }
}
