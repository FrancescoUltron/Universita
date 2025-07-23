import java.time.LocalDate;

import models.Supporto;
import models.dvd;
import models.libro;
import models.prestiti;
import services.biblioteca;

public class Main {
    public static void main(String[] args) {
        biblioteca b = new biblioteca();

        b.addProdotto("Il Signore Degli Anelli", "Mondadori", 2000, null, 400);

        libro p1 = (libro) b.getProdotto("Il Signore Degli Anelli");
        p1.addPrestito(LocalDate.now(), LocalDate.now(), LocalDate.of(2024, 11, 22), "Marco", "Gialli", 21.0);

        p1.addPrestito(LocalDate.of(2024, 12, 9), LocalDate.of(2025, 1, 27), LocalDate.of(2025, 2, 15), "Mario", "Rossi", 21.0);
        
        p1.addPrestito(LocalDate.of(2025, 5, 21), LocalDate.of(2025, 5, 25), LocalDate.of(2025, 6, 12), "Marco", "Gialli", 21.0);

        b.trovaInconsistenze(p1);

        p1.addPrestito(LocalDate.now(), LocalDate.now(), LocalDate.of(2024, 9, 11), "Giovanni", "Verdi", 21.0);
        System.out.println("Prima di aggiungere:");
        System.out.println(p1.getSeqPrestiti());
        System.out.println("Dopo l'aggiunta: ");
        b.setGiorniRicosengna(p1, 10);
        System.out.println(p1.getSeqPrestiti());
        System.out.println();

        int n = b.maxGiorniPrestiti(p1);
        System.out.println(n);

        libro p2 = (libro) b.getProdotto("The Witcher");
    }
}
