package pacman.model.observer;

import pacman.model.GameState;

public class ScoreDisplay implements Observer {
    public void update(String event, Object data) {
        if (event.equals("scoreChange")) {
            GameState gameState = (GameState) data;
            int newScore = gameState.getScore();
            // Update the score display on the screen

        }
    }
}