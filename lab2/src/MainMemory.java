// Rickard Björklund & Lucas Grönborg

public class MainMemory {
    public static void main(String[] args) {
        Boardgame go = new MemoryModel();
        ViewControll g = new ViewControll("Memory", 4, go);
        g.setVisible(true);
        g.setSize(500, 500);
    }
}
