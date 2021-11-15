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
import java.util.Random;

public class Arena {
    private int width, height;
    private Hero hero = new Hero(10,10);
    private List<Wall> walls;
    private List<Coin> coins;

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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;

            if(hero.getPosition().equals(new Position(x,y)))
            {
                i--;
                continue;
            }
            Coin coin = new Coin(x, y);
            coins.add(coin);
        }
        return coins;
    }

    public Arena(int width, int height){
        this.height =  height;
        this.width = width;
        this.walls = createWalls();
        this.coins = createCoins();
    }

    private boolean canHeroMove(Position position) {
        if(position.getX() > width - 1 || position.getY() > height - 1 || position.getY() < 0 || position.getX() < 0)
        {
            return false;
        }
        for (Wall wall : walls)
        {
            if (wall.getPosition().equals(position))
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

    private void retrieveCoins(){
        for (Coin coin : coins)
        {
            if(coin.getPosition().equals(hero.getPosition()))
            {
                coins.remove(coin);
                break;
            }
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
        retrieveCoins();
        System.out.println(key);
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
        {
            wall.draw(graphics);
        }

        for (Coin coin : coins)
        {
            coin.draw(graphics);
        }
        hero.draw(graphics);
    }
}
