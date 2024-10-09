package pacman.model.state;
import pacman.model.entity.dynamic.ghost.GhostImpl;
import pacman.model.entity.dynamic.ghost.GhostMode;

public class ScatterState implements GhostState {
    @Override
    public void execute(GhostImpl ghost) {
        System.out.println("Ghost is in scatter-mode.");
        ghost.setGhostMode(GhostMode.SCATTER);
    }
}