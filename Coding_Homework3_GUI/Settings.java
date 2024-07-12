import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class Settings extends Application {

    private final Color[] backgroundColors = {
            Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
            Color.ORANGE, Color.PURPLE, Color.CYAN, Color.MAGENTA
    };

    private final Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Settings");

        VBox root = new VBox();
        root.setPrefSize(640, 300);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(900, 400);
        anchorPane.setMinSize(900, 400);
        anchorPane.setMaxSize(900, 400);

        Label settingsLabel = new Label("Settings");
        settingsLabel.setFont(new Font(32));
        AnchorPane.setTopAnchor(settingsLabel, 17.0);
        AnchorPane.setLeftAnchor(settingsLabel, 21.0);

        Button backButton = new Button("Back");
        backButton.setPrefSize(220, 26);
        AnchorPane.setBottomAnchor(backButton, 15.0);
        AnchorPane.setLeftAnchor(backButton, 25.0);
        backButton.setOnAction(e -> {
            primaryStage.hide();
            MainPage.getInstance().getPrimaryStage().show();
        });

        Button fontSizeButton = new Button("Font Size");
        fontSizeButton.setPrefSize(220, 26);
        AnchorPane.setTopAnchor(fontSizeButton, 134.0);
        AnchorPane.setLeftAnchor(fontSizeButton, 211.0);

        Button fontColorButton = new Button("Font Color");
        fontColorButton.setPrefSize(220, 26);
        AnchorPane.setTopAnchor(fontColorButton, 134.0);
        AnchorPane.setLeftAnchor(fontColorButton, 470.0);

        Button backgroundColorButton = new Button("Background Colour");
        backgroundColorButton.setPrefSize(220, 26);
        AnchorPane.setTopAnchor(backgroundColorButton, 187.0);
        AnchorPane.setLeftAnchor(backgroundColorButton, 337.0);
        backgroundColorButton.setOnAction(event -> {
            Color randomColor = backgroundColors[random.nextInt(backgroundColors.length)];
            root.setStyle("-fx-background-color: #" + toHexString(randomColor) + ";");

            MainPage.getInstance().applyBackgroundColor(randomColor);
        });

        anchorPane.getChildren().addAll(settingsLabel, backButton, fontSizeButton, fontColorButton, backgroundColorButton);

        root.getChildren().add(anchorPane);

        Scene scene = new Scene(root, 900, 400);

        primaryStage.getIcons().add(new Image("logo.png"));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String toHexString(Color color) {
        return String.format("%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    public static void main(String[] args) {
        launch(args);
    }
}