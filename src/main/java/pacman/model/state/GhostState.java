package pacman.model.state;
import pacman.model.entity.dynamic.ghost.GhostImpl;


public interface GhostState {
    void execute(GhostImpl ghost);
}