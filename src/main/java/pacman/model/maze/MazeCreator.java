package pacman.model.maze;

import pacman.model.entity.Factory.PelletFactory;
import pacman.model.entity.Renderable;
import pacman.model.entity.Factory.WallFactory;
import pacman.model.entity.Factory.GhostFactory;
import pacman.model.entity.dynamic.ghost.GhostMode;
import pacman.model.entity.dynamic.physics.Vector2D;
import pacman.model.entity.Factory.PacmanFactory;
import pacman.model.entity.dynamic.player.PacmanVisual;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serial;
import java.util.*;
import java.util.ArrayList;
import static java.lang.System.exit;
import java.util.Random;

/**
 * Responsible for creating renderables and storing it in the Maze
 */
public class MazeCreator {

    private final String fileName;
    public static final int RESIZING_FACTOR = 16;
    public HashMap<PacmanVisual, Image> pacmanImages = new HashMap<>();

    //wall images
    public final Image horImage = new Image(getClass().getResourceAsStream("/maze/walls/horizontal.png"));
    public final Image verImage = new Image(getClass().getResourceAsStream("/maze/walls/vertical.png"));
    public final Image upLeftImage = new Image(getClass().getResourceAsStream("/maze/walls/upLeft.png"));
    public final Image upRightImage = new Image(getClass().getResourceAsStream("/maze/walls/upRight.png"));
    public final Image downLeft = new Image(getClass().getResourceAsStream("/maze/walls/downLeft.png"));
    public final Image downRight = new Image(getClass().getResourceAsStream("/maze/walls/downRight.png"));

    //ghost image
    public final Image ghostImage = new Image(getClass().getResourceAsStream("/maze/ghosts/ghost.png"));
    //pacman images
    public final Image closedPacman = new Image(getClass().getResourceAsStream("/maze/pacman/playerClosed.png"));
    public final Image downPacman = new Image(getClass().getResourceAsStream("/maze/pacman/playerDown.png"));
    public final Image leftPacman = new Image(getClass().getResourceAsStream("/maze/pacman/playerLeft.png"));
    public final Image rightPacman = new Image(getClass().getResourceAsStream("/maze/pacman/playerRight.png"));
    public final Image upPacman = new Image(getClass().getResourceAsStream("/maze/pacman/playerUp.png"));


    //corner vectors
    public Vector2D firstCorner;
    public Vector2D secondCorner;
    public Vector2D thirdCorner;
    public Vector2D fourthCorner;



    //pellet
    public final Image pelletImage = new Image(getClass().getResourceAsStream("/maze/pellet.png"));

    public MazeCreator(String fileName) {
        this.fileName = fileName;
    }


    public Maze createMaze() {
        File f = new File(this.fileName);
        Maze maze = new Maze();
        //Vector2D pacmanPos = maze.getControllable().getPosition();
        try {
            Scanner scanner = new Scanner(f);
            pacmanImages.put(PacmanVisual.CLOSED, closedPacman);
            pacmanImages.put(PacmanVisual.UP, upPacman);
            pacmanImages.put(PacmanVisual.DOWN, downPacman);
            pacmanImages.put(PacmanVisual.LEFT, leftPacman);
            pacmanImages.put(PacmanVisual.RIGHT, rightPacman);
            this.firstCorner = new Vector2D(64, 0);
            this.secondCorner = new Vector2D(440, 0);
            this.thirdCorner = new Vector2D(0, 900);
            this.fourthCorner = new Vector2D(440, 900);
            int y = 0;
            int z = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char[] row = line.toCharArray();

                for (int x = 0; x < row.length; x++) {
                    /**
                     * TO DO: Implement Factory Method Pattern
                     */
                    char renderableType = row[x];
                    Renderable renderable = createRenderable(renderableType, x, y, z);
                    maze.addRenderable(renderable, renderableType, x, y);
                    if (renderableType == 'g') {
                        z += 1;
                    }



                }

                y += 1;
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No maze file was found.");
            exit(0);
        } catch (Exception e) {
            System.out.println("Error");
            //e.printStackTrace(System.out);
            exit(0);
        }

        return maze;
    }
    //helper function to send createRenderable call to appropriate factory (pellets, ghosts, pacman or walls)
    private Renderable createRenderable(char renderableType, int x, int y, int z) {
        int xPosition = x * RESIZING_FACTOR;
        int yPosition = y * RESIZING_FACTOR;
        int random = 0;

        switch (renderableType) {
            case 'p':
                return new PacmanFactory(pacmanImages).createRenderable(xPosition, yPosition);
            case 'g':
                if (z == 0) { //assigns to first corner
                    return new GhostFactory(ghostImage, GhostMode.getNextGhostMode(GhostMode.SCATTER), firstCorner).createRenderable(xPosition, yPosition);
                } else if (z == 1) { //second corner
                    return new GhostFactory(ghostImage, GhostMode.getNextGhostMode(GhostMode.SCATTER), secondCorner).createRenderable(xPosition, yPosition);
                } else if (z == 2) { //third corner
                    return new GhostFactory(ghostImage, GhostMode.getNextGhostMode(GhostMode.SCATTER), thirdCorner).createRenderable(xPosition, yPosition);
                } else { // fourth corner
                    return new GhostFactory(ghostImage, GhostMode.getNextGhostMode(GhostMode.SCATTER), fourthCorner).createRenderable(xPosition, yPosition);
                }
            case '7':
                return new PelletFactory(pelletImage, Renderable.Layer.BACKGROUND, 0).createRenderable(xPosition, yPosition);
            case '1':
                return new WallFactory(horImage).createRenderable(xPosition, yPosition);
            case '2':
                return new WallFactory(verImage).createRenderable(xPosition, yPosition);
            case '3':
                return new WallFactory(upLeftImage).createRenderable(xPosition, yPosition);
            case '4':
                return new WallFactory(upRightImage).createRenderable(xPosition, yPosition);
            case '5':
                return new WallFactory(downLeft).createRenderable(xPosition, yPosition);
            case '6':
                return new WallFactory(downRight).createRenderable(xPosition, yPosition);
            default:
                return null;
        }
    }
}
