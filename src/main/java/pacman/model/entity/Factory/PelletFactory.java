package pacman.model.entity.Factory;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.Renderable;
import pacman.model.entity.Renderable.Layer;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import javafx.scene.image.Image;
import pacman.model.entity.staticentity.collectable.Pellet;
import pacman.model.maze.MazeCreator;

public class PelletFactory implements RenderableFactory {

    private final Image image;
    private final Layer layer;
    private final int points;

    public PelletFactory(Image image, Layer layer, int points) {
        this.image = image;
        this.layer = layer;
        this.points = points;
    }

    @Override
    public Renderable createRenderable(int x, int y) {
        BoundingBox boundingBox = new BoundingBoxImpl(new Vector2D(x, y), MazeCreator.RESIZING_FACTOR, MazeCreator.RESIZING_FACTOR);
        return new Pellet(boundingBox, layer, image, points);
    }
}

