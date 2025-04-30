public class BicycleDemo {
    public static void main(String[] args) {
        //Crea due oggetti
        Bicycle bike1 = new Bicycle();
        Bicycle bike2 = new Bicycle();

        MountainBike bike3 = new MountainBike();

        //Invochiamo dei metodi
        bike1.changeCadence(50);
        bike1.speedUp(10);
        bike1.changeGear(2);
        bike1.printStates();

        bike2.changeCadence(50);
        bike2.speedUp(10);
        bike2.changeGear(2);
        bike2.changeCadence(40);
        bike2.speedUp(10);
        bike2.changeGear(3);
        bike2.printStates();

        bike3.specificaTipo();
        bike3.printStates();
    }    
}
