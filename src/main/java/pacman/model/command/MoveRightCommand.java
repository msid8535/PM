package pacman.model.command;

import pacman.model.engine.GameEngine;

public class MoveRightCommand implements Command {
    private GameEngine model;

    public MoveRightCommand(GameEngine model) {
        this.model = model;
    }

    /***
     * moves pacman right
     */
    @Override
    public void execute() {
        model.moveRight();
        //System.out.println("rightward key executed");
    }
}