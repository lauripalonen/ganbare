/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganbare.domain;

/**
 *
 * @author palolaur
 */
import java.io.File;
import java.util.*;

public class GanbareService {

    private User user;
    private ArrayList<String> lexicon;
    private ArrayList<String> sessionLexicon;
    private Session session;

    public GanbareService() {
        this.lexicon = new ArrayList<>();

    }

    public void addUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void prepareLexicon() {
        try (Scanner fileScanner = new Scanner(new File("lexicon.txt"))) {
            while (fileScanner.hasNextLine()) {

                this.lexicon.add(fileScanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }

    }

    public String newSession(String language, boolean verbs, boolean adjectives, boolean substantives, int length) {

        this.session = new Session(language, verbs, adjectives, substantives, length);
        this.session.prepareSessionLexicon(this.lexicon);
        return this.session.getQuestion();

    }

    public Session getSession() {
        return this.session;
    }

    public ArrayList<String> getLexicon() {
        return this.lexicon;
    }

}
