package array;

public class array {
    
    public static void main(String[] args)
    {
        String[] cars = {"Camaro", "Corvette", "Tesla", "BMW"};
        cars[0] = "Ferrari";
        System.out.println(cars[0]);

        int[] valori = new int[3]; //3 = lunghezza array

        valori[0] = 0;
        valori[1] = 10;
        valori[2] = 100;

        System.out.println(valori[0]);
        System.out.println(valori[1]);
        System.out.println(valori[2]);

        for(int i = 0;i < cars.length; i++)
        {
          System.out.println("La macchina in posizione "+ i + ":" + cars[i]);
        }

    }
}
