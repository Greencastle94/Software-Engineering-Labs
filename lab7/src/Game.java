// Rickard Björklund & Lucas Grönborg

public class Game {
    public static void main(String[] args) {
        Boardgame go = new TTTModel();
        ViewControl g = new ViewControl("Slide Puzzle", 3, go);
        g.setVisible(true);
        g.setSize(500, 500);
    }
}
