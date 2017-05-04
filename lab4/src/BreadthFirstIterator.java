import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

class BreadthFirstIterator implements Iterator {
    private LinkedList<Composite> q = new LinkedList<Composite>();
    private LinkedList<Component> l = new LinkedList<Component>();

    BreadthFirstIterator(Component c, ArrayList comps) {
        q.add((Composite)c);
        l.add(c);

    }

    public Component next() {
        return l.pollFirst();
    }

    public boolean hasNext() {
        if(l.peek() != null){
            return true;
        } else {
            return false;
        }
    }

}