public class Runner {
    public static void main(String[] args) throws InterruptedException {
        Ludoteca ludoteca = new Ludoteca();
        Gioco g1 = new Gioco("AcchiappaPalle", "StoCazzo", "io", 2000, 18);
        Gioco g2 = new Gioco("AcchiappaPalle", "StoCazzo", "io", 2000, 18);
        ludoteca.insertProdotto(g1);
        ludoteca.insertProdotto(g2);
        Uso s1 = ludoteca.noleggiaGioco("Davide", "Toniatti", "AcchiappaPalle", "StoCazzo", "io", 2000);
        Uso s2 = ludoteca.noleggiaGioco("Luca", "Rossi", "AcchiappaPalle", "StoCazzo", "io", 2000);
        Uso s3 = ludoteca.noleggiaGioco("Piero", "Gialli", "AcchiappaPalle", "StoCazzo", "io", 2000);
        ludoteca.fineNoleggioProdotto(s1);
        ludoteca.fineNoleggioProdotto(s2);
    }
}
