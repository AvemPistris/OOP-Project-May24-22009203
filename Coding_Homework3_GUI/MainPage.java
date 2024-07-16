// Safiqur Rahman 22008929
// Danish Harith 22009489

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainPage extends Application {

    private static MainPage instance;
    private VBox root;
    private Stage primaryStage;

    public MainPage() {
        instance = this;
        this.root = new VBox();
    }

    public static MainPage getInstance() {
        return instance;
    }

    public void applyBackgroundColor(Color color) {
        root.setStyle("-fx-background-color: #" + toHexString(color) + ";");
    }

    public VBox getRoot() {
        return root;
    }

    private String toHexString(Color color) {
        return String.format("%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tic Tac Toe");

        Pane mainContent = new Pane();

        Button playButton = new Button("Play");
        playButton.setLayoutX(550);
        playButton.setLayoutY(90);
        playButton.setPrefSize(220, 26);
        playButton.setOnAction(event -> {
            primaryStage.hide();
            GameAndScore board = new GameAndScore();
            Stage scoreboardStage = new Stage();
            board.start(scoreboardStage);
        });

        Button helpButton = new Button("How to Play");
        helpButton.setLayoutX(550);
        helpButton.setLayoutY(140);
        helpButton.setPrefSize(220, 26);
        helpButton.setOnAction(event -> showHelpPage());

        Button settingsButton = new Button("Settings");
        settingsButton.setLayoutX(550);
        settingsButton.setLayoutY(190);
        settingsButton.setPrefSize(220, 26);
        settingsButton.setOnAction(event -> {
            primaryStage.hide();
            Settings settings = new Settings();
            Stage settingsStage = new Stage();
            settings.start(settingsStage);
        });

        Button aboutUsButton = new Button("About Us");
        aboutUsButton.setLayoutX(550);
        aboutUsButton.setLayoutY(240);
        aboutUsButton.setPrefSize(220, 26);
        aboutUsButton.setOnAction(event -> showAboutUsPage());

        Button exitButton = new Button("Exit");
        exitButton.setLayoutX(550);
        exitButton.setLayoutY(290);
        exitButton.setPrefSize(220, 26);
        exitButton.setOnAction(event -> primaryStage.close());

        Label titleLabel = new Label("TIC-TAC-TOE");
        titleLabel.setFont(new Font("Verdana", 42));
        titleLabel.setLayoutX(128);
        titleLabel.setLayoutY(230);

        Label statement = new Label("Strategize Your Way to Victory!");
        statement.setFont(new Font("Verdana", 14));
        statement.setLayoutX(128);
        statement.setLayoutY(280);

        ImageView imageView = new ImageView(new Image("logo.png"));
        imageView.setX(170);
        imageView.setY(60);
        imageView.setFitWidth(160);
        imageView.setFitHeight(160);

        mainContent.getChildren().addAll(playButton, helpButton, settingsButton, aboutUsButton, exitButton, titleLabel, statement, imageView);

        root.getChildren().add(mainContent);

        Scene scene = new Scene(root, 900, 400);

        primaryStage.getIcons().add(new Image("logo.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    private void showHelpPage() {
        Stage helpStage = new Stage();
        helpStage.setTitle("How To Play");

        VBox root = new VBox();
        root.setPrefHeight(300.0);
        root.setPrefWidth(640.0);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinHeight(400.0);
        anchorPane.setMinWidth(900.0);
        anchorPane.setMaxHeight(400.0);

        Label label = new Label("How To Play");
        label.setFont(new Font(32));
        AnchorPane.setTopAnchor(label, 17.0);
        AnchorPane.setLeftAnchor(label, 21.0);

        Label description1 = new Label("Classic Tic-Tac-Toe, place your X's or O's and try to get three in a row and prevent your ");
        description1.setFont(new Font(18));
        AnchorPane.setTopAnchor(description1, 64.0);
        AnchorPane.setLeftAnchor(description1, 21.0);

        Label description2 = new Label("opponent from getting three in a row.");
        description2.setFont(new Font(18));
        AnchorPane.setTopAnchor(description2, 91.0);
        AnchorPane.setLeftAnchor(description2, 21.0);

        ImageView imageView = new ImageView("Tutorial.png");
        imageView.setFitHeight(204.0);
        imageView.setFitWidth(845.0);
        AnchorPane.setTopAnchor(imageView, 131.0);
        AnchorPane.setLeftAnchor(imageView, 25.0);
        imageView.setPreserveRatio(true);

        Button backButton = new Button("Back");
        backButton.setPrefHeight(26.0);
        backButton.setPrefWidth(220.0);
        AnchorPane.setBottomAnchor(backButton, 25.0);
        AnchorPane.setLeftAnchor(backButton, 25.0);

        backButton.setOnAction(e -> helpStage.close());

        anchorPane.getChildren().addAll(label, description1, description2, imageView, backButton);
        root.getChildren().add(anchorPane);

        Scene scene = new Scene(root, 900, 400);
        helpStage.getIcons().add(new Image("logo.png"));
        helpStage.setScene(scene);
        helpStage.show();
    }

    private void showAboutUsPage() {
        Stage aboutUsStage = new Stage();
        aboutUsStage.setTitle("About Us");

        VBox root = new VBox();
        root.setPrefHeight(400.0);
        root.setPrefWidth(900.0);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinHeight(400.0);
        anchorPane.setMinWidth(900.0);
        anchorPane.setMaxHeight(400.0);
        anchorPane.setMaxWidth(900.0);

        Label label = new Label("About Us");
        label.setFont(new Font(32));
        AnchorPane.setTopAnchor(label, 20.0);
        AnchorPane.setLeftAnchor(label, 20.0);

        Label missionLabel = new Label("Our Mission:");
        missionLabel.setFont(new Font(24));
        AnchorPane.setTopAnchor(missionLabel, 80.0);
        AnchorPane.setLeftAnchor(missionLabel, 20.0);

        Label missionDescription = new Label("To create fun and accessible games that bring joy and foster friendly competition among friends and family.");
        missionDescription.setFont(new Font(18));
        missionDescription.setWrapText(true);
        AnchorPane.setTopAnchor(missionDescription, 120.0);
        AnchorPane.setLeftAnchor(missionDescription, 20.0);
        AnchorPane.setRightAnchor(missionDescription, 20.0);

        Label whyLabel = new Label("Why Tic-Tac-Toe?");
        whyLabel.setFont(new Font(24));
        AnchorPane.setTopAnchor(whyLabel, 170.0);
        AnchorPane.setLeftAnchor(whyLabel, 20.0);

        Label whyDescription = new Label("Tic-Tac-Toe is a timeless game that has been enjoyed by generations. Its simplicity, combined with the strategic depth, makes it a perfect choice for both casual play and competitive challenges. We wanted to preserve the classic essence while adding modern touches to enhance your experience.");
        whyDescription.setFont(new Font(18));
        whyDescription.setWrapText(true);
        AnchorPane.setTopAnchor(whyDescription, 210.0);
        AnchorPane.setLeftAnchor(whyDescription, 20.0);
        AnchorPane.setRightAnchor(whyDescription, 20.0);

        Button backButton = new Button("Back");
        backButton.setPrefHeight(26.0);
        backButton.setPrefWidth(220.0);
        AnchorPane.setBottomAnchor(backButton, 20.0);
        AnchorPane.setLeftAnchor(backButton, 20.0);

        backButton.setOnAction(e -> aboutUsStage.close());

        anchorPane.getChildren().addAll(label, missionLabel, missionDescription, whyLabel, whyDescription, backButton);
        root.getChildren().add(anchorPane);

        Scene scene = new Scene(root, 900, 400);
        aboutUsStage.getIcons().add(new Image("logo.png"));
        aboutUsStage.setScene(scene);
        aboutUsStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
