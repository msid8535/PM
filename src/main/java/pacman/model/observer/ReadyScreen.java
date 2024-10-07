package pacman.model.observer;

public class ReadyScreen implements Observer {
    public void update(String event, Object data) {
        if (event.equals("levelStart")) {

            // Render "Ready" on the screen
        }
    }
}
