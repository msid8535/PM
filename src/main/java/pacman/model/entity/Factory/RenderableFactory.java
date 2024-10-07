package pacman.model.entity.Factory;

import pacman.model.entity.Renderable;

public interface RenderableFactory {
    Renderable createRenderable(int x, int y);  // Pass coordinates for placement
}

