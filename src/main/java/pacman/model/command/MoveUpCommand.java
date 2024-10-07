package pacman.model.command;

import pacman.model.engine.GameEngine;

public class MoveUpCommand implements Command {
    private GameEngine model;

    public MoveUpCommand(GameEngine model) {
        this.model = model;
    }

    /***
     * moves pacman up
     */
    @Override
    public void execute() {
        model.moveUp();
        //System.out.println("upward key executed");
    }
}
