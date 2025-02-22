package array;

public class arraymultidimensionali {

    public static void main(String[] args){

        //2D array = Array di array

        String[][] cars = new String[3][3];

        cars[0][0] = "Ferrari";
        cars[0][1] = "BMW";
        cars[0][2] = "Lamborghini";
        cars[1][0] = "Mustang";
        cars[1][1] = "F-150";
        cars[1][2] = "Ranger";
        cars[2][0] = "Camaro";
        cars[2][1] = "Corvette";
        cars[2][2] = "Tesla";

		for(int i=0; i<cars.length; i++) 
        {
			System.out.println();
			for(int j=0; j<cars[i].length; j++) 
            {
				System.out.print(cars[i][j]+" ");
            }
        }

    }
    
}
