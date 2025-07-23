package it.uniroma2.art.lmp.lmp0;

import it.uniroma2.art.lmp.lmp0.model.*;

public class Runner {
    public static void main(String[] args) throws AnnoCorsoException {
        PersonImpl p1 = new PersonImpl("Davide", "Toniatti");
        //Student s1 = new Student("Mario", "Rossi", "0285077");
        Professor prof1 = new ProfessorImpl("Armando", "Stellato", "LMP");
        Student per = (Student) new PersonImpl("Davide", "Toniatti");
        per.getName();
        p1.saluta();
        System.out.println(p1);

        // s1.saluta(prof1);
        // s1.saluta(prof1, "un coglione");

        Student s3 = null;
        Student s4 = null;
        try {
            s3 = new StudentImpl("Davide", "Toniatti", CorsoDiStudi.INFORMATICA, 1);
            s4 = new StudentImpl("Paolo", "Verdi", CorsoDiStudi.PSICOLOGIA, 5);
        } catch (AnnoCorsoException e) {
            e.printStackTrace();
        }
        System.out.println(s3);
        System.out.println(s4);

        prof1 = null;
        try {
            s3.saluta(prof1);
        } catch (ProfNotFoundException e) {
            e.printStackTrace();
        }
    }
}
