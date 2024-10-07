package pacman.model.observer;

public class WinScreen implements Observer {
    public void update(String event, Object data) {
        if (event.equals("gameWin")) {
            // Render "You Win!" on the screen
        }
    }
}
