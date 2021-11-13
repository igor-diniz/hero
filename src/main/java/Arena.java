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
        if(position.getX() > width || position.getY() > height || position.getY() < 0 || position.getX() < 0)
        {
            return false;
        }
        return true;
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
        {
            hero.setPosition(position);
        }
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
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

    public void draw(Screen screen) {
        hero.draw(screen);
    }
}
