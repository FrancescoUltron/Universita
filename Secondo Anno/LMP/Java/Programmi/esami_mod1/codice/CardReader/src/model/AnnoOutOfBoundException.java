package model;

public class AnnoOutOfBoundException extends Exception {
    public AnnoOutOfBoundException(int anno) {
        super("L'anno " + anno + " è fuori dal range 1800-2022.");
    }
}
