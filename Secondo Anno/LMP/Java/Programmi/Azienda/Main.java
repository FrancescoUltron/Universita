import java.time.LocalDate;
import java.util.ArrayList;

import models.Dipendenti;
import models.Mansione;
import models.Dipendenti.Dipartimento;
import services.GestioneDipendente;

public class Main {
    public static void main(String[] args) {
        GestioneDipendente gs = new GestioneDipendente();

        gs.NuovoDipendente("Marco", "Rossi", LocalDate.of(2002, 11, 9), Dipartimento.MARKETING);
        gs.NuovoDipendente("Lorenzo", "Bianchi", LocalDate.of(2002, 10, 4), Dipartimento.RISORSE_UMANE);
        gs.NuovoDipendente("Giacomo", "Verdi", LocalDate.of(2002, 9, 9), Dipartimento.MARKETING);

        Dipendenti Marco = gs.TrovaDipendente("Marco", "Rossi");
        Dipendenti Lorenzo = gs.TrovaDipendente("Lorenzo", "Bianchi");
        Dipendenti giacomo = gs.TrovaDipendente("Giacomo", "Verdi");

        gs.AggiornaDipendente(Mansione.HR, 8, null, Marco);
        gs.AggiornaDipendente(Mansione.MARKETING, 7, Lorenzo, Marco);
        gs.AggiornaDipendente(Mansione.MARKETING, 7, Marco, giacomo);

        gs.showDipendenti();
        ArrayList<Dipendenti> chain = gs.chainOfCommand(giacomo);

        System.out.println("Chain of command\n\n\n");
        System.out.println(chain);

        System.out.println("\n\n\n");

        ArrayList<Dipendenti> Assumi = gs.StessoAnno();
        System.out.println("Same year of hire\n\n\n");
        System.out.println(Assumi);

    }
}