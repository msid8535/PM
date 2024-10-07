package pacman.model.observer;

import pacman.model.GameState;

public class LivesDisplay implements Observer {
    public void update(String event, Object data) {
        if (event.equals("livesChange")) {
            GameState gameState = (GameState) data;
            int lives = gameState.getLives();
            // Update the lives display on the screen
        }
    }
}
