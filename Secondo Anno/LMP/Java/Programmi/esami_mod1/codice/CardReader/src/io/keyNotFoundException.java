package io;

public class keyNotFoundException extends Exception {
    public keyNotFoundException(String key) {
        super("Attriubto " + key +  " mancante");
    }
}
