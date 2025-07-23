/* Le Hashmap mantengono degli oggetti tramite una coppia "Chiave/Valore" */

/* Si possono usare fiversi tipi come:
 * Integer
 * Double
 * String
*/

import java.util.HashMap;

public class Main{
    public static void main(String[] args) {
    
        //Nuova hashmap con chiavi di tipo String e valori di tipo String
        HashMap<String, String> CapitalCities = new HashMap<String, String>();

        //Per aggiungere elementi usiamo il metodo put
        //Aggiungiamo chiave e valore
        CapitalCities.put("Inghilterra", "Londra");
        CapitalCities.put("Italia", "Roma");
        CapitalCities.put("Germania", "Berlino");
        CapitalCities.put("Francia", "Parigi");

        System.out.println(CapitalCities);
        //{Germania=Berlino, Inghilterra=Londra, Francia=Parigi, Italia=Roma}

        //Possiamo accedere ad un valore tramite una chiave usando il metodo get
        String citta = CapitalCities.get("Italia");
        System.out.println(citta); //Roma

        //Possiamo rimuovere un oggetto facendo riferimento alla chiave
        CapitalCities.remove("Inghilterra");
        System.out.println(CapitalCities);

        //Eliminiamo tutti gli elementi con clear
        /*CapitalCities.clear();
        System.out.println(CapitalCities);*/

        //Per trovare la grandezza dell'HashMap usiamo size
        int len = CapitalCities.size();
        System.out.println(len);

        //Print di tutte le chiavi
        for(String i : CapitalCities.keySet()){
            System.out.println(i);
        }

        //Print di tutti i valori
        for(String i : CapitalCities.values()){
            System.out.println(i);
        }

        //Print di tutte le chiavi e valori associati
        for(String i : CapitalCities.keySet()){
            System.out.println("Chiave: " + i + " Valore: " + CapitalCities.get(i));
        }

    }
}