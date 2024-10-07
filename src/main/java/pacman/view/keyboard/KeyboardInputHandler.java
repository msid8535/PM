package pacman.view.keyboard;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pacman.model.command.MoveLeftCommand;
import pacman.model.command.MoveRightCommand;
import pacman.model.command.MoveUpCommand;
import pacman.model.command.MoveDownCommand;
import pacman.model.engine.GameEngine;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.player.Controllable;
import pacman.model.entity.dynamic.player.Pacman;

import javax.naming.ldap.Control;
import java.util.List;

/**
 * Responsible for handling keyboard input from player
 */
public class KeyboardInputHandler {

    private final GameEngine model;
    public KeyboardInputHandler(GameEngine model) {
        this.model = model;
    }

    public void handlePressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        System.out.println(keyCode);
        switch (keyCode) {
            case LEFT:
                MoveLeftCommand left = new MoveLeftCommand(model);
                left.execute();
                break;
            case RIGHT:
                MoveRightCommand right = new MoveRightCommand(model);
                right.execute();
                break;
            case DOWN:
                MoveDownCommand down = new MoveDownCommand(model);
                down.execute();
                break;
            case UP:
                MoveUpCommand up = new MoveUpCommand(model);
                up.execute();
                break;
        };
    }
}
