import com.googlecode.lanterna.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.w3c.dom.Text;


public class Hero {

    private Position position;

    public Hero(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public void draw(TextGraphics graphics){
        //graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
        graphics.putString(position.getX() * 2, position.getY() * 2, "\\/" );
        graphics.putString(new TerminalPosition(position.getX() * 2, position.getY() * 2), "/\\");

    }

}
