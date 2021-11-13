import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width, height;
    private Hero hero = new Hero(new Position(10,10));

    public Arena(int width, int height){
        this.height =  height;
        this.width = width;
    }

    private boolean canHeroMove(Position position) {
        if(position.getX() > width - 1 || position.getY() > height - 1 || position.getY() < 0 || position.getX() < 0)
        {
            return false;
        }
        return true;
    }

    private void moveHero(Position position) {
        if (canHeroMove(position))
        {
            hero.setPosition(position);
        }
    }


    public void processKey(KeyStroke key) {

        String keyT = key.getKeyType().toString();
        switch (keyT){
            case "ArrowUp": moveHero(hero.moveUp()); break;
            case "ArrowDown": moveHero(hero.moveDown()); break;
            case "ArrowLeft": moveHero(hero.moveLeft()); break;
            case "ArrowRight": moveHero(hero.moveRight()); break;
        }
        System.out.println(key);
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
    }
}
