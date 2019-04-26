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
import javafx.scene.control.ComboBox;
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
        loginButton.setDefaultButton(true);

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
                    userField.clear();
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

        Label announcementLabel = new Label("ようこそ " + username + "-さん!");
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

        subsBox.setOnAction(e -> wordsLabel.setText("(max: " + ganbareService.getTotalWords(subsBox.isSelected(), adjBox.isSelected(), verbBox.isSelected(), adverbBox.isSelected()) + " sanaa)"));
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

        Label questionLabel = new Label(ganbareService.newQuestion());
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
                String newQuestion = ganbareService.newQuestion();

                if (newQuestion != null) {

                    feedbackLabel.setText(feedback);
                    answerField.clear();
                    questionNumLabel.setText("kysymys: " + (ganbareService.getCurrentQuestionNum()) + "/" + ganbareService.getSessionLength());
                    questionLabel.setText(newQuestion);

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
        reviewPane.setAlignment(Pos.CENTER);

        Scene reviewScene = new Scene(reviewPane, 400, 300);

        return reviewScene;
    }

    public Scene adminScene() {
        VBox adminPane = new VBox(10);
        HBox buttonPane = new HBox(10);

        Label infoLabel = new Label("Olet kirjautunut admin-tilaan.");
        infoLabel.setPadding(new Insets (0, 0, 30, 0));
        
        Button addWordButton = new Button("Lisää sana");
        Button addSynonymButton = new Button("Lisää synonyymi");
        Button logoutButton = new Button("Kirjaudu ulos");

        addWordButton.setOnAction(e -> primaryStage.setScene(addWordScene()));
        addSynonymButton.setOnAction(e -> primaryStage.setScene(addSynonymScene()));
        logoutButton.setOnAction(e -> primaryStage.setScene(loginScene));

        buttonPane.getChildren().addAll(addWordButton, addSynonymButton, logoutButton);
        buttonPane.setAlignment(Pos.CENTER);
        
        adminPane.getChildren().addAll(infoLabel, buttonPane);
        adminPane.setAlignment(Pos.CENTER);

        Scene adminScene = new Scene(adminPane, 400, 300);

        return adminScene;

    }

    public Scene addWordScene() {
        VBox addWordPane = new VBox(10);
        GridPane optionsPane = new GridPane();
        HBox buttonPane = new HBox(10);

        Label finnishLabel = new Label("Suomeksi");
        Label kanaLabel = new Label("Kana");
        Label romajiLabel = new Label("Romaji");
        Label wordClassLabel = new Label("Sanaluokka");
        Label chapterLabel = new Label("Kappale");
        Label announcementLabel = new Label("");
        TextField finnishField = new TextField();
        TextField kanaField = new TextField();
        TextField romajiField = new TextField();
        ComboBox classBox = new ComboBox();
        ComboBox chapterBox = new ComboBox();
        classBox.getItems().addAll("substantiivi", "adjektiivi", "verbi", "adverbi");
        chapterBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        classBox.getSelectionModel().selectFirst();
        chapterBox.getSelectionModel().selectFirst();

        Button addWordButton = new Button("Lisää sana");
        Button goBackButton = new Button("Takaisin");

        buttonPane.getChildren().addAll(addWordButton, goBackButton);

        optionsPane.add(finnishLabel, 0, 0);
        optionsPane.add(finnishField, 0, 1);
        optionsPane.add(kanaLabel, 1, 0);
        optionsPane.add(kanaField, 1, 1);
        optionsPane.add(romajiLabel, 2, 0);
        optionsPane.add(romajiField, 2, 1);
        optionsPane.add(wordClassLabel, 0, 3);
        optionsPane.add(classBox, 0, 4);
        optionsPane.add(chapterLabel, 1, 3);
        optionsPane.add(chapterBox, 1, 4);
        optionsPane.setHgap(10);
        optionsPane.setVgap(5);
        optionsPane.setPadding(new Insets(10, 20, 20, 20));

        goBackButton.setOnAction(e -> primaryStage.setScene(adminScene()));

        addWordButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String finnish = finnishField.getText();
                String kana = kanaField.getText();
                String romaji = romajiField.getText();
                String wordClass = classBox.getSelectionModel().getSelectedItem().toString();
                int chapter = Integer.parseInt(chapterBox.getSelectionModel().getSelectedItem().toString());

                if (ganbareService.addNewWord(finnish, kana, romaji, wordClass, chapter));
                {
                    announcementLabel.setText("Uusi sana lisätty!");
                }
            }

        });

        buttonPane.setAlignment(Pos.CENTER);
        addWordPane.getChildren().addAll(optionsPane, buttonPane, announcementLabel);

        addWordPane.setAlignment(Pos.CENTER);

        Scene wordScene = new Scene(addWordPane, 400, 300);

        return wordScene;

    }

    public Scene addSynonymScene() {
        VBox addSynonymPane = new VBox(10);
        GridPane optionsPane = new GridPane();
        HBox buttonPane = new HBox(10);

        Label originalLabel = new Label("Alkuperäinen sana");
        Label synonymLabel = new Label("Lisättävä synonyymi");
        Label announcementLabel = new Label("");

        TextField originalField = new TextField();
        TextField synonymField = new TextField();

        Button addSynonymButton = new Button("Lisää synonyymi");
        Button goBackButton = new Button("Takaisin");

        optionsPane.add(originalLabel, 0, 1);
        optionsPane.add(synonymLabel, 1, 1);
        optionsPane.add(originalField, 0, 2);
        optionsPane.add(synonymField, 1, 2);
        optionsPane.setHgap(10);
        optionsPane.setVgap(5);
        optionsPane.setPadding(new Insets(10, 20, 20, 20));

        optionsPane.setAlignment(Pos.CENTER);

        buttonPane.getChildren().addAll(addSynonymButton, goBackButton);

        buttonPane.setAlignment(Pos.CENTER);

        goBackButton.setOnAction(e -> primaryStage.setScene(adminScene()));

        addSynonymButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (ganbareService.addFinnishSynonym(originalField.getText(), synonymField.getText())) {
                    announcementLabel.setText("Synonyymi lisätty!");
                }
            }

        });

        addSynonymPane.getChildren().addAll(optionsPane, buttonPane, announcementLabel);
        addSynonymPane.setAlignment(Pos.CENTER);

        Scene synonymScene = new Scene(addSynonymPane, 400, 300);

        return synonymScene;
    }

    public static void main(String[] args) {

        launch(args);
    }

}
