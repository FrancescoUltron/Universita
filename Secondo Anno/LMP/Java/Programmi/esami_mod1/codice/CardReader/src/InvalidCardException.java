public class InvalidCardException extends Exception {
    public InvalidCardException(String filetype) {
        super("La classe " + filetype + " non è valida.");
    }
}
