package operatori;

class Tank
{
    int level; //classe tank con variabile intera
}

public class Assignment{
 
    public static void main(String[] args)
    {
        Tank t1 = new Tank();
        Tank t2 = new Tank();

        t1.level = 9;
        t2.level = 47;

        System.out.println("1: t1.level: " + t1.level + ", t2.level: " + t2.level);
        t1 = t2; //uguaglianza tra oggetti, adesso gli oggetti puntano alla stessa reference;
        //t1 punta allo stesso oggetto di t2
        System.out.println("2: t1.level: " + t1.level + ", t2.level: " + t2.level);
        t1.level = 27; //se cambio valore a t1 cambia anche quello di t2
        System.out.println("3: t1.level: " + t1.level + ", t2.level: " + t2.level);

        //Se avessimo fatto t1.level = t2.level allora avremo ancora oggetti indipendenti.
    }
}
