package com.example.notepad;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class RemindMe {

    public boolean isTaskRequired = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label dateTimeLabel;

    @FXML void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime now = LocalDateTime.now();
            dateTimeLabel.setText(now.format(formatter));
        }), new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();

        addNote.setOnAction(e -> {
            isTaskRequired = true;
            addTask();
        });

        cancel.setOnAction(e -> {
            isTaskRequired = false;
            addTask();
        });

        saveNote.setOnAction(e -> {
            isTaskRequired = false;
            getTitle();
        });
    }

    @FXML
    private ImageView telegram;

    @FXML
    private ImageView vk;

    @FXML
    private void openVk(MouseEvent event) {
        openLink("https://vk.com/volk_bez_3");
    }

    @FXML
    private void oppenTelegram(MouseEvent event) {
        openLink("https://t.me/bo1tara");
    }

    private void openLink(String url) {
        if(Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private Label quantity;

    @FXML
    private Label completed;

    @FXML
    private Label notCompleted;

    @FXML
    private Button cancel;

    @FXML
    private AnchorPane newScene;

    @FXML
    private AnchorPane infoPane;

    @FXML
    private Line firstLine;

    @FXML
    private Line secondLine;

    @FXML
    private Button addNote;

    @FXML
    private Button saveNote;

    @FXML
    private AnchorPane functionPane;

    @FXML
    private void addTask(){
        if(isTaskRequired){
            functionPane.setVisible(false);
            infoPane.setVisible(false);
            firstLine.setVisible(false);
            secondLine.setVisible(false);
            newScene.setVisible(true);
        }
        else {
            newScene.setVisible(false);
            functionPane.setVisible(true);
            infoPane.setVisible(true);
            firstLine.setVisible(true);
            secondLine.setVisible(true);
        }
    }
    
    @FXML
    private CheckBox tit;

    @FXML
    public void getTitle(){
        String titleText = title.getText();
        CheckBox newCheckbox = new CheckBox(titleText);
        newCheckbox.setStyle("-fx-font-family: 'BankGothic Md BT'; -fx-font-size: 18px;");

        int checkBoxCount = functionPane.getChildren().size();
        double newYPosition = 30.0 * checkBoxCount;
        double xOffset = 40.0;
        double yOffset = 10.0;

        newCheckbox.setLayoutX(xOffset);
        newCheckbox.setLayoutY(newYPosition + yOffset);
        functionPane.getChildren().add(newCheckbox);

        title.clear();

        if(isTaskRequired){
            functionPane.setVisible(false);
            infoPane.setVisible(false);
            firstLine.setVisible(false);
            secondLine.setVisible(false);
            newScene.setVisible(true);
        }
        else {
            newScene.setVisible(false);
            functionPane.setVisible(true);
            infoPane.setVisible(true);
            firstLine.setVisible(true);
            secondLine.setVisible(true);
            tit.setVisible(true);
        }
    }

    
    @FXML
    private TextField title;

    @FXML
    private Button load;

    @FXML
    private Button redact;

}