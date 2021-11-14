import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width, height;
    private Hero hero = new Hero(new Position(10,10));
    private List<Wall> walls;

    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    public Arena(int width, int height){
        this.height =  height;
        this.width = width;
        this.walls = createWalls();
    }

    private boolean canHeroMove(Position position) {
        if(position.getX() > width - 1 || position.getY() > height - 1 || position.getY() < 0 || position.getX() < 0)
        {
            return false;
        }
        for (Wall wall : walls)
        {
            if (position.getX() == wall.getX() && position.getY() == wall.getY())
            {
                return false;
            }
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
        for (Wall wall : walls)
        {
            wall.draw(graphics);
        }
        hero.draw(graphics);
    }
}
