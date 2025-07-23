package it.uniroma2.art.lmp.lmp0.model;

public class AnnoCorsoException extends Exception {
    public AnnoCorsoException(int annoCorso) {
        super("Questa università non ammette l'anno di iscrizione: " + annoCorso);
    }
}
