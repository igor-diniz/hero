import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.security.Key;

public class Game {
    Screen screen;
    private int x = 10;
    private int y = 10;

    public Game() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    private void draw() throws IOException {

        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }

    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.EOF)
            {
                break;
            }
            processKey(key);
        }
    }
    private void processKey(KeyStroke key) throws IOException {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
        {
            screen.close();
        }

        String keyT = key.getKeyType().toString();
        switch (keyT){
            case "ArrowUp": y-=1; break;
            case "ArrowDown": y+=1; break;
            case "ArrowLeft": x-=1; break;
            case "ArrowRight": x+=1; break;
        }
        System.out.println(key);
    }
}
