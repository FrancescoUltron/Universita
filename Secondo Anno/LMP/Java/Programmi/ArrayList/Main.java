import java.util.ArrayList; //Importa la classe Arraylist
import java.util.Collections; //Importa la classe Collections

/* Una delle funzioni di Collections è
 * quella del Sorting in maniera numerica o alfabetica
*/

/* Arraylist permette di creare un array ridimensionabile
 * Possono essere di diversi tipi: Integer, Boolean, Character, Double
*/

public class Main {
    
    public static void main(String[] args) {

        //Creazione di un oggetto Arraylist
        ArrayList<String> cars = new ArrayList<String>();
        ArrayList<Integer> numeri = new ArrayList<Integer>();

        //Aggiungere elementi nell'ArrayList
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");

        cars.add(0, "Ferrari"); //Aggiungi Ferrari in posizione 0
        
        System.out.println(cars);

        //Accedi ad un elemento dell'ArrayList
        cars.get(0);

        //Cambia un elemento 
        cars.set(0, "Opel");
        System.out.println(cars);

        //Elimina un elemento
        cars.remove(0);
        System.out.println(cars);

        //Elimina tutti gli elementi
        cars.clear();
        System.out.println(cars);

        //Controlla la lunghezza
        System.out.println(cars.size());

        numeri.add(4);
        numeri.add(2);
        numeri.add(5);
        numeri.add(1);
        numeri.add(3);

        //Ciclo for con Arraylist
        System.out.println("Lista NON sortata: ");
        for(int i = 0; i < numeri.size(); i++){
            System.out.println(numeri.get(i));
        }

        //Loop tramite for-each
        System.out.println("Lista NON sortata: ");
        for(int i : numeri){
            System.out.println(i);
        }

        //Sorting degli elementi 
        System.out.println("Lista sortata: ");
        Collections.sort(numeri);
        for(int i : numeri){
            System.out.println(i);
        }





    }
}