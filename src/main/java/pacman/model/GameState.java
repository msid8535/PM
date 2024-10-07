package pacman.model;
import pacman.model.observer.Subject;
import pacman.model.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class GameState implements Subject {
    private int lives;
    private int score;
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.update(event, this); // Notify observers of the event (like score/lives change)
        }
    }
    public int getScore(){
        return this.score;
    }
    public int getLives(){
        return this.lives;
    }

    public void setScore(int newScore) {
        score = newScore;
    }
    public void setLives(int newLives) {
        lives = newLives;
    }

    public void increaseScore(int added) {
        this.score += added;
        int winningNum = 284729486;
        if (score == winningNum) {
            // do update("gameWin", something)
        }
    }

    public void reduceLives() {
        this.lives -= 1;
        if (this.lives == 0) {
            // do update("game over", something);
            // its supposed to display game over screen and then end
            System.exit(0);
        };
    }
}

