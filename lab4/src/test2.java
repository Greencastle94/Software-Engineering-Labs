import human.*;

public class test2 {
    public static void main(String[] args) {
        Human billie = Human.create("Billie", "xxxxxx-560x");
        Human anna = Human.create("Anna", "xxxxxx-642x");
        Human magnus = Human.create("Magnus","xxxxxx-011x");
        System.out.println(billie);
        System.out.println(anna);
        System.out.println(magnus);

        // Alla nedan ska ge kompileringsfel
        Human h = new Human(){};   // Skapar ett objekt av subklass till Human, klasskroppen är tom
        //Man m = new Man("Ska inte funka!", "xxxxxx-642x");
        //Woman w = new Woman("Ska inte funka!", "xxxxxx-641x");
        //NonBinary nb = new NonBinary("Ska inte funka!", "xxxxxx-640x");
    }
}
