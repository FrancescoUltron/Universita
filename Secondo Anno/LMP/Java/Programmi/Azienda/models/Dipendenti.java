package models;

import java.time.LocalDate;

public class Dipendenti {

    public enum Dipartimento{
        MARKETING, RISORSE_UMANE, SVILUPPO;
    }

    Dipartimento dipartimento;

    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private LocalDate dataAssunzione;

    private Mansione mansione;

    private String matricola;
    static int numeroIscrizione = 0;

    private int livello; 
    private Dipendenti Capo;

    public Dipendenti(String nome, String cognome, LocalDate dataDiNascita, LocalDate dataAssunzione, Dipartimento dipartimento, int livello){
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;

        this.dataAssunzione = LocalDate.now();
        this.matricola = matricola = "CTL_" + String.valueOf(numeroIscrizione + 1);

        this.livello = livello;

        this.dipartimento = dipartimento;
        numeroIscrizione++;
    }

    public Mansione getMansione() {
        return mansione;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataAssunzione() {
        return dataAssunzione;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public Dipartimento getDipartimento() {
        return dipartimento;
    }

    public String getMatricola() {
        return matricola;
    }

    public String getNome() {
        return nome;
    }

    public static int getNumeroIscrizione() {
        return numeroIscrizione;
    }

    public Dipendenti getCapo() {
        return Capo;
    }

    public int getLivello() {
        return livello;
    }

    public void setCapo(Dipendenti capo) {
        this.Capo = capo;
    }

    public void setMansione(Mansione mansione){
        this.mansione = mansione;
    }

    public void setDipartimento(Dipartimento dipartimento) {
        this.dipartimento = dipartimento;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
