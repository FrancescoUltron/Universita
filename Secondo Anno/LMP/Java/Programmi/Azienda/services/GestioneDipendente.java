package services;

import java.time.LocalDate;
import java.util.ArrayList;

import models.Dipendenti;
import models.Mansione;
import models.Dipendenti.Dipartimento;

public class GestioneDipendente {
    
    private ArrayList<Dipendenti> dipendenti = new ArrayList<>();

    public void NuovoDipendente(String nome, String cognome, LocalDate dataDiNascita, Dipartimento Dipartimento){
        dipendenti.add(new Dipendenti(nome, cognome, dataDiNascita, dataDiNascita, Dipartimento, 0));
    }

    public ArrayList<Dipendenti> chainOfCommand(Dipendenti dipendente){
        
        ArrayList<Dipendenti> chain = new ArrayList<>();

        while(dipendente.getCapo() != null){
            chain.add(dipendente.getCapo());
            dipendente = dipendente.getCapo();
        }

        return chain;
    }

    public void AggiornaDipendente(Mansione mansione, int livello, Dipendenti capo, Dipendenti corrente){
        corrente.setCapo(capo);
        corrente.setMansione(mansione);

        if(livello < 1 || livello > 8){
            throw new IllegalArgumentException("Il livello deve essere compreso tra 1 e 8");
        }
    }

    public Dipendenti prossimoDipendente(Dipendenti dipendenteCorrente){

        for(Dipendenti dip : this.dipendenti){
            if (dip.getCapo().equals(dipendenteCorrente.getCapo()) && dip.getMansione().equals(dipendenteCorrente.getMansione())) {
                return dip;
            }
        }

        System.out.println("Impossibile sostituire dipendente");
        return dipendenteCorrente;
    }

    public ArrayList<Dipendenti> StessoAnno() {
        ArrayList<Dipendenti> dipendentiStessoAnnoDiAssunzione = new ArrayList<>();

        for (Dipendenti d1 : this.dipendenti) {
            for (Dipendenti d2 : this.dipendenti) {
                if (!d1.equals(d2)) {
                    if (d1.getDipartimento().equals(d2.getDipartimento()) &&
                            d1.getDataAssunzione().getMonth() == d2.getDataAssunzione().getMonth() &&
                            d1.getDataAssunzione().getYear() == d2.getDataAssunzione().getYear()) 
                        {
                            dipendentiStessoAnnoDiAssunzione.add(d1);
                    }
                }
            }
        }

        return dipendentiStessoAnnoDiAssunzione;
    }


    public Dipendenti TrovaDipendente(String nome, String cognome){
        for(Dipendenti dip : this.dipendenti){
            if(dip.getNome().equals(nome) && dip.getCognome().equals(cognome)){
                return dip;
            }
        }

        System.out.println("Non è stato trovato nessun dipendente");
        return null;
    }

    public Dipendenti TrovaDipendente(String matricola){
        for(Dipendenti dip : this.dipendenti){
            if(dip.getMatricola().equals(matricola)){
                return dip;
            }
        }

        System.out.println("Non è stato trovato nessun dipendente");
        return null;
    }

    public void showDipendenti() {
        for (Dipendenti dip : dipendenti) {
            System.out.println(dip.toString());
        }
    }

}
