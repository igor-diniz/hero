import java.io.IOException;


public class Application {
    public static void main(String[] args) throws IOException {
        Game game = new Game(100,30);
        game.run();
    }
}
