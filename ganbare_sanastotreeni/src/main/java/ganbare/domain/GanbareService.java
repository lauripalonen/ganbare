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

    public void newSession(int sessionLength) {

        Collections.shuffle(this.lexicon);

        ArrayList<String> session = new ArrayList<>();

        for (int i = 0; i < sessionLength; i++) {
            session.add(lexicon.get(i));
        }

        this.sessionLexicon = session;

    }

    public ArrayList<String> getSession() {
        return this.sessionLexicon;
    }

    public ArrayList<String> getLexicon() {
        return this.lexicon;
    }

}
