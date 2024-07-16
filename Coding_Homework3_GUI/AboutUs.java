// Muhammad Solihin 22009201

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AboutUs extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("About Us");

        // Create the main VBox as the root layout
        VBox root = new VBox();
        root.setPrefHeight(400.0);
        root.setPrefWidth(900.0);

        // Create the AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinHeight(400.0);
        anchorPane.setMinWidth(900.0);
        anchorPane.setMaxHeight(400.0);
        anchorPane.setMaxWidth(900.0);

        // Create and configure the labels
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


        // Create the Back button
        Button backButton = new Button("Back");
        backButton.setPrefHeight(26.0);
        backButton.setPrefWidth(220.0);
        AnchorPane.setBottomAnchor(backButton, 20.0);
        AnchorPane.setLeftAnchor(backButton, 20.0);

        backButton.setOnAction(e -> {
            // Close the current stage (About Us window)
            primaryStage.hide();

            // Create a new instance of MainPage and start it
            MainPage mainPage = new MainPage();
            Stage mainStage = new Stage();
            mainPage.start(mainStage);
        });

        // Add children to the AnchorPane
        anchorPane.getChildren().addAll(label, missionLabel, missionDescription, whyLabel, whyDescription, backButton);

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
