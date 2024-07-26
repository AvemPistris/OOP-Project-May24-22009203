// Danish Harith bin Ahmad Nizam 22009489
// Muhammad Amin Zufar Bin Ramlan 24000162

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;

public class LoginPage extends Application {

    private TextField usernameField;
    private PasswordField passwordField;
    private TextField showPasswordField;
    private Label messageLabel;
    private Stage primaryStage;

    //@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Login Page");

        // Set the same icon as the MainPage
        primaryStage.getIcons().add(new Image("logo.png"));

        Pane mainContent = new Pane();

        // Creating labels
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        // Creating text fields
        usernameField = new TextField();
        passwordField = new PasswordField();
        showPasswordField = new TextField();
        showPasswordField.setManaged(false);
        showPasswordField.setVisible(false);

        // Creating message label for validation messages
        messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);

        // Creating buttons
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Button cancelButton = new Button("Cancel");
        Button showPasswordButton = new Button("Show Password");

        // Setting event handlers for buttons
        loginButton.setOnAction(e -> handleLogin());
        registerButton.setOnAction(e -> navigateToRegistrationPage());
        cancelButton.setOnAction(e -> handleCancel());
        showPasswordButton.setOnMousePressed(e -> showPassword());
        showPasswordButton.setOnMouseReleased(e -> hidePassword());

        // Setting positions for right side
        usernameLabel.setLayoutX(550);
        usernameLabel.setLayoutY(60);
        passwordLabel.setLayoutX(550);
        passwordLabel.setLayoutY(100);

        usernameField.setLayoutX(650);
        usernameField.setLayoutY(60);
        passwordField.setLayoutX(650);
        passwordField.setLayoutY(100);
        showPasswordField.setLayoutX(650);
        showPasswordField.setLayoutY(100);

        showPasswordButton.setLayoutX(650);
        showPasswordButton.setLayoutY(130);
        loginButton.setLayoutX(550);
        loginButton.setLayoutY(170);
        registerButton.setLayoutX(650);
        registerButton.setLayoutY(170);
        cancelButton.setLayoutX(750);
        cancelButton.setLayoutY(170);

        messageLabel.setLayoutX(550);
        messageLabel.setLayoutY(220);

        // Adding elements to the pane
        mainContent.getChildren().addAll(usernameLabel, passwordLabel, usernameField, passwordField, showPasswordField, showPasswordButton, loginButton, registerButton, cancelButton, messageLabel);

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

    // Method to handle login button click
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
    
        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("All fields must be filled out.");
        } else {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tic_tac_toe", "root", "");
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
    
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
    
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    messageLabel.setText("");
                    navigateToMainPage();
                } else {
                    messageLabel.setText("Invalid username or password.");
                }
    
            } catch (SQLException e) {
                e.printStackTrace();
                messageLabel.setText("Login failed. Please try again.");
            }
        }
    }

    // Method to show password
    private void showPassword() {
        showPasswordField.setText(passwordField.getText());
        showPasswordField.setVisible(true);
        showPasswordField.setManaged(true);
        passwordField.setVisible(false);
        passwordField.setManaged(false);
    }

    // Method to hide password
    private void hidePassword() {
        passwordField.setText(showPasswordField.getText());
        passwordField.setVisible(true);
        passwordField.setManaged(true);
        showPasswordField.setVisible(false);
        showPasswordField.setManaged(false);
    }

    // Method to handle cancel button click
    private void handleCancel() {
        primaryStage.close();
    }

    // Method to navigate to RegistrationPage
    private void navigateToRegistrationPage() {
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.start(primaryStage);
    }

    // Method to navigate to MainPage
    private void navigateToMainPage() {
        MainPage mainPage = new MainPage();
        mainPage.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void displaySuccessMessage() {
        messageLabel.setTextFill(Color.GREEN);
        messageLabel.setText("Account successfully registered.");
    }
}




