package it.uniroma2.art.lmp.lmp0.model;

public class PersonImpl implements Person {

    private final String name;
    private final String surname;

    // Convention: put the constructor or constructors before any other method
    public PersonImpl(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public void saluta() {
        System.out.println("Hey!");
    }

    @Override
    public String toString() {
        return "PersonImpl{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
