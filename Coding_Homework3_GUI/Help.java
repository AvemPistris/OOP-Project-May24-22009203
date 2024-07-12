import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Help extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("How To Play");

        // Create the main VBox as the root layout
        VBox root = new VBox();
        root.setPrefHeight(300.0);
        root.setPrefWidth(640.0);

        // Create the AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinHeight(400.0);
        anchorPane.setMinWidth(900.0);
        anchorPane.setMaxHeight(400.0);

        // Create and configure the labels
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

        // Create the ImageView
        ImageView imageView = new ImageView("Tutorial.png");
        imageView.setFitHeight(204.0);
        imageView.setFitWidth(845.0);
        AnchorPane.setTopAnchor(imageView, 131.0);
        AnchorPane.setLeftAnchor(imageView, 25.0);
        imageView.setPreserveRatio(true);

        // Create the Back button
        Button backButton = new Button("Back");
        backButton.setPrefHeight(26.0);
        backButton.setPrefWidth(220.0);
        AnchorPane.setBottomAnchor(backButton, 25.0);
        AnchorPane.setLeftAnchor(backButton, 25.0);

        backButton.setOnAction(e -> {
            // Close the current stage (Help window)
            primaryStage.hide();

            // Create a new instance of MainPage and start it
            MainPage mainPage = new MainPage();
            Stage mainStage = new Stage();
            mainPage.start(mainStage);
        });

        // Add children to the AnchorPane
        anchorPane.getChildren().addAll(label, description1, description2, imageView, backButton);

        // Add the AnchorPane to the VBox
        root.getChildren().add(anchorPane);

        // Create a scene with the root layout
        Scene scene = new Scene(root, 900, 400);

        primaryStage.getIcons().add(new Image("logo.png"));

        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
