package ganbare.domain;

import java.io.File;
import java.util.*;

public class GanbareService {

    private ArrayList<String> lexicon;
    private Session session;

    public GanbareService() {
        this.lexicon = new ArrayList<>();

    }

    public boolean prepareLexicon(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {

                this.lexicon.add(fileScanner.nextLine());

            }
            return true;

        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
            return false;
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
