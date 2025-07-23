package it.uniroma2.art.lmp.lmp0.model;

public enum CorsoDiStudi {
    PSICOLOGIA("psy"),
    INFORMATICA("cs");

    private String code;

    CorsoDiStudi(String code) {
       this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
