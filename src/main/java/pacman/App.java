package pacman;

import javafx.application.Application;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pacman.model.GameState;
import pacman.model.engine.GameEngine;
import pacman.model.engine.GameEngineImpl;
import pacman.model.observer.*;
import pacman.view.GameWindow;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
        GameState gameState = new GameState();
        ReadyScreen readyScreen = new ReadyScreen();
        GameOverScreen gameOverScreen = new GameOverScreen();
        WinScreen winScreen = new WinScreen();
        ScoreDisplay scoreDisplay = new ScoreDisplay();
        LivesDisplay livesDisplay = new LivesDisplay();

        gameState.addObserver(readyScreen);
        gameState.addObserver(gameOverScreen);
        gameState.addObserver(winScreen);
        gameState.addObserver(scoreDisplay);
        gameState.addObserver(livesDisplay);

// Modify game state as game progresses
        gameState.setScore(0);
        gameState.notifyObservers("scoreChange");

        gameState.setLives(3);
        gameState.notifyObservers("livesChange");

    }

    @Override
    public void start(Stage primaryStage) {
        GameEngine model = new GameEngineImpl("src/main/resources/config.json");
        GameWindow window = new GameWindow(model, 448, 576);
        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(window.getScene());
        Text t = new Text();
        //primaryStage.setX("This is a text sample");
        
        primaryStage.show();
        model.startGame();
        window.run();
    }
}