import java.util.Iterator;
import java.util.Stack;

class DFIterator implements Iterator {
    private Stack<Component> stack;
    DepthFirstIterator(Composite c) {
        DF(c);
    }

    private void DF(Component c) {
        stack.push(c);

        if (c instanceof Composite) {
            for (int i = 0; i < ((Composite) c).howManyChildren(); i++) {
                DF(((Composite) c).getChild(i));
            }
        }
    }

    public Component next() {
        return stack.pop();
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}