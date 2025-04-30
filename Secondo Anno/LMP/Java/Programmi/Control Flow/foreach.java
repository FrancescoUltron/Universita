//Con il foreach possiamo evitare di creare una variabile contatore
import java.util.Random;


public class foreach {
    public static void main(String[] args) {

        Random rand = new Random(47);
        float f[] = new float[10]; //Array di tipo float con 10 elementi
        for(int i = 0;i < 10;i++){
            f[i] = rand.nextFloat();
        }
        for(float x: f){ //Assegna ad x ogni elemento di f
            System.out.println(x);
        }

    }
}