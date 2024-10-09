package pacman;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pacman.model.GameState;
import pacman.model.engine.GameEngine;
import pacman.model.engine.GameEngineImpl;
import pacman.model.observer.*;
import pacman.view.GameWindow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class App extends Application {
    public int numScore = 70;
    public int lives = 3;
    public static List<Observer> observers;

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
        observers = gameState.getObservers();

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
        //score
        Text scoreDisplay = new Text();
        scoreDisplay.setText("Score: " + numScore);
        scoreDisplay.setFill(Color.YELLOW);
        Text ready = new Text();
        ready.setText("READY");
        ready.setFill(Color.RED);

        try {
            Font customFont = Font.loadFont(new FileInputStream("src/main/resources/maze/PressStart2P-Regular.ttf"), 10); // Size 10
            scoreDisplay.setFont(customFont);  // Apply the custom font
            ready.setFont(customFont);
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Handle if the font file is not found
        }

        //lives display at bottom
        Image pacmanLife1 = new Image("file:src/main/resources/maze/pacman/playerRight.png");
        Image pacmanLife2 = new Image("file:src/main/resources/maze/pacman/playerRight.png");
        Image pacmanLife3 = new Image("file:src/main/resources/maze/pacman/playerRight.png");
        ImageView life1View = new ImageView(pacmanLife1);
        ImageView life2View = new ImageView(pacmanLife2);
        ImageView life3View = new ImageView(pacmanLife3);

        VBox scoreBox = new VBox(10);
        scoreBox.getChildren().addAll(scoreDisplay);
        //VBox readyBox = new VBox(10);
        //readyBox.getChildren().addAll(ready);
        //readyBox.setLayoutX(200);
        //readyBox.setLayoutY(288);
        // Create HBox to display the lives horizontally
        HBox livesBox = new HBox(10); // 10 px spacing between images
        if (lives == 3) {
            livesBox.getChildren().addAll(life1View, life2View, life3View);
        } else if (lives == 2) {
            livesBox.getChildren().addAll(life1View, life2View, life3View);
        } else if (lives == 1) {
            livesBox.getChildren().addAll(life1View);
        } else {
            //don't add statusBox
        }
        // Create a VBox to display lives at the bottom
        VBox statusBox = new VBox(10); // 10 px spacing between score and lives
        statusBox.getChildren().addAll(livesBox);
        statusBox.setLayoutX(10);
        statusBox.setLayoutY(544);
        // Add the VBox to the GameWindow pane
        window.getPane().getChildren().add(scoreBox);
        //window.getPane().getChildren().add(readyBox);
        window.getPane().getChildren().add(statusBox);

        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(window.getScene());
        primaryStage.show();
        model.startGame();
        window.run();
    }

    public void reduceLife() {
        lives -= 1;
    }

    public void increaseScore() {
        numScore += 10;
    }
}