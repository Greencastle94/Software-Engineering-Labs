// Lucas Grönborg & Rickard Björklund

public abstract class Component {
    protected String str;
    protected int weight;
    protected int depth = 1; // Depth in tree structure

    Component(String s, int w) {
        str = s;
        weight = w;
    }

    public abstract int getWeight();

    public void setDepth(int d){ depth = d; }

    public int getDepth(){ return depth; }

    public String getStr() {
        return str;
    }

    public abstract String toString();
}
