public class Runner {
    public static void main(String[] args) throws DataNonConformeException {
        Azienda azienda = new Azienda();
        Dipendente d1 = azienda.creaDipendenteMarketing("Davide","Toniatti", new Date(20,3,2000), "nullafacente", 6, null);
        Dipendente d2 = azienda.creaDipendenteMarketing("Mario","Rossi", new Date(24,3,2002), "nullafacente", 4, d1);
        Dipendente d3 = azienda.creaDipendenteRisorseUmane("Paolo","Gialli", new Date(17,6,2002), "nullafacente", 3, d2);
        Dipendente d4 = azienda.creaDipendenteRisorseUmane("Luca","Marroni", new Date(17,6,2002), "nullafacente", 3, d3);
        Dipendente d5 = azienda.creaDipendenteRisorseUmane("Giancarlo","Violi", new Date(17,6,2002), "nullafacente", 3, d2);
        Dipendente d6 = azienda.creaDipendenteMarketing("Lorenzo","Bianchi", new Date(10,7,1999), "nullafacente", 2, d1);

        System.out.println(azienda.impiegatoDaSostituire(d4));

    }
}
