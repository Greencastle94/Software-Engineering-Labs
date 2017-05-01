// Rickard Björklund & Lucas Grönborg

public class MainSlidePuzzle {
    public static void main(String[] args) {
        Boardgame go = new FifteenModel();
        ViewControll g = new ViewControll("Slide Puzzle", 4, go);
        g.setVisible(true);
        g.setSize(500, 500);
    }
}
