/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganbare.ui;

import ganbare.domain.Session;
import ganbare.domain.GanbareService;

import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author palolaur
 */
public class GanbareUi extends Application {

    private Scene loginScene;
    private Scene optionScene;
    private Scene sessionScene;
    private Scene reviewScene;
    private GanbareService ganbareService;

    @Override
    public void init() throws Exception {
        this.ganbareService = new GanbareService();

        ganbareService.prepareLexicon();

    }

    public Scene reviewScene() {
        VBox reviewPane = new VBox(10);
        BorderPane textPane = new BorderPane();
        Label reviewLabel = new Label("Oikeita vastauksia: " + ganbareService.getSession().getCorrectAnswers()
                + "\n Kysymyksiä yhteensä: " + ganbareService.getSession().getLength());
        Button exitButton = new Button("Sulje");
        BorderPane buttonPane = new BorderPane();
        exitButton.setOnAction(e -> Platform.exit());

        textPane.setCenter(reviewLabel);
        buttonPane.setCenter(exitButton);

        reviewPane.getChildren().addAll(textPane, buttonPane);

        Scene testiscene = new Scene(reviewPane, 400, 300);

        return testiscene;
    }

    @Override
    public void start(Stage primaryStage) {

        //Login scene
        VBox loginPane = new VBox(10);
        HBox buttonPane = new HBox(20);
        loginPane.setPadding(new Insets(50, 10, 10, 10));
        buttonPane.setPadding(new Insets(30, 10, 10, 10));
        GridPane credentialsPane = new GridPane();

        Label userLabel = new Label("Käyttäjätunnus");
        Label passwordLabel = new Label("Salasana");

        TextField userField = new TextField();
        TextField passwordField = new TextField();

        Button loginButton = new Button("Kirjaudu");
        Button registerButton = new Button("Rekisteröidy");

        credentialsPane.add(userLabel, 0, 0);
        credentialsPane.add(userField, 1, 0);
        credentialsPane.add(passwordLabel, 0, 1);
        credentialsPane.add(passwordField, 1, 1);

        credentialsPane.setHgap(10);
        credentialsPane.setVgap(10);
        buttonPane.getChildren().addAll(loginButton, registerButton);
        buttonPane.setAlignment(Pos.CENTER);

        loginPane.getChildren().addAll(credentialsPane, buttonPane);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                primaryStage.setScene(optionScene);
            }
        });

        registerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(optionScene);
            }
        });

        loginScene = new Scene(loginPane, 400, 300);

        //Option scene
        VBox optionPane = new VBox(10);
        optionPane.setPadding(new Insets(10));
        HBox welcomePane = new HBox(10);
        HBox directionPane = new HBox(10);
        HBox wordClassPane = new HBox(10);
        HBox sessionLengthPane = new HBox(10);
        HBox optionButtonsPane = new HBox(10);

        Label welcomeLabel = new Label("Welcome user!");
        welcomePane.getChildren().add(welcomeLabel);
        welcomePane.setAlignment(Pos.CENTER);

        Label questionDirectionLabel = new Label("Harjoittelukieli");
        Label fromLanguageLabel = new Label("日本語");
        Label toLanguageLabel = new Label("suomi");
        Button directionButton = new Button("->");
        
        directionButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String fromLanguage = fromLanguageLabel.getText();
                fromLanguageLabel.setText(toLanguageLabel.getText());
                toLanguageLabel.setText(fromLanguage);
            }
        });

        directionPane.getChildren().addAll(fromLanguageLabel, directionButton, toLanguageLabel);

        Label wordClassLabel = new Label("Sanaluokat");
        CheckBox verbBox = new CheckBox("Verbit");
        CheckBox adjBox = new CheckBox("Adjektiivit");
        CheckBox subsBox = new CheckBox("Subsantiivit");
        wordClassPane.getChildren().addAll(verbBox, adjBox, subsBox);

        Label sessionLengthLabel = new Label("Session pituus");
        TextField sessionLengthField = new TextField();
        sessionLengthField.setPrefWidth(50);
        Label wordsLabel = new Label("sanaa (min: 5, max: " + ganbareService.getLexicon().size() + ")");
        sessionLengthPane.getChildren().addAll(sessionLengthLabel, sessionLengthField, wordsLabel);

        Button logoutButton = new Button("Kirjaudu ulos");
        Button exitButton = new Button("Sulje");
        Button beginButton = new Button("Aloita");
        optionButtonsPane.getChildren().addAll(beginButton, logoutButton, exitButton);
        optionButtonsPane.setAlignment(Pos.CENTER);

        exitButton.setOnAction(e -> Platform.exit());

        logoutButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(loginScene);
            }
        });

        Label questionLabel = new Label();
        Label currentLabel = new Label();

        beginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String language = fromLanguageLabel.getText();
                Boolean verbs = verbBox.isSelected();
                Boolean adjectives = adjBox.isSelected();
                Boolean substantives = subsBox.isSelected();
                int sessionLength = Integer.parseInt(sessionLengthField.getText());

                if ((verbs == false && adjectives == false && substantives == false) || sessionLength < 5) {
                    System.out.println("Epävalidit parametrit");

                } else {

                    ganbareService.newSession(language, verbs, adjectives, substantives, sessionLength);
                    questionLabel.setText(ganbareService.getSession().getQuestion());
                    currentLabel.setText("1/" + ganbareService.getSession().getLength());
                    primaryStage.setScene(sessionScene);
                }
            }

        });

        optionPane.getChildren().addAll(welcomePane, questionDirectionLabel, directionPane, wordClassLabel, wordClassPane, sessionLengthPane, optionButtonsPane);

        optionScene = new Scene(optionPane, 400, 300);

        //Session scene
        VBox sessionPane = new VBox(10);
        BorderPane feedbackPane = new BorderPane();
        TextField answerField = new TextField();
        Label feedbackLabel = new Label();
        feedbackPane.setCenter(feedbackLabel);
        feedbackLabel.setAlignment(Pos.CENTER);
        answerField.setPrefWidth(150);

        Button answerButton = new Button("Vastaa");
        Button toOptionsButton = new Button("Alkuvalikkoon");

        BorderPane currentPane = new BorderPane();
        currentPane.setRight(currentLabel);

        BorderPane questionPane = new BorderPane();
        questionPane.setCenter(questionLabel);

        HBox answerPane = new HBox(10);
        answerPane.getChildren().add(answerField);
        answerPane.setAlignment(Pos.CENTER);

        BorderPane answerButtonPane = new BorderPane();
        answerButtonPane.setCenter(answerButton);

        answerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                boolean correct = ganbareService.getSession().correctAnswer(answerField.getText());
                String feedback = ganbareService.getSession().getFeedback(correct, answerField.getText());
                
                if (ganbareService.getSession().incrementCounter()) {
                    
                    feedbackLabel.setText(feedback);
                    answerField.clear();
                    currentLabel.setText((ganbareService.getSession().currentQuestion() + 1) + "/" + ganbareService.getSession().getLength());
                    questionLabel.setText(ganbareService.getSession().getQuestion());
                } else {
                    primaryStage.setScene(reviewScene());
                }
            }
        });

        BorderPane optionsButtonPane = new BorderPane();
        optionsButtonPane.setRight(toOptionsButton);

        toOptionsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(optionScene);
            }
        });

        sessionPane.getChildren().addAll(currentPane, questionPane, answerPane, answerButtonPane, optionsButtonPane, feedbackPane);

        sessionScene = new Scene(sessionPane, 400, 300);

        primaryStage.setTitle("ガンバレ！！！");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
