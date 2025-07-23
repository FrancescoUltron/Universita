import io.FiletypeNotFoundException;
import io.LettoreFile;
import io.keyNotFoundException;
import model.*;

import java.io.File;
import java.io.IOException;

public class CardReader {
    public Attivita creaAttivita (String f) throws InvalidCardException, IOException, FiletypeNotFoundException, keyNotFoundException, AnnoOutOfBoundException {
        LettoreFile lf = new LettoreFile(f);
        String filetype = lf.getValue("filetype");
        String sede = lf.getValue("sede");
        int anno = Integer.parseInt(lf.getValue("in_attività_dal"));

        switch(filetype) {

            case "ristorante":
                return new Ristorante(anno,sede,lf.getValue("partita_iva"), Ristorante.Categoria.valueOf(lf.getValue("categoria")));

            case "negozio":
                return new Negozio(anno,sede,lf.getValue("partita_iva"), lf.getValue("merce_venduta"));

            case "associazione":
                return new Associazione(anno,sede, Associazione.Scopo.valueOf(lf.getValue("scopo")));

            default:
                throw new InvalidCardException(filetype);

        }
    }

}
