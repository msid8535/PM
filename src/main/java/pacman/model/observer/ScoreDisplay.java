package pacman.model.observer;

import javafx.scene.text.Text;
import pacman.model.GameState;

public class ScoreDisplay implements Observer {
    /**
    displays updated score
     */
    public void update(String event, Object data) {
        if (event.equals("scoreChange")) {
            GameState gameState = (GameState) data;
            int newScore = gameState.getScore();
            // Update the score display on the screen
            Text scoreDisplay = new Text();
            scoreDisplay.setText("Score: " + newScore);

        }
    }
}