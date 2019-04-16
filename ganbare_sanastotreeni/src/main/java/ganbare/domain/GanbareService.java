package ganbare.domain;

import dao.LexiconDao;

import java.sql.SQLException;
import java.util.*;

public class GanbareService {

    private Session session;
    private LexiconDao lexiconDao;

    public GanbareService() {
        this.lexiconDao = new LexiconDao();

    }

    public boolean newSession(String language, boolean substantives, boolean adjectives, boolean verbs, boolean adverbs, int length) {

        if (!(language.equals("suomi") || language.equals("日本語")) || (substantives == false && adjectives == false && verbs == false && adverbs == false) || length < 1) {
            return false;
        }

        SqlParameters sqlParams = new SqlParameters(language, substantives, adjectives, verbs, adverbs, length);

        ArrayList<String[]> lexicon = new ArrayList<>();

        try {
            lexicon = this.lexiconDao.createLexicon(sqlParams);
        } catch (SQLException e) {
            System.out.println("Virhe: " + e);
        }

        this.session = new Session(language, lexicon, length);

        return true;

    }

    public Session getSession() {
        return this.session;
    }

    public String getQuestion() {
        return this.session.getQuestion();
    }

    public int getSessionLength() {
        return this.session.getLength();
    }

    public String getFeedback(String answer) {
        return this.session.getFeedback(answer);
    }

    public String nextQuestion() {
        if (this.session.incrementCounter()) {
            return this.session.getQuestion();
        }

        return null;
    }

    public int getCurrentQuestionNum() {
        return this.session.getCurrentQuestionNum() + 1;
    }

    public String getSessionReview() {
        return this.session.getReview();
    }

    public int getTotalWords(boolean substantives, boolean adjectives, boolean verbs, boolean adverbs) {
        SqlParameters sqlParams = new SqlParameters(substantives, adjectives, verbs, adverbs);

        try {
            return this.lexiconDao.totalWordCount(sqlParams);
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
            return -1;
        }
    }

    public LexiconDao getLexiconDao() {
        return this.lexiconDao;
    }

}
