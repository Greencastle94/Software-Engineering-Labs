// Lucas Grönborg & Rickard Björklund

import java.util.Iterator;
import java.util.LinkedList;

class DFIterator implements Iterator {
    private LinkedList<Component> l = new LinkedList();
    DFIterator(Composite c) {
        DF(c);
    }

    private void DF(Component c) {
        l.add(c);

        if (c instanceof Composite) {
            for (int i = 0; i < ((Composite) c).howManyChildren(); i++) {
                DF(((Composite) c).getChild(i));
            }
        }
    }

    public Component next() { return l.pollFirst(); }

    public boolean hasNext() { return !l.isEmpty(); }
}