import java.util.Iterator;
import java.util.LinkedList;

class BFIterator implements Iterator {
    private LinkedList<Component> l = new LinkedList();
    private LinkedList<Component> q = new LinkedList<>();
    BFIterator(Composite c) {
        BF(c);
    }

    private void BF(Component c) {

        if (c instanceof Composite)
            q.add(c);

        while (!q.isEmpty()) {
            Component current = q.pollFirst();
            l.add(current);
            if (current instanceof Composite) {
                Composite currentComposite = (Composite) current;
                for (int i = 0; i < currentComposite.howManyChildren(); i++) {
                    q.add(currentComposite.getChild(i));
                }
            }
        }
    }

    public Component next() { return l.pollFirst(); }

    public boolean hasNext() { return !l.isEmpty(); }
}