package operatori;

public class operatorirelazionali {

    public static void main(String[] args)
    {
        int a = 10;
        int b = 20;
        int c = 10;
        int d = 20;
        boolean e = true;
        boolean f = false;

        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a < b: " + (a < b));
        System.out.println("b >= a: " + (b >= a));
        System.out.println("b <= a: " + (b <= a));
        System.out.println("a == c: " + (a == c));
        System.out.println("b != d: " + (b != d));

        System.out.println("e AND f: " + (e && f));
        System.out.println("e OR f: " + (e || f));
        System.out.println("NOT f: " + (!f));
    }
}