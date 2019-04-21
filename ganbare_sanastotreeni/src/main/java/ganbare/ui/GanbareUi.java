package ganbare.ui;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GanbareUi extends Application {

    private Stage primaryStage;
    private Scene loginScene;
    private Scene optionsScene;
    private GanbareService ganbareService;

    @Override
    public void init() throws Exception {
        this.ganbareService = new GanbareService();

    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        VBox loginPane = new VBox(10);
        HBox buttonPane = new HBox(20);
        GridPane credentialsPane = new GridPane();
        BorderPane announcementPane = new BorderPane();

        loginPane.setPadding(new Insets(50, 10, 10, 10));
        buttonPane.setPadding(new Insets(30, 10, 10, 10));

        Label userLabel = new Label("Käyttäjätunnus");
        Label passwordLabel = new Label("Salasana");
        Label announcementLabel = new Label("");

        TextField userField = new TextField();
        PasswordField passwordField = new PasswordField();

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

        announcementPane.setCenter(announcementLabel);

        loginPane.getChildren().addAll(credentialsPane, buttonPane, announcementPane);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String name = userField.getText();
                String password = passwordField.getText();

                if (ganbareService.loginUser(name, password)) {
                    passwordField.clear();

                    if (name.equals("admin")) {
                        primaryStage.setScene(adminScene());
                    } else {
                        primaryStage.setScene(optionsScene(name));
                    }
                } else {
                    announcementLabel.setText("Virheellinen käyttäjätunnus tai salasana.");
                }

            }
        });

        registerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String name = userField.getText();
                String password = passwordField.getText();

                if (ganbareService.newUser(name, password)) {

                    passwordField.clear();
                    primaryStage.setScene(optionsScene(name));
                } else {
                    announcementLabel.setText("Käyttäjätunnus \"" + userField.getText() + "\" on jo olemassa");
                }

            }
        });

        loginScene = new Scene(loginPane, 400, 300);

        primaryStage.setTitle("ガンバレ！！！");
        primaryStage.setScene(loginScene);
        primaryStage.show();

    }

    public Scene optionsScene(String username) {
        VBox optionsPane = new VBox(10);
        HBox announcementPane = new HBox(10);
        HBox languagePane = new HBox(10);
        HBox wordClassPane = new HBox(10);
        HBox sessionLengthPane = new HBox(10);
        HBox optionButtonsPane = new HBox(10);

        optionsPane.setPadding(new Insets(10));

        Label announcementLabel = new Label("Welcome " + username + "!");
        announcementLabel.setAlignment(Pos.CENTER);
        announcementPane.getChildren().add(announcementLabel);
        announcementPane.setAlignment(Pos.CENTER);

        Label questionDirectionLabel = new Label("Harjoittelukieli:");
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

        languagePane.getChildren().addAll(fromLanguageLabel, directionButton, toLanguageLabel);

        Label wordClassLabel = new Label("Sanaluokat:");
        CheckBox subsBox = new CheckBox("Subsantiivit");
        CheckBox verbBox = new CheckBox("Verbit");
        CheckBox adjBox = new CheckBox("Adjektiivit");
        CheckBox adverbBox = new CheckBox("Adverbit");
        wordClassPane.getChildren().addAll(subsBox, adjBox, verbBox, adverbBox);

        Label sessionLengthLabel = new Label("Session pituus: ");
        TextField sessionLengthField = new TextField();
        sessionLengthField.setPrefWidth(50);
        Label wordsLabel = new Label("(max: 0 sanaa)");
        sessionLengthPane.getChildren().addAll(sessionLengthLabel, sessionLengthField, wordsLabel);

        Button beginButton = new Button("Aloita");
        Button logoutButton = new Button("Kirjaudu ulos");
        Button exitButton = new Button("Sulje");

        optionButtonsPane.getChildren().addAll(beginButton, logoutButton, exitButton);
        optionButtonsPane.setAlignment(Pos.CENTER);

        exitButton.setOnAction(e -> Platform.exit());
        logoutButton.setOnAction(e -> primaryStage.setScene(loginScene));

        beginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (sessionLengthField.getText().isEmpty()) {
                    announcementLabel.setText("Aseta validi sanamäärä");
                } else {

                    int sessionLength = Integer.parseInt(sessionLengthField.getText());
                    int maxLength = ganbareService.getTotalWords(subsBox.isSelected(), adjBox.isSelected(), verbBox.isSelected(), adverbBox.isSelected());

                    if ((!subsBox.isSelected() && !adjBox.isSelected() && !verbBox.isSelected() && !adverbBox.isSelected()) || sessionLength < 1 || sessionLength > maxLength) {
                        announcementLabel.setText("Valitse vähintään yksi sanaluokka \n ja aseta validi sanamäärä");

                    } else {

                        ganbareService.newSession(fromLanguageLabel.getText(), subsBox.isSelected(), adjBox.isSelected(), verbBox.isSelected(), adverbBox.isSelected(), sessionLength);

                        subsBox.setSelected(false);
                        adjBox.setSelected(false);
                        verbBox.setSelected(false);
                        adverbBox.setSelected(false);
                        sessionLengthField.clear();
                        wordsLabel.setText("max: 0 sanaa");
                        announcementLabel.setText("");

                        primaryStage.setScene(sessionScene());
                    }
                }
            }

        });

        subsBox.setOnAction(e -> wordsLabel.setText("(max: " + ganbareService.getTotalWords(subsBox.isSelected(), adjBox.isSelected(), verbBox.isSelected(), adverbBox.isSelected()) + " sanaa"));
        adjBox.setOnAction(e -> wordsLabel.setText("(max: " + ganbareService.getTotalWords(subsBox.isSelected(), adjBox.isSelected(), verbBox.isSelected(), adverbBox.isSelected()) + " sanaa)"));
        verbBox.setOnAction(e -> wordsLabel.setText("(max: " + ganbareService.getTotalWords(subsBox.isSelected(), adjBox.isSelected(), verbBox.isSelected(), adverbBox.isSelected()) + " sanaa)"));
        adverbBox.setOnAction(e -> wordsLabel.setText("(max: " + ganbareService.getTotalWords(subsBox.isSelected(), adjBox.isSelected(), verbBox.isSelected(), adverbBox.isSelected()) + " sanaa)"));

        optionsPane.getChildren().addAll(announcementPane, questionDirectionLabel, languagePane, wordClassLabel,
                wordClassPane, sessionLengthPane, optionButtonsPane);

        optionsScene = new Scene(optionsPane, 400, 300);

        return optionsScene;

    }

    public Scene sessionScene() {
        VBox sessionPane = new VBox(10);
        BorderPane feedbackPane = new BorderPane();

        Label questionLabel = new Label(ganbareService.getQuestion());
        Label questionNumLabel = new Label("kysymys: 1/" + ganbareService.getSessionLength());
        TextField answerField = new TextField();
        Label feedbackLabel = new Label();
        feedbackPane.setCenter(feedbackLabel);
        feedbackLabel.setAlignment(Pos.CENTER);
        answerField.setPrefWidth(150);

        Button answerButton = new Button("Vastaa");
        Button toOptionsButton = new Button("Alkuvalikkoon");
        Button quitButton = new Button("Lopeta");

        BorderPane currentPane = new BorderPane();
        currentPane.setRight(questionNumLabel);

        BorderPane questionPane = new BorderPane();
        questionPane.setCenter(questionLabel);

        HBox answerPane = new HBox(20);
        answerPane.getChildren().add(answerField);
        answerPane.setAlignment(Pos.CENTER);

        HBox ButtonPane = new HBox(10);
        ButtonPane.getChildren().addAll(answerButton, toOptionsButton, quitButton);
        ButtonPane.setAlignment(Pos.CENTER);

        answerButton.setDefaultButton(true);

        answerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String feedback = ganbareService.getFeedback(answerField.getText());
                String nextQuestion = ganbareService.nextQuestion();

                if (nextQuestion != null) {

                    feedbackLabel.setText(feedback);
                    answerField.clear();
                    questionNumLabel.setText("kysymys: " + (ganbareService.getCurrentQuestionNum()) + "/" + ganbareService.getSessionLength());
                    questionLabel.setText(nextQuestion);

                } else {
                    primaryStage.setScene(reviewScene(feedback));
                }
            }
        });

        toOptionsButton.setOnAction(e -> primaryStage.setScene(optionsScene));

        quitButton.setOnAction(e -> primaryStage.setScene(reviewScene("")));

        sessionPane.getChildren().addAll(currentPane, questionPane, answerPane, ButtonPane, feedbackPane);

        Scene sessionScene = new Scene(sessionPane, 400, 300);

        return sessionScene;
    }

    public Scene reviewScene(String feedback) {
        VBox reviewPane = new VBox(10);
        HBox feedbackPane = new HBox(10);
        BorderPane textPane = new BorderPane();
        HBox buttonPane = new HBox(10);

        Label feedbackLabel = new Label(feedback);
        Label reviewLabel = new Label(ganbareService.getSessionReview());
        Button exitButton = new Button("Sulje");
        Button toOptionsButton = new Button("Alkuvalikkoon");

        exitButton.setOnAction(e -> Platform.exit());
        toOptionsButton.setOnAction(e -> primaryStage.setScene(optionsScene));

        feedbackPane.getChildren().addAll(feedbackLabel);
        feedbackPane.setAlignment(Pos.CENTER);
        textPane.setCenter(reviewLabel);
        buttonPane.getChildren().addAll(toOptionsButton, exitButton);
        buttonPane.setAlignment(Pos.CENTER);

        reviewPane.getChildren().addAll(feedbackPane, textPane, buttonPane);

        Scene testiscene = new Scene(reviewPane, 400, 300);

        return testiscene;
    }

    public Scene adminScene() {
        VBox adminPane = new VBox();
        GridPane optionsPane = new GridPane();
        HBox buttonsPane = new HBox(10);
        
        adminPane.setPadding(new Insets(50, 10, 10, 10));
        buttonsPane.setPadding(new Insets(30, 10, 10, 10));


        Label lisaaLabel = new Label("Lisää uusi synonyymi");
        Label originalLabel = new Label("Alkuperäinen sana");
        Label synonymLabel = new Label("Lisättävä synonyymi");

        TextField originalField = new TextField();
        TextField synonymField = new TextField();

        Button addButton = new Button("Lisää synonyymi");
        Button logoutButton = new Button("Kirjaudu ulos");

        optionsPane.add(lisaaLabel, 0, 0);
        optionsPane.add(originalLabel, 0, 1);
        optionsPane.add(synonymLabel, 1, 1);
        optionsPane.add(originalField, 0, 2);
        optionsPane.add(synonymField, 1, 2);
        
        optionsPane.setHgap(10);
        optionsPane.setVgap(10);

        buttonsPane.getChildren().addAll(addButton, logoutButton);
        buttonsPane.setAlignment(Pos.CENTER);
        
        optionsPane.setAlignment(Pos.CENTER);

        adminPane.getChildren().addAll(optionsPane, buttonsPane);
        
        logoutButton.setOnAction(e -> primaryStage.setScene(loginScene));
        
        addButton.setOnAction(new EventHandler<ActionEvent>() {
          
           @Override
           public void handle(ActionEvent event){
               ganbareService.addFinnishSynonym(originalField.getText(), synonymField.getText());
           }
            
        });

        Scene adminScene = new Scene(adminPane, 400, 300);

        return adminScene;

    }

    public static void main(String[] args) {

        launch(args);
    }

}
