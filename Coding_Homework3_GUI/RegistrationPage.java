// Muhammad Amin Zufar 24000162

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegistrationPage extends Application {

    private TextField firstNameField, lastNameField;
    private PasswordField passwordField;
    private Label messageLabel;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Registration Page");

        // Set the same icon as the MainPage
        primaryStage.getIcons().add(new Image("logo.png"));

        Pane mainContent = new Pane();

        // Creating labels
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label passwordLabel = new Label("Password:");

        // Creating text fields
        firstNameField = new TextField();
        lastNameField = new TextField();
        passwordField = new PasswordField();

        // Creating message label for validation messages
        messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);

        // Creating buttons
        Button registerButton = new Button("Register");
        Button resetButton = new Button("Reset");
        Button cancelButton = new Button("Cancel");

        // Setting event handlers for buttons
        registerButton.setOnAction(e -> handleRegister());
        resetButton.setOnAction(e -> handleReset());
        cancelButton.setOnAction(e -> handleCancel());

        // Creating a grid pane and setting up layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Setting positions for right side
        firstNameLabel.setLayoutX(550);
        firstNameLabel.setLayoutY(60);
        lastNameLabel.setLayoutX(550);
        lastNameLabel.setLayoutY(100);
        passwordLabel.setLayoutX(550);
        passwordLabel.setLayoutY(140);

        firstNameField.setLayoutX(650);
        firstNameField.setLayoutY(60);
        lastNameField.setLayoutX(650);
        lastNameField.setLayoutY(100);
        passwordField.setLayoutX(650);
        passwordField.setLayoutY(140);

        registerButton.setLayoutX(550);
        registerButton.setLayoutY(190);
        resetButton.setLayoutX(650);
        resetButton.setLayoutY(190);
        cancelButton.setLayoutX(750);
        cancelButton.setLayoutY(190);

        messageLabel.setLayoutX(550);
        messageLabel.setLayoutY(240);

        // Adding elements to the pane
        mainContent.getChildren().addAll(firstNameLabel, lastNameLabel, passwordLabel, firstNameField, lastNameField, passwordField, registerButton, resetButton, cancelButton, messageLabel);

        // Adding logo, title, and phrase to the left side
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

        mainContent.getChildren().addAll(titleLabel, statement, imageView);

        Scene scene = new Scene(mainContent, 900, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to handle register button click
    private void handleRegister() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
            messageLabel.setText("All fields must be filled out.");
        } else {
            messageLabel.setText("");
            navigateToMainPage();
        }
    }

    // Method to handle reset button click
    private void handleReset() {
        firstNameField.clear();
        lastNameField.clear();
        passwordField.clear();
        messageLabel.setText("");
    }

    // Method to handle cancel button click
    private void handleCancel() {
        primaryStage.close();
    }

    // Method to navigate to MainPage
    private void navigateToMainPage() {
        MainPage mainPage = new MainPage();
        mainPage.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
