public class LeggeNotFoundException extends Exception {
    public LeggeNotFoundException(Tipo tipo, Date data) {
        super("La legge "+ tipo.getNome() + "-" + data + " non esiste!");
    }
}
