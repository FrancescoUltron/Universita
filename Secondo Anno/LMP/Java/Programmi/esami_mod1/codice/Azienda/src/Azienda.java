import java.util.ArrayList;
import java.util.HashSet;

public class Azienda {
    private ArrayList<Dipendente> listaDipendenti = new ArrayList<>();

    private static int progressivo;

    public Dipendente creaDipendenteMarketing(String nome, String cognome, Date data_nascita,  String mansione, int livello, Dipendente superiore) {
        Dipendente d = new Dipendente(nome, cognome, data_nascita, new Date());
        d.setMatricola("CLT_" + ++progressivo);
        d.setDipartimento(Dipendente.Dipartimento.MARKETING);
        d.setMansione(mansione);
        d.setLivello(livello);
        d.setSuperiore(superiore);
        listaDipendenti.add(d);
        return d;
    }
    public Dipendente creaDipendenteSviluppo(String nome, String cognome, Date data_nascita,  String mansione, int livello, Dipendente superiore) {
        Dipendente d = new Dipendente(nome, cognome, data_nascita, new Date());
        d.setMatricola("CLT_" + ++progressivo);
        d.setDipartimento(Dipendente.Dipartimento.SVILUPPO);
        d.setMansione(mansione);
        d.setLivello(livello);
        d.setSuperiore(superiore);
        listaDipendenti.add(d);
        return d;
    }

    public Dipendente creaDipendenteRisorseUmane(String nome, String cognome, Date data_nascita,  String mansione, int livello, Dipendente superiore) {
        Dipendente d = new Dipendente(nome, cognome, data_nascita, new Date());
        d.setMatricola("CLT_" + ++progressivo);
        d.setDipartimento(Dipendente.Dipartimento.RISORSE_UMANE);
        d.setMansione(mansione);
        d.setLivello(livello);
        d.setSuperiore(superiore);
        listaDipendenti.add(d);
        return d;
    }
    public ArrayList<Dipendente> chainOfCommand (Dipendente dipendente) {
        ArrayList<Dipendente> chainOfCom = new ArrayList<>();
        Dipendente sup = dipendente.getSuperiore();
        while(sup != null) {
           chainOfCom.add(sup);
           sup = sup.getSuperiore();
        }
        return chainOfCom;
    }

    public Dipendente impiegatoDaSostituire(Dipendente dipendente) {
        Dipendente capo = dipendente.getSuperiore();
        Dipendente candidato = null;
        int priority = 1000;
        for (Dipendente d :
                listaDipendenti) {
            System.out.println("dipendente: " + d.getNome());
            if ( ! (d.equals(dipendente)) && d.getMansione().equals(dipendente.getMansione()) ) {
                for (Dipendente d1 :
                        chainOfCommand(d)) {
                    System.out.println("capo di " + d.getNome() + ": " + d1.getNome());
                    if (capo.equals(d1) && chainOfCommand(d).indexOf(d1)+1 < priority) {
                        candidato = d;
                        priority = chainOfCommand(d).indexOf(d1)+1;
                        System.out.println(priority);
                    }
                }
            }
        }
        return candidato;
    }

    public HashSet<Dipendente> assuntiStessoMeseDipartimento() {
        HashSet<Dipendente> listDip = new HashSet<>();
        for (Dipendente d :
                listaDipendenti) {
            for (Dipendente d1 :
                    listaDipendenti) {
                if ((!d.equals(d1)) && (d.getDipartimento() == d1.getDipartimento()) &&
                        (d.getData_assunzione().getMonth() == d1.getData_assunzione().getMonth()) &&
                        (d.getData_assunzione().getYear() == d1.getData_assunzione().getYear())) {
                    listDip.add(d1);
                    listDip.add(d);
                }

            }
        }
        return listDip;
    }
}