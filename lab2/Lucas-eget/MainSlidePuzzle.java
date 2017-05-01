public class MainSlidePuzzle {
    public static void main(String[] args) {
        //Boardgame go = new FifteenModel();
        Boardgame go = new TTTModel();
        ViewController g = new ViewController(go);
        g.setVisible(true);
        g.setSize(500, 500);
    }
}
