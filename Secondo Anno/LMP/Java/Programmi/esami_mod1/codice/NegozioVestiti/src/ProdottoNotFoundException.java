public class ProdottoNotFoundException extends Exception {
    public ProdottoNotFoundException(Prodotto p) {
        super("Il prodotto di nome " + p.getNome() + " non è presente in negozio!");
    }
}
