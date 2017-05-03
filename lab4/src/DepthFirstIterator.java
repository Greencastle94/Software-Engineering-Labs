import java.util.Iterator;

class DepthFirstIterator implements Iterator {
    private Component current;
    private Component next;
    private boolean isEmpty = false;
    private int i = 0;
    DepthFirstIterator(Component c) {
        current = c;
    }

    public Component next() {
            if (current instanceof Composite) {
                current = next;
                next = current.getChild(i);
                return current;
            }
            else if (current instanceof Leaf) {
                i = 0;
                return current;
            }
            else {
                isEmpty = true;
            }
        }

    public boolean hasNext() {
        if (isEmpty) {
            return false;
        }
    }
}