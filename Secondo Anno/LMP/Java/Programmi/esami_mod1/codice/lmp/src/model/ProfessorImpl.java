package it.uniroma2.art.lmp.lmp0.model;

public class ProfessorImpl extends PersonImpl implements Professor {
    private String cattedra;

    public ProfessorImpl(String name, String surname, String cattedra) {
        super(name, surname);
    }

    @Override
    public String getCattedra() {
        return cattedra;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "cattedra='" + cattedra + '\'' +
                "} " + super.toString();
    }
}
