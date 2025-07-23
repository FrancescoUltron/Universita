package it.uniroma2.art.lmp.lmp0.model;

public class ProfNotFoundException extends Exception {
    public ProfNotFoundException() {
        super("Professore inesistente.");
    }
}
