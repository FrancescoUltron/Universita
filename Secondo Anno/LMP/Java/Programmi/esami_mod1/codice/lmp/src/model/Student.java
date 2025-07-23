package it.uniroma2.art.lmp.lmp0.model;

public interface Student extends Person {
    String getSerial();

    int getAnnoCorso();

    void saluta(Professor prof) throws ProfNotFoundException;

    void saluta(Professor prof, String appellativo) throws ProfNotFoundException;
}
