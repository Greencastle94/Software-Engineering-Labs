// Rickard Björklund & Lucas Grönborg

public class MainMock {
    public static void main(String[] args) {
        Boardgame go = new MockModel();
        ViewControll g = new ViewControll("Mock", 4, go);
        g.setVisible(true);
        g.setSize(500, 500);
    }
}
