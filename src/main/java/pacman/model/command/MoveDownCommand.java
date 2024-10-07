package pacman.model.command;

import pacman.model.engine.GameEngine;

public class MoveDownCommand implements Command {
    private GameEngine model;

    public MoveDownCommand(GameEngine model) {
        this.model = model;
    }

    /***
     * moves pacman down
     */
    @Override
    public void execute() {
        model.moveDown();
        //System.out.println("downward key executed");
    }
}