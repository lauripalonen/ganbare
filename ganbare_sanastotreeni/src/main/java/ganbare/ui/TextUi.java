/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganbare.ui;

import ganbare.domain.User;
import ganbare.domain.GanbareService;
import ganbare.domain.Session;
/**
 *
 * @author palolaur
 */
import java.util.*;

public class TextUi {

    private Scanner scanner;
    private GanbareService service;
    private Session session;

    public TextUi(Scanner scanner, GanbareService service) {
        this.scanner = scanner;
        this.service = service;

    }

    public void start() {

        System.out.println("Hei! Syötä käyttäjätietosi: ");
        System.out.println("Käyttäjätunnus: ");
        String username = scanner.nextLine();
        System.out.println("Salasana: ");
        String password = scanner.nextLine();

        System.out.println("Kirjaudu [k] vai rekisteröidy[r]?");
        String input = scanner.nextLine();

        if (input.equals("k")) {
            login(username, password);
        } else if (input.equals("r")) {
            register(username, password);
        } else {
            System.out.println("Epäkelpo syöte");
        }

    }

    public void login(String username, String password) {
        //handle credential check from database
        User user = new User(username);
        service.addUser(user);

        options();

    }

    public void register(String username, String password) {
        //handle adding new user to the database
        User user = new User(username);
        service.addUser(user);

        options();

    }

    public void options() {
        System.out.println("");
        System.out.println("*****ガンバレ!!!******");
        System.out.println("ようこそ　" + service.getUser() + "-さん！");
        System.out.println("");

        System.out.println("1. Kysytäänkö japaniksi [j] vai suomeksi [s]?");
        String quizLanguage = scanner.nextLine();
        System.out.println("2. Mitä sanaluokkia kysytään?");
        String[] wordClass = new String[5];

        System.out.println("  2.1: Verbit [y/n]:");
        wordClass[0] = scanner.nextLine();
        System.out.println("  2.2: Adjektiivit [y/n]:");
        wordClass[1] = scanner.nextLine();
        System.out.println("  2.3: Substantiivit [y/n]:");
        wordClass[2] = scanner.nextLine();

        System.out.println("Valitse session pituus [5-20]: ");
        int sessionLength = Integer.parseInt(scanner.nextLine());

        System.out.println("Valitsit session pituudeksi: " + sessionLength);
        System.out.println("");

        //Confirm that the session configuration is valid.
        //
        //Load correct session package from database and
        //save the package to GanbareService.
        this.session = new Session(sessionLength, service.getLexicon());

        beginSession();

    }

    public void beginSession() {

        int sessionLength = session.getLength();
        int questionNum = session.currentQuestion();

        while (true) {
            if (session.currentQuestion() > sessionLength - 1) {
                break;

            }

            newQuestion();

            session.incrementCounter();

        }

        review();

    }

    public void newQuestion() {

        String question = session.getQuestion();
        String answer = session.getAnswer();

        System.out.println("Kysymys " + (session.currentQuestion() + 1) + "/" + session.getLength() + ": ");
        System.out.println(question);
        System.out.println("");

        System.out.println("Vastaus: ");
        String userInput = scanner.nextLine().toLowerCase();
        System.out.println("");

        if (session.correctAnswer(userInput)) {
            System.out.println("Oikea vastaus!");
            System.out.println("");
        } else {
            System.out.println("Oikea vastaus oli: " + session.getAnswer());
            System.out.println("Vastasit: " + userInput);
            System.out.println("");
        }

    }

    public void review() {
        System.out.println("Sessio päättyi!");
        System.out.println("Oikeita vastauksia: " + session.getCorrectAnswers());
        System.out.println("Kysymyksiä yhteensä: " + session.getLength());
        System.out.println("");
        System.out.println("Siirrytäänkö alkuvalikkoon [a] vai suljetaanko sovellus[x]?");
        String userInput = scanner.nextLine();

        if (userInput.equals("a")) {
            options();
        } else if (userInput.equals("x")) {
            System.out.println("Kiitos pelaamisesta!");
        } else {
            System.out.println("Epäkelpo syöte");
        }
    }

}
