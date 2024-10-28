package operatori;

public class assegnazione {

    public static void main(String[] args)
    {
        int a = 4;
        int b = a;

        System.out.println(a); //4
        System.out.println(b); //4

        a = a + 1;

        System.out.println(a); //5
        System.out.println(b); //4

    }
    
}
