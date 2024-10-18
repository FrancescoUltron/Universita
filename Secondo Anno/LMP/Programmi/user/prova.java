public class prova { //In Java tutto è un oggetto o una
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        User u = new User(); //Creazione oggetto User
        u.name = "Mario"; //Assegnazione nome
        u.surname = "Rossi"; //Assegnazione cognome
        u.age = 30; //Assegnazione età

        //Un costruttore è un metodo che viene chiamato quando un oggetto viene creato
        //In questo caso è User() che è il costruttore di default

        User u2 = new User(); //Creazione di un secondo oggetto User
        u2.name = "Luca"; 
        u2.surname = "Bianchi"; 
        u2.age = 25;

        System.out.println(u.name);
        System.out.println(u2.surname);
    }
}
