package ganbare.domain;

import java.io.File;
import java.util.*;

public class GanbareService {

    private ArrayList<String> lexicon;
    private Session session;

    public GanbareService() {
        this.lexicon = new ArrayList<>();

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

    public void newSession(String language, boolean verbs, boolean adjectives, boolean substantives, int length) {

        this.session = new Session(language, verbs, adjectives, substantives, length);
        this.session.prepareSessionLexicon(this.lexicon);

    }

    public Session getSession() {
        return this.session;
    }

    public ArrayList<String> getLexicon() {
        return this.lexicon;

    }

}
