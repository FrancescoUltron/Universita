package models;

public class dvd extends Supporto {

    private double durata;

    public dvd(String titolo, String entePubblicante, int annoPubblicazione, double durata){
        super(titolo, entePubblicante, annoPubblicazione);
        this.durata = durata;
    }

    public double getDurata() {
        return durata;
    }
    
}
