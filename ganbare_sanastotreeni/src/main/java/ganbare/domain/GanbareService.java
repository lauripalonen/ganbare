package ganbare.domain;

import ganbare.dao.LexiconDao;

import java.sql.SQLException;
import java.util.*;

public class GanbareService {

    private Session session;
    private LexiconDao lexiconDao;
    
    private int substantives;
    private int adjectives;
    private int verbs;
    private int adverbs;

    public GanbareService() {
        this.lexiconDao = new LexiconDao();
        
//        try {
//        this.lexiconDao.testConnection();
//        } catch (Exception e){
//            System.out.println("Virhe: " + e);
//        }

        try {
            substantives = this.lexiconDao.getCount(1);
            adjectives = this.lexiconDao.getCount(2);
            verbs = this.lexiconDao.getCount(3);
            adverbs = this.lexiconDao.getCount(4);
        } catch (Exception e){
            System.out.println("Virhe: " + e);
        }

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
        
        int totalWordCount = 0;
        
        if(substantives){
            totalWordCount += this.substantives;
        }
        
        if(adjectives){
            totalWordCount += this.adjectives;
        }
        
        if(verbs){
            totalWordCount += this.verbs;
        }
        
        if(adverbs){
            totalWordCount += this.adverbs;
        }
        
        return totalWordCount;
    }

    public LexiconDao getLexiconDao() {
        return this.lexiconDao;
    }
    

}
