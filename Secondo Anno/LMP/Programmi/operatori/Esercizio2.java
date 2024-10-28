package operatori;

class voto
{
    float numero;
}

public class Esercizio2{

    public static void main(String[] args)
    {
        //Creazione di due oggetti voto 
        voto voto1 = new voto();
        voto voto2 = new voto(); 

        //Assegnazione del valore agli oggetti

        voto1.numero = 0.50430f;
        voto2.numero = 0.342f;

        voto1.numero = voto2.numero;

        System.out.println("Il voto 1 è: " + voto1.numero);
        System.out.println("Il voto 2 è: " + voto2.numero);

        voto1.numero++;

        System.out.println("Il voto 1 è: " + voto1.numero);
        System.out.println("Il voto 2 è: " + voto2.numero);

        voto1 = voto2;

        System.out.println("Il voto 1 è: " + voto1.numero);
        System.out.println("Il voto 2 è: " + voto2.numero);

        voto2.numero = voto2.numero - 0.1f;

        System.out.println("Il voto 1 è: " + voto1.numero);
        System.out.println("Il voto 2 è: " + voto2.numero);

    }
    
}
