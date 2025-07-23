package services;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import models.Supporto;
import models.dvd;
import models.libro;
import models.prestiti;

public class biblioteca {
    private HashMap<String, Supporto> archivioProdotti = new HashMap<>();

    public void addProdotto(String titolo, String entePubblicante, int anno, ArrayList<prestiti> seqPresititi, int nPagine){
        archivioProdotti.put(titolo ,new libro(titolo, entePubblicante, anno, nPagine));
    }

    public void addProdotto(String titolo, String entePubblicante, int anno, ArrayList<prestiti> seqPresititi, double durata){
        archivioProdotti.put(titolo, new dvd(titolo, entePubblicante, anno, durata));
    }

    public Supporto getProdotto(String titolo){
        Supporto p = archivioProdotti.get(titolo);
        if(p == null){
            throw new NoSuchElementException("Il prodotto " + titolo + " non è presente!");
        }
        return p;
    }

    public void setGiorniRicosengna(Supporto p, int giorni){
        for (prestiti pre : p.getSeqPrestiti()) {
            pre.setDataConsegnaPrevista(giorni);
        }
    }

    public int maxGiorniPrestiti(Supporto p){
        int max = 0;
        for (prestiti pre : p.getSeqPrestiti()) {
                Period periodo = Period.between(pre.getDataInizio(), pre.getDataConsegna());
                int days = periodo.getDays();
                if(days > max){
                    max = days;
                }
        }
        return max;
    }

    public void trovaInconsistenze(Supporto p){
        ArrayList<prestiti> inconsistenze = new ArrayList<>();
        ArrayList<prestiti> seqPrestiti = p.getSeqPrestiti();

        System.out.println("\nControllo inconsistenze per il prodotto: " + p);

        if (seqPrestiti.size() > 1) {
            for (int i = 0; i < seqPrestiti.size(); i++) {
                prestiti prestito1 = seqPrestiti.get(i);
    
                for (int j = 0; j < seqPrestiti.size(); j++) {
                    prestiti prestito2 = seqPrestiti.get(j);
    
                    if (prestito1.getDataInizio().isBefore(prestito2.getDataConsegna()) &&  !prestito1.equals(prestito2)) {
                        if (!inconsistenze.contains(prestito1)){
                            inconsistenze.add(prestito1);
                        }
                    }
                }
            }
        } else {
            System.err.println("Non ci sono abbastanza prestiti per questo prodotto");
        }

        if(inconsistenze.isEmpty()){
            System.out.println("Non sono state trovate inconsistenze");
        }else{
            System.out.println("Sono state trovate inconsistenze per i seguneti prestiti: ");
            for (prestiti prestito : inconsistenze) {
                System.out.println(prestito);
            }
            System.out.println();
        }

    }

} 
