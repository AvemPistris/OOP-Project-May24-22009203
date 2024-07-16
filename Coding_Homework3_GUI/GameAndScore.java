// Muhammad Solihin 22009201
// Adam Marwan 22009203

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameAndScore extends Application {
    private boolean xTurn = true;
    private Button[][] buttons = new Button[3][3];
    private Label statusLabel = new Label("X turn's");
    private int xScore = 0;
    private int oScore = 0;
    private int ties = 0;
    private List<WinRecord> winRecords = new ArrayList<>();
    private Scene gameScene;
    private Scene scoreboardScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setPrefHeight(524);
        root.setPrefWidth(900);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(550);
        anchorPane.setPrefWidth(900);

        Button startButton = new Button("Restart");
        startButton.setLayoutX(645);
        startButton.setLayoutY(250);
        startButton.setPrefHeight(26);
        startButton.setPrefWidth(220);
        startButton.setOnAction(event -> resetGame());

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setLayoutX(645);
        mainMenuButton.setLayoutY(200);
        mainMenuButton.setPrefHeight(26);
        mainMenuButton.setPrefWidth(220);
        mainMenuButton.setOnAction(event -> {
            primaryStage.hide();
            MainPage mainPage = new MainPage();
            Stage mainStage = new Stage();
            mainPage.start(mainStage);
        });

        Button scoreboardButton = new Button("Scoreboard");
        scoreboardButton.setLayoutX(645);
        scoreboardButton.setLayoutY(300);
        scoreboardButton.setPrefHeight(26);
        scoreboardButton.setPrefWidth(220);
        scoreboardButton.setOnAction(event -> updateScoreboard(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(292);
        gridPane.setLayoutY(50);
        gridPane.setPrefHeight(300);
        gridPane.setPrefWidth(300);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setPrefHeight(100);
                button.setPrefWidth(100);
                button.setOnAction(event -> handleButtonClick(button));
                buttons[row][col] = button;
                gridPane.add(button, col, row);

                primaryStage.setTitle("Tic Tac Toe");
            }
        }

        Label titleLabel = new Label("Tic Tac Toe");
        titleLabel.setLayoutX(23);
        titleLabel.setLayoutY(20);
        titleLabel.setPrefHeight(39);
        titleLabel.setPrefWidth(158);
        titleLabel.setFont(new Font(30));

        statusLabel.setLayoutX(23);
        statusLabel.setLayoutY(66);
        statusLabel.setPrefHeight(20);
        statusLabel.setPrefWidth(100);
        statusLabel.setFont(new Font(14));

        anchorPane.getChildren().addAll(startButton, mainMenuButton, scoreboardButton, gridPane, titleLabel, statusLabel);
        root.getChildren().add(anchorPane);

        gameScene = new Scene(root, 900, 400);

        // Scoreboard scene
        VBox scoreboardRoot = new VBox();
        scoreboardRoot.setPrefHeight(524);
        scoreboardRoot.setPrefWidth(900);

        AnchorPane scoreboardPane = new AnchorPane();
        scoreboardPane.setPrefHeight(550);
        scoreboardPane.setPrefWidth(900);

        Label scoreboardTitle = new Label("Scoreboard");
        scoreboardTitle.setLayoutX(21);
        scoreboardTitle.setLayoutY(17);
        scoreboardTitle.setPrefHeight(39);
        scoreboardTitle.setPrefWidth(158);
        scoreboardTitle.setFont(new Font(32));

        TableView<WinRecord> tableView = new TableView<>();
        tableView.setLayoutX(200);
        tableView.setLayoutY(100);
        tableView.setPrefHeight(266);
        tableView.setPrefWidth(500);
        tableView.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        TableColumn<WinRecord, Integer> xScoreColumn = new TableColumn<>("X Score");
        xScoreColumn.setCellValueFactory(new PropertyValueFactory<>("xScore"));
        xScoreColumn.setPrefWidth(150);

        TableColumn<WinRecord, Integer> oScoreColumn = new TableColumn<>("O Score");
        oScoreColumn.setCellValueFactory(new PropertyValueFactory<>("oScore"));
        oScoreColumn.setPrefWidth(150);

        TableColumn<WinRecord, String> resultColumn = new TableColumn<>("Result");
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        resultColumn.setPrefWidth(200);

        tableView.getColumns().add(xScoreColumn);
        tableView.getColumns().add(oScoreColumn);
        tableView.getColumns().add(resultColumn);
        tableView.getItems().addAll(winRecords);

        Label xScoreLabel = new Label("X Wins: " + xScore);
        xScoreLabel.setLayoutX(21);
        xScoreLabel.setLayoutY(81);
        xScoreLabel.setFont(new Font(14));

        Label oScoreLabel = new Label("O Wins: " + oScore);
        oScoreLabel.setLayoutX(21);
        oScoreLabel.setLayoutY(117);
        oScoreLabel.setFont(new Font(14));

        Label tieLabel = new Label("Ties: " + ties);
        tieLabel.setLayoutX(21);
        tieLabel.setLayoutY(153);
        tieLabel.setFont(new Font(14));

        Button backButton = new Button("Back to Game");
        backButton.setLayoutX(14);
        backButton.setLayoutY(366);
        backButton.setPrefHeight(26);
        backButton.setPrefWidth(220);
        backButton.setOnAction(event -> primaryStage.setScene(gameScene));

        scoreboardPane.getChildren().addAll(scoreboardTitle, tableView, backButton, xScoreLabel, oScoreLabel, tieLabel);
        scoreboardRoot.getChildren().add(scoreboardPane);

        primaryStage.getIcons().add(new Image("logo.png"));

        scoreboardScene = new Scene(scoreboardRoot, 900, 400);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    private void handleButtonClick(Button button) {
        if (button.getText().isEmpty()) {
            if (xTurn) {
                button.setText("X");
                statusLabel.setText("O turn's");
            } else {
                button.setText("O");
                statusLabel.setText("X turn's");
            }
            xTurn = !xTurn;
            checkWinner();
        }
    }

    private void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][1].getText().equals(buttons[i][2].getText()) && !buttons[i][0].getText().isEmpty()) {
                showWinner(buttons[i][0].getText());
                return;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[1][i].getText().equals(buttons[2][i].getText()) && !buttons[0][i].getText().isEmpty()) {
                showWinner(buttons[0][i].getText());
                return;
            }
        }
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText()) && !buttons[0][0].getText().isEmpty()) {
            showWinner(buttons[0][0].getText());
            return;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText()) && !buttons[0][2].getText().isEmpty()) {
            showWinner(buttons[0][2].getText());
            return;
        }
        if (isBoardFull()) {
            showWinner("Tie");
        }
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinner(String winner) {
        statusLabel.setText(winner.equals("Tie") ? "It's a Tie!" : winner + " wins!");
        disableButtons();
        if (winner.equals("X")) {
            xScore++;
            winRecords.add(new WinRecord(xScore, oScore, "X wins"));
        } else if (winner.equals("O")) {
            oScore++;
            winRecords.add(new WinRecord(xScore, oScore, "O wins"));
        } else {
            ties++;
            winRecords.add(new WinRecord(xScore, oScore, "Tie"));
        }
    }

    private void disableButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setDisable(true);
            }
        }
    }

    private void resetGame() {
        xTurn = true;
        statusLabel.setText("X turn's");
        resetBoard();
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setDisable(false);
            }
        }
    }

    private void updateScoreboard(Stage primaryStage) {
        primaryStage.setTitle("Scoreboard");
        TableView<WinRecord> tableView = new TableView<>();
        tableView.setLayoutX(200);
        tableView.setLayoutY(100);
        tableView.setPrefHeight(250);
        tableView.setPrefWidth(500);
        tableView.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        TableColumn<WinRecord, Integer> xScoreColumn = new TableColumn<>("X Score");
        xScoreColumn.setCellValueFactory(new PropertyValueFactory<>("xScore"));
        xScoreColumn.setPrefWidth(150);

        TableColumn<WinRecord, Integer> oScoreColumn = new TableColumn<>("O Score");
        oScoreColumn.setCellValueFactory(new PropertyValueFactory<>("oScore"));
        oScoreColumn.setPrefWidth(150);

        TableColumn<WinRecord, String> resultColumn = new TableColumn<>("Result");
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        resultColumn.setPrefWidth(200);

        tableView.getColumns().add(xScoreColumn);
        tableView.getColumns().add(oScoreColumn);
        tableView.getColumns().add(resultColumn);
        tableView.getItems().addAll(winRecords);

        Label xScoreLabel = new Label("X Wins: " + xScore);
        xScoreLabel.setLayoutX(21);
        xScoreLabel.setLayoutY(81);
        xScoreLabel.setFont(new Font(14));

        Label oScoreLabel = new Label("O Wins: " + oScore);
        oScoreLabel.setLayoutX(21);
        oScoreLabel.setLayoutY(117);
        oScoreLabel.setFont(new Font(14));

        Label tieLabel = new Label("Ties: " + ties);
        tieLabel.setLayoutX(21);
        tieLabel.setLayoutY(153);
        tieLabel.setFont(new Font(14));

        Button backButton = new Button("Back to Game");
        backButton.setLayoutX(14);
        backButton.setLayoutY(366);
        backButton.setPrefHeight(26);
        backButton.setPrefWidth(220);
        backButton.setOnAction(event -> primaryStage.setScene(gameScene));

        VBox scoreboardRoot = new VBox();
        scoreboardRoot.setPrefHeight(524);
        scoreboardRoot.setPrefWidth(900);

        AnchorPane scoreboardPane = new AnchorPane();
        scoreboardPane.setPrefHeight(550);
        scoreboardPane.setPrefWidth(900);

        Label scoreboardTitle = new Label("Scoreboard");
        scoreboardTitle.setLayoutX(21);
        scoreboardTitle.setLayoutY(17);
        scoreboardTitle.setPrefHeight(39);
        scoreboardTitle.setPrefWidth(158);
        scoreboardTitle.setFont(new Font(31));

        scoreboardPane.getChildren().addAll(scoreboardTitle, tableView, backButton, xScoreLabel, oScoreLabel, tieLabel);
        scoreboardRoot.getChildren().add(scoreboardPane);

        scoreboardScene = new Scene(scoreboardRoot, 900, 400);
        primaryStage.setScene(scoreboardScene);
    }

    public static class WinRecord {
        private final int xScore;
        private final int oScore;
        private final String result;

        public WinRecord(int xScore, int oScore, String result) {
            this.xScore = xScore;
            this.oScore = oScore;
            this.result = result;
        }

        public int getXScore() {
            return xScore;
        }

        public int getOScore() {
            return oScore;
        }

        public String getResult() {
            return result;
        }
    }
}


