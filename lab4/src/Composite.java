import java.util.ArrayList;

public class Composite extends Component {
    private ArrayList<Component> comps = new ArrayList<Component>();

    Composite(String s, int w) {
        super(s, w);
    }

    public int getWeight() {
        int totalWeight = weight;
        for(Component c : comps){
            totalWeight += c.getWeight();
        }
        return totalWeight;
    }

    public String toString() {
        StringBuilder wholeString = new StringBuilder(str);
        for(Component c : comps){
            wholeString.append("\n");
            // Adding tabs depending on depth
            for(int i=0; i<depth; i++) {
                wholeString.append("\t");
            }
            wholeString.append(c.toString());
        }
        return wholeString.toString();
    }

    public void add(Component c) {
        c.setDepth(this.depth + 1);
        comps.add(c);
    }

    public void remove(Component c) {
        comps.remove(c);
    }

    private Component getChild(int i) {
        return comps.get(i);
    }
}
