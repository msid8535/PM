package pacman.model.entity.Factory;
import pacman.model.entity.Renderable;
import pacman.model.entity.dynamic.physics.BoundingBox;
import pacman.model.entity.dynamic.physics.BoundingBoxImpl;
import pacman.model.entity.dynamic.physics.Vector2D;
import javafx.scene.image.Image;
import pacman.model.entity.staticentity.StaticEntityImpl;
import pacman.model.maze.MazeCreator;

public class WallFactory implements RenderableFactory {

    private final Image wallImage;

    public WallFactory(Image wallImage) {
        this.wallImage = wallImage;
    }

    @Override
    public Renderable createRenderable(int x, int y) {
        BoundingBox boundingBox = new BoundingBoxImpl(new Vector2D(x, y), MazeCreator.RESIZING_FACTOR, MazeCreator.RESIZING_FACTOR);
        return new StaticEntityImpl(boundingBox, Renderable.Layer.FOREGROUND, wallImage);
    }
}

