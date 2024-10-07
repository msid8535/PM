package pacman.model.state;
import pacman.model.entity.dynamic.ghost.GhostImpl;

public class ChaseState implements GhostState {
    @Override
    public void execute(GhostImpl ghost) {
        System.out.println("Ghost is chasing Pac-man.");
    }


}
