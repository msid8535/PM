package pacman.model.command;

import pacman.model.engine.GameEngine;

public class MoveLeftCommand implements Command {
    private GameEngine model;

    public MoveLeftCommand(GameEngine model) {
        this.model = model;
    }

    /***
     * moves pacman left
     */
    @Override
    public void execute() {
        model.moveLeft();
        //System.out.println("leftward key executed");
    }
}
