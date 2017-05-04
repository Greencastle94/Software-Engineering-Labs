import java.util.Iterator;

public class test1 {
    public static void main(String[] args) {
        Composite suitcase = new Composite("1. Suitcase", 700);
        Composite bag = new Composite("2. Bag", 200);
        Composite bag2 = new Composite("2. Bag 2", 200);
        Composite smallPlasticBag = new Composite("3. Small plastic bag", 10);

        // Packing the suitcase
        suitcase.add(new Leaf("2. T-shirt", 100));
        suitcase.add(new Leaf("2. Jeans", 200));
        suitcase.add(new Leaf("2. Sweater", 200));
        suitcase.add(new Leaf("2. Shoes", 150));
        suitcase.add(bag);
        bag.add(new Leaf("3. Soap", 150));
        bag.add(new Leaf("3. Schampoo", 150));
        bag.add(new Leaf("3. Toothbrush", 30));
        bag.add(smallPlasticBag);
        smallPlasticBag.add(new Leaf("4. Hair clipper", 20));
        smallPlasticBag.add(new Leaf("4. Hair tie", 10));
        suitcase.add(bag2);
        bag2.add(new Leaf("3. Soap", 150));
        bag2.add(new Leaf("3. Schampoo", 150));
        bag2.add(new Leaf("3. Toothbrush", 30));

        // Print out the total weight of the suitcase
        //System.out.println(suitcase.getWeight() + " g");
        //System.out.println();
        // Print out the items in the suitcase
        //System.out.println(suitcase.toString());
        //System.out.println();

        Iterator scIter = suitcase.iterator();
        while (scIter.hasNext()) {
            Component comp = (Component) scIter.next();
            System.out.println(comp.getStr());
        }
    }
}
