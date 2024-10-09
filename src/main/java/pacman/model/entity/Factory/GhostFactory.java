package pacman.model.entity.Factory;
import pacman.model.entity.dynamic.ghost.GhostImpl;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.maze.MazeCreator;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.KinematicState;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.dynamic.physics.Direction;
import javafx.scene.image.Image;
import pacman.model.entity.dynamic.physics.KinematicStateImpl;

public class GhostFactory implements RenderableFactory {

    private final Image image;
    private final GhostMode ghostMode;
    private Vector2D targetCorner;

    public GhostFactory(Image image, GhostMode ghostMode, Vector2D targetCorner) {
        this.image = image;
        this.ghostMode = ghostMode;
        this.targetCorner = targetCorner;
    }

    @Override
    public Renderable createRenderable(int x, int y) {
        // Create the bounding box for the ghost
        BoundingBox boundingBox = new BoundingBoxImpl(new Vector2D(x, y), 20, 20);

        // Create the KinematicState using the builder
        KinematicState kinematicState = new KinematicStateImpl.KinematicStateBuilder()
                .setPosition(new Vector2D(x, y))
                .setSpeed(1.5)  // Example speed for ghost
                .setDirection(Direction.UP)  // Default initial direction for the ghost
                .build();

        // Create and return the Ghost entity
        return new GhostImpl(image, boundingBox, kinematicState, ghostMode, targetCorner, Direction.UP);
    }
}
