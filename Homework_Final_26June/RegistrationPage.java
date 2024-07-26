// Muhammad Amin Zufar Bin Ramlan 24000162
// Danish Harith bin Ahmad Nizam 22009489

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;

public class RegistrationPage extends Application {

    private TextField firstNameField, lastNameField, usernameField, ageField, emailField;
    private PasswordField passwordField;
    private TextField showPasswordField;
    private ComboBox<String> genderComboBox;
    private Label messageLabel;
    private Stage primaryStage;

    //@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Registration Page");

        // Set the same icon as the MainPage
        primaryStage.getIcons().add(new Image("logo.png"));

        Pane mainContent = new Pane();

        // Creating labels
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label usernameLabel = new Label("Username:");
        Label genderLabel = new Label("Gender:");
        Label ageLabel = new Label("Age:");
        Label emailLabel = new Label("Email:");
        Label passwordLabel = new Label("Password:");

        // Creating text fields
        firstNameField = new TextField();
        lastNameField = new TextField();
        usernameField = new TextField();
        ageField = new TextField();
        emailField = new TextField();
        passwordField = new PasswordField();
        showPasswordField = new TextField();
        showPasswordField.setManaged(false);
        showPasswordField.setVisible(false);

        // Creating gender combo box
        genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Prefer not to say");

        // Creating message label for validation messages
        messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);

        // Creating buttons
        Button registerButton = new Button("Register");
        Button backButton = new Button("Back");
        Button showPasswordButton = new Button("Show Password");

        // Setting event handlers for buttons
        registerButton.setOnAction(e -> handleRegister());
        backButton.setOnAction(e -> navigateToLoginPage());
        showPasswordButton.setOnMousePressed(e -> showPassword());
        showPasswordButton.setOnMouseReleased(e -> hidePassword());

        // Setting positions for right side
        firstNameLabel.setLayoutX(550);
        firstNameLabel.setLayoutY(20);
        lastNameLabel.setLayoutX(550);
        lastNameLabel.setLayoutY(60);
        usernameLabel.setLayoutX(550);
        usernameLabel.setLayoutY(100);
        genderLabel.setLayoutX(550);
        genderLabel.setLayoutY(140);
        ageLabel.setLayoutX(550);
        ageLabel.setLayoutY(180);
        emailLabel.setLayoutX(550);
        emailLabel.setLayoutY(220);
        passwordLabel.setLayoutX(550);
        passwordLabel.setLayoutY(260);

        firstNameField.setLayoutX(650);
        firstNameField.setLayoutY(20);
        lastNameField.setLayoutX(650);
        lastNameField.setLayoutY(60);
        usernameField.setLayoutX(650);
        usernameField.setLayoutY(100);
        genderComboBox.setLayoutX(650);
        genderComboBox.setLayoutY(140);
        ageField.setLayoutX(650);
        ageField.setLayoutY(180);
        emailField.setLayoutX(650);
        emailField.setLayoutY(220);
        passwordField.setLayoutX(650);
        passwordField.setLayoutY(260);
        showPasswordField.setLayoutX(650);
        showPasswordField.setLayoutY(260);

        showPasswordButton.setLayoutX(650);
        showPasswordButton.setLayoutY(290);
        registerButton.setLayoutX(550);
        registerButton.setLayoutY(340);
        backButton.setLayoutX(650);
        backButton.setLayoutY(340);

        messageLabel.setLayoutX(550);
        messageLabel.setLayoutY(380);

        // Adding elements to the pane
        mainContent.getChildren().addAll(firstNameLabel, lastNameLabel, usernameLabel, genderLabel, ageLabel, emailLabel, passwordLabel, firstNameField, lastNameField, usernameField, genderComboBox, ageField, emailField, passwordField, showPasswordField, showPasswordButton, registerButton, backButton, messageLabel);

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
        String username = usernameField.getText();
        String gender = genderComboBox.getValue();
        String age = ageField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || gender == null || age.isEmpty() || email.isEmpty() || password.isEmpty()) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("All fields must be filled out.");
        } else if (!age.matches("\\d+") || Integer.parseInt(age) <= 0) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Age must be a positive integer.");
        } else if (!email.matches("\\S+@(yahoo|gmail)\\.(com|edu)")) {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Email must be a valid format (e.g., example@gmail.com).");
        } else {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tic_tac_toe", "root", "");
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (first_name, last_name, username, gender, age, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, gender);
                preparedStatement.setInt(5, Integer.parseInt(age));
                preparedStatement.setString(6, email);
                preparedStatement.setString(7, password);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    messageLabel.setTextFill(Color.GREEN);
                    messageLabel.setText("Registration successful!");
                    navigateToLoginPageWithSuccess();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                messageLabel.setTextFill(Color.RED);
                messageLabel.setText("Registration failed. Please try again.");
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

    // Method to navigate to LoginPage
    private void navigateToLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.start(primaryStage);
    }

    // Method to navigate to LoginPage with success message
    private void navigateToLoginPageWithSuccess() {
        LoginPage loginPage = new LoginPage();
        loginPage.start(primaryStage);
        loginPage.displaySuccessMessage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



