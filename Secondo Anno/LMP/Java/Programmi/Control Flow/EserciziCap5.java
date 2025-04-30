/* (1) Scrivi un programma che che stampa i numeri da 1 a 100 

public class EserciziCap5 {
    public static void main(String[] args){
        for(int i = 1; i < 101;i++){
            System.out.println("numero: " + i);
        }
    }
    
}*/

/*---------------------------------------------------------------------------------- */


/*(2) Write a program that generates 25 random int values. For each value, 
use an if-else statement to classify it as greater than, less than, or equal to a second
randomly generated value. 

public class EserciziCap5 {
    public static void main(String[] args){
        int randomNum1 = (int)(Math.random()*26);
        
        for(int i = 0;i < 26;i++){
            int randomNum2 = (int)(Math.random()*26);
            if(randomNum2 == randomNum1){
                System.out.println("Il valore di " + randomNum2 + " e' uguale a " + randomNum1);
            } else if(randomNum2 < randomNum1){
                System.out.println("Il valore di " + randomNum2 + " e' minore a " + randomNum1);
            } else {
                System.out.println("Il valore di " + randomNum2 + " e' maggiore a " + randomNum1);
            }
        }
    }
}*/

/*---------------------------------------------------------------------------------- */

/*(1) Modify Exercise 2 so that your code is surrounded by an “infinite” while
loop. It will then run until you interrupt it from the keyboard (typically by pressing ControlC).  

public class EserciziCap5 {
    public static void main(String[] args) {
        int randomNum1 = (int)(Math.random()*26);
        while(true){
            int randomNum2 = (int)(Math.random()*26);

            if(randomNum2 == randomNum1){
                System.out.println("Il valore di " + randomNum2 + " e' uguale a " + randomNum1);
            } else if(randomNum2 < randomNum1){
                System.out.println("Il valore di " + randomNum2 + " e' minore a " + randomNum1);
            } else {
                System.out.println("Il valore di " + randomNum2 + " e' maggiore a " + randomNum1);
            }
        }
    }
} */

/*---------------------------------------------------------------------------------- */

/*(3) Write a program that uses two nested for loops and the modulus
operator (%) to detect and print prime numbers (integral numbers that are not evenly
divisible by any other numbers except for themselves and 1). */

public class EserciziCap5{
    public static void main(String[] args) {
        int limit = 100; // Limite fino a cui cercare numeri primi

        System.out.println("Numeri primi da 1 a " + limit + ":");
        for (int num = 2; num <= limit; num++) { // Ciclo per ogni numero da 2 a limite
            boolean isPrime = true;

            for (int divisor = 2; divisor <= Math.sqrt(num); divisor++) { // Controllo divisori
                if (num % divisor == 0) { // Se divisibile, non è primo
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.println(num); // Stampa il numero primo
            }
        }
    }
}