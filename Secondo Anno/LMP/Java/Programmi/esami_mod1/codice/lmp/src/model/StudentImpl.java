package it.uniroma2.art.lmp.lmp0.model;

public class StudentImpl extends PersonImpl implements Student {
    private final String serial;
    private static int nroIscritti = 0;
    private CorsoDiStudi course;

    private int annoCorso;

    /*
    public StudentImpl(String name, String surname, String serial) {
        super(name, surname);
        this.serial = serial;
    }
    */

    public StudentImpl(String name, String surname, CorsoDiStudi course, int annoCorso) throws AnnoCorsoException {
        super(name, surname);
        if (annoCorso > 5 || annoCorso < 1) {
            throw new AnnoCorsoException(annoCorso);
        }
        this.annoCorso = annoCorso;
        this.course = course;
        this.serial = course.getCode()+"_"+ ++nroIscritti;
    }

    @Override
    public String getSerial() {
        return serial;
    }

    @Override
    public int getAnnoCorso() {
        return this.annoCorso;
    }

    @Override
    public void saluta(Professor prof) throws ProfNotFoundException {
        if (prof == null) {
            throw new ProfNotFoundException();
        }
        System.out.println("Salve professor " + prof.getName());
    }

    @Override
    public void saluta(Professor prof, String appellativo) throws ProfNotFoundException {
        if (prof == null) {
            throw new ProfNotFoundException();
        }
        System.out.println("Salve professor " + prof.getName() + ", lei è proprio " + appellativo);
    }

    @Override
    public String toString() {
        return "StudentImpl{" +
                "serial='" + serial + '\'' +
                ", course=" + course +
                ", annoCorso=" + annoCorso +
                "} " + super.toString();
    }
}
