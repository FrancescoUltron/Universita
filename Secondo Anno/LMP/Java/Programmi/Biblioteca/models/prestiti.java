package models;
import java.time.LocalDate;

public class prestiti {
    
    private LocalDate dataInizio;
    private LocalDate dataConsegnaPrevista;
    private LocalDate dataConsegna;

    String nome;
    String cognome;

    private double prezzo;
    private static final int pena = 10;

    public prestiti(LocalDate dataInizio, LocalDate dataConsegnaPrevista, LocalDate dataConsegna, String nome, String cognome, double prezzo) {
        setDate(dataInizio, dataConsegnaPrevista, dataConsegna);
        this.nome = nome;
        this.cognome = cognome;
        this.prezzo = prezzo;
    }

    public void setDataConsegna(LocalDate dataConsegna) {
        this.dataConsegna = dataConsegna;
        if(checkConsegna()){
            this.prezzo += pena;
            System.out.println("Hai consegnato in ritardo, nuovo costo " + this.prezzo);
        } 
    }

    public boolean checkConsegna(){
        return this.dataConsegna.isAfter(this.dataConsegnaPrevista);
    }

    public void setDate(LocalDate dataInizio, LocalDate dataConsegnaPrevista, LocalDate dataConsegna){
        if(dataInizio.isAfter(dataConsegna) || dataInizio.isAfter(dataConsegnaPrevista)){
            throw new IllegalArgumentException("La data di inizio non può essere dopo la data di consegna o consegna prevista!");
        }

        this.dataInizio = dataInizio;
        this.dataConsegna = dataConsegna;
        this.dataConsegnaPrevista = dataConsegnaPrevista;
    }

    public void setDataConsegnaPrevista(int giorni){
        if(giorni <= 0){
            System.out.println("Operazione inammissibile");
        } else {
            this.dataConsegnaPrevista = this.dataConsegnaPrevista.plusDays(giorni);
        }
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCognome(String cognome){
        this.cognome = cognome;
    }

    public void setPrezzo(double prezzo){
        this.prezzo = prezzo;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataConsegna() {
        return dataConsegna;
    }

    public LocalDate getDataConsegnaPrevista() {
        return dataConsegnaPrevista;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public String getNome() {
        return nome;
    }

    public static int getPena() {
        return pena;
    }

    public double getPrezzo() {
        return prezzo;
    }
}
