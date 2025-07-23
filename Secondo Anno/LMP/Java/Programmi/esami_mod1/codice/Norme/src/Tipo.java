public enum Tipo {
    LEGGESTATO("Legge dello Stato"),
    DECRETO("Decreto"),
    DECRETOLEGGE("Decreto Legge"),
    DECRETOLEGISLATIVO("Decreto Legislativo"),
    ORDINANZA("Ordinanza"),
    PROVVEDIMENTO("Provvedimento"),
    CIRCOLARE("Circolare");

    private final String nome;

    Tipo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
