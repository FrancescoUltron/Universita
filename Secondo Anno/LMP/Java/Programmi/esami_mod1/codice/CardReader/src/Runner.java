import io.FiletypeNotFoundException;
import io.keyNotFoundException;
import model.AnnoOutOfBoundException;
import model.Attivita;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {

        CardReader cardReader = new CardReader();
        Attivita att;
        try {
            att = cardReader.creaAttivita("file.csv");
        } catch (InvalidCardException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FiletypeNotFoundException e) {
            throw new RuntimeException(e);
        } catch (keyNotFoundException e) {
            throw new RuntimeException(e);
        } catch (AnnoOutOfBoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(att);
    }
}
