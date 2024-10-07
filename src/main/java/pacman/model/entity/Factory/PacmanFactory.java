package pacman.model.entity.Factory;

import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.*;
import javafx.scene.image.Image;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.dynamic.player.Pacman;
import pacman.model.entity.dynamic.player.PacmanVisual;
import pacman.model.maze.MazeCreator;

import java.util.Map;

import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.KinematicStateImpl;
import pacman.model.entity.dynamic.physics.KinematicState;

public class PacmanFactory implements RenderableFactory {

    private final Map<PacmanVisual, Image> images;

    public PacmanFactory(Map<PacmanVisual, Image> images) {
        this.images = images;
    }

    @Override
    public Renderable createRenderable(int x, int y) {
        // Create the bounding box for Pacman
        BoundingBox boundingBox = new BoundingBoxImpl(new Vector2D(x, y), MazeCreator.RESIZING_FACTOR, MazeCreator.RESIZING_FACTOR);

        // Create the KinematicState using the builder
        KinematicState kinematicState = new KinematicStateImpl.KinematicStateBuilder()
                .setPosition(new Vector2D(x, y))
                .setSpeed(1.0) // Example speed, adjust accordingly
                .setDirection(Direction.LEFT) // Default initial direction, adjust accordingly
                .build();

        // Create and return the Pacman entity
        return new Pacman(images.get(PacmanVisual.CLOSED), images, boundingBox, kinematicState);
    }
}
