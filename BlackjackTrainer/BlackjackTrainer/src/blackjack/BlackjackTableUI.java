// BlackjackTableUI.java
package blackjack;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class BlackjackTableUI {
    private final GameController controller;
    private final Stage stage;
    private final int delayMillis;
    private VBox root;
    private VBox playerBox;
    private HBox dealerBox;
    private Label prompt;
    private TextField input;
    private Button submit;
    private Label result;

    public BlackjackTableUI(Stage stage, int decks, int players, String difficulty) {
        this.stage = stage;
        this.controller = new GameController(decks, players);
        this.delayMillis = switch (difficulty.toLowerCase()) {
            case "easy" -> 2000;
            case "medium" -> 1000;
            case "hard" -> 500;
            default -> 1000;
        };
    }

    public void show() {
        root = new VBox(10);
        root.setStyle("-fx-background-color: green; -fx-padding: 20;");

        dealerBox = new HBox(10);
        root.getChildren().add(new Label("Dealer:"));
        root.getChildren().add(dealerBox);

        playerBox = new VBox(10);
        root.getChildren().add(new Label("Players:"));
        root.getChildren().add(playerBox);

        prompt = new Label("Enter running count:");
        input = new TextField();
        submit = new Button("Submit");
        result = new Label();

        HBox inputBox = new HBox(10, prompt, input, submit);
        root.getChildren().addAll(inputBox, result);

        submit.setOnAction(e -> {
            try {
                int guess = Integer.parseInt(input.getText());
                controller.submitGuess(guess);
                int actual = controller.getRunningCount();
                result.setText("Correct count: " + actual);
                if (controller.isGameOver()) {
                    showFinalScore();
                } else {
                    PauseTransition pause = new PauseTransition(Duration.millis(1500));
                    pause.setOnFinished(ev -> startNextHand());
                    pause.play();
                }
            } catch (NumberFormatException ex) {
                result.setText("Enter a valid number.");
            }
        });

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Blackjack Table");
        stage.show();

        startNextHand();
    }

    private void startNextHand() {
        controller.resetHands();
        playerBox.getChildren().clear();
        dealerBox.getChildren().clear();
        input.clear();
        result.setText("");

        controller.dealInitialCards();
        renderHands();

        PauseTransition dealPause = new PauseTransition(Duration.millis(delayMillis));
        dealPause.setOnFinished(e -> {
            controller.playRound();
            renderHands();
        });
        dealPause.play();
    }

    private void renderHands() {
        dealerBox.getChildren().clear();
        for (Card card : controller.getDealer().getHand()) {
            dealerBox.getChildren().add(new Label(card.toString()));
        }

        playerBox.getChildren().clear();
        List<Player> players = controller.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            VBox playerHand = new VBox();
            playerHand.getChildren().add(new Label("Player " + (i + 1)));
            for (Card card : p.getHand()) {
                playerHand.getChildren().add(new Label(card.toString()));
            }
            playerBox.getChildren().add(playerHand);
        }
    }

    private void showFinalScore() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Session Complete");
        alert.setHeaderText("Game Over");
        alert.setContentText("Your Score: " + controller.getScorePercentage() + "% correct");
        alert.showAndWait();
        Platform.exit();
    }
}
