package pacman.model.observer;

public class GameOverScreen implements Observer {
    public void update(String event, Object data) {
        if (event.equals("gameOver")) {
            // Render "Game Over" on the screen

        }
    }
}
