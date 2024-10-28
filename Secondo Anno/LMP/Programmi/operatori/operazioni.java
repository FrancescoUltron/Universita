package operatori;
import java.util.*;

public class operazioni {
    public static void main(String[] args)
    {
        //Create a seeded random number generator
        Random rand = new Random(47);
        int i,j,k;

        //Sceglie un valore da 1 a 100
        j = rand.nextInt(100)+1;
        System.out.println("j : " + j);

        i = rand.nextInt(100)+1;
        System.out.println("i: "+i);

        k = rand.nextInt(100)+1;
        System.out.println("k: "+k);

        //Somma fra due numeri
        i = j+k;
        System.out.println("j + k: " + i);

        //Differenza fra due numeri
        i = j-k;
        System.out.println("j - k: " + i);

        //Divisione fra due numeri
        i = j/k;
        System.out.println("j / k: " + i);

        //Moltiplicazione fra due numeri
        i = j*k;
        System.out.println("j * k: " +i);

        //Modulo fra due numeri
        i = j%k;
        System.out.println("j % k: "+ i);

        //Da notare come queste sono assegnazioni e non aliasing perché stiamo
        //Usando delle variabili

        //Possiamo fare questi test anche con i float e double
        float u, v;

        u = rand.nextFloat();
        v = rand.nextFloat();

        float w = u + v;

        System.out.println("u + v: " + w);
        //Queste operazioni funzionano anche per char,
        //byte, short, int, long, double
    }
}
