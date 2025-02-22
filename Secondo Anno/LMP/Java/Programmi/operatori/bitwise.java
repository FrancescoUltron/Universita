package operatori;

public class bitwise {
    public static void main(String[] args) {

        int a = 10101010;
        int b = 10101011;

        System.out.println("a & b = " + (a & b)); //and su bit
        System.out.println("a | b = " + (a | b)); //or su bit
        System.out.println("a ^ b = " + (a ^ b)); //xor su bit
        System.out.println("~a = " + ~a); //negazione su bit

        int c = 5;

        System.out.println(Integer.toBinaryString(c)); //stampa in binario il valore 5 in binario 101
        c = c << 2; //shift a sinistra di 2 bit del valore 101
        System.out.println(Integer.toBinaryString(c)); //stampa in binario il valore 10100
        System.out.println(c);//stampa il valore corrispettivo al binario 10100 = 20

        char d = 'A';
        System.out.println(Integer.toBinaryString(d)); //stampa in binario il valore 65 = 1000001
        //65 perche' il char viene convertito in int e il valore corrispondente e' 65        
    }
}