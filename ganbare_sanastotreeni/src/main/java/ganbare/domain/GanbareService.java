package ganbare.domain;

import ganbare.dao.LexiconDao;
import ganbare.dao.UserDao;

import java.sql.SQLException;
import java.util.*;

/**
 * Manages all the logical components of the Ganbare software.
 *
 */
public class GanbareService {

    private Session session;
    private LexiconDao lexiconDao;
    private UserDao userDao;

    private int substantives;
    private int adjectives;
    private int verbs;
    private int adverbs;

    /**
     * Creates new GanbareService and automatically forms connection to
     * lexiconDao and userDao. Gets count of different word classes to be
     * presented in the user interface.
     */
    public GanbareService() {
        this.lexiconDao = new LexiconDao("jdbc:h2:./lexicon");
        this.userDao = new UserDao("jdbc:h2:./user");

        try {
            substantives = this.lexiconDao.getCount(1);
            adjectives = this.lexiconDao.getCount(2);
            verbs = this.lexiconDao.getCount(3);
            adverbs = this.lexiconDao.getCount(4);
        } catch (Exception e) {

        }

    }

    /**
     *
     * @param language user set language in which questions are presented
     * (either Finnish or Japanese)
     * @param substantives boolean value whether substantive words are set to be
     * included in session
     * @param adjectives boolean value whether adjective words are set to be
     * included in session
     * @param verbs boolean value whether verb words are set to be included in
     * session
     * @param adverbs boolean value whether adverb words are set to be included
     * in session
     * @param length user set length for the number of questions to be asked
     * @return boolean value whether new session was successfully created.
     */
    public boolean newSession(String language, boolean substantives, boolean adjectives, boolean verbs, boolean adverbs, int length) {

        if (!(language.equals("suomi") || language.equals("日本語")) || (substantives == false && adjectives == false && verbs == false && adverbs == false) || length < 1) {
            return false;
        }

        SqlParameters sqlParams = new SqlParameters(language, substantives, adjectives, verbs, adverbs, length);

        ArrayList<Word> lexicon = new ArrayList<>();

        try {
            lexicon = this.lexiconDao.createLexicon(sqlParams);
        } catch (SQLException e) {

            return false;
        }

        this.session = new Session(language, lexicon, length);

        return true;

    }

    /**
     * @return new question
     */
    public String newQuestion() {
        return this.session.newQuestion();
    }

    /**
     * @return number of questions to be asked
     */
    public int getSessionLength() {
        return this.session.getLength();
    }

    /**
     *
     * @param answer user input for the asked question
     * @return feedback corresponding correctness of the user answer
     */
    public String getFeedback(String answer) {
        return this.session.getFeedback(answer);
    }

    /**
     * @return number of the current question
     */
    public int getCurrentQuestionNum() {
        return this.session.getCurrentQuestionNum();
    }

    /**
     *
     * @return String feedback of the whole session
     */
    public String getSessionReview() {
        return this.session.getReview();
    }

    /**
     * Presented in the user interface so the user knows the maximum number of
     * words that can be set for the session (session length)
     *
     * @param substantives boolean value whether substantives are selected
     * @param adjectives boolean value whether adjectives are selected
     * @param verbs boolean value whether verbs are selected
     * @param adverbs boolean value whether adverbs are selected
     * @return the maximum number of the words in the lexicon combined from the
     * selected word classes.
     */
    public int getTotalWords(boolean substantives, boolean adjectives, boolean verbs, boolean adverbs) {

        int totalWordCount = 0;

        if (substantives) {
            totalWordCount += this.substantives;
        }

        if (adjectives) {
            totalWordCount += this.adjectives;
        }

        if (verbs) {
            totalWordCount += this.verbs;
        }

        if (adverbs) {
            totalWordCount += this.adverbs;
        }

        return totalWordCount;
    }

    /**
     *
     * @param name user set name
     * @param password user set password
     * @return boolean value whether or not new user could be registered
     */
    public boolean newUser(String name, String password) {

        try {
            this.userDao.newUser(name, password);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    /**
     *
     * @param name user set name
     * @param password user set password
     * @return boolean value whether login is successful
     */
    public boolean loginUser(String name, String password) {

        try {
            return this.userDao.loginUser(name, password);
        } catch (Exception e) {

            return false;
        }

    }

    /**
     *
     * @param original original word that is supposed to be in the database
     * @param synonym synonym for the original word
     * @return boolean value whether or not synonym was successfully added to
     * the database
     */
    public boolean addFinnishSynonym(String original, String synonym) {
        if (original.isEmpty() || synonym.isEmpty()) {
            return false;
        }

        try {
            return lexiconDao.addFinnishSynonym(original, synonym);
        } catch (Exception e) {

        }

        return false;
    }

    /**
     *
     * @param finnish new word in Finnish
     * @param kana new word in Japanase hiragakana or katakana
     * @param romaji new word in romaji
     * @param wordClass class of the new word (e.g. substantive, verb, etc.)
     * @param chapter chapter in which word is first presented in University of
     * Helsinki's japanase course
     * @return boolean value whether or not word was successfully added to the
     * database
     */
    public boolean addNewWord(String finnish, String kana, String romaji, String wordClass, int chapter) {
        if (finnish.isEmpty() || kana.isEmpty() || romaji.isEmpty()) {
            return false;
        }
        int wordClassNum = 0;
        if (wordClass.equals("substantiivi")) {
            wordClassNum = 1;
        } else if (wordClass.equals("adjektiivi")) {
            wordClassNum = 2;
        } else if (wordClass.equals("verbi")) {
            wordClassNum = 3;
        } else if (wordClass.equals("adverbi")) {
            wordClassNum = 4;
        }
        try {
            System.out.println("Lisätään: " + finnish + ", " + kana + ", " + romaji + ", " + wordClassNum + ", " + chapter);
            return lexiconDao.addWord(finnish, kana, romaji, wordClassNum, chapter);
        } catch (Exception e) {
            return false;
        }
    }
    
    

    /**
     *
     * @return lexiconDao formed for this service
     */
    public LexiconDao getLexiconDao() {
        return this.lexiconDao;
    }

    /**
     *
     * @return session formed for this service
     */
    public Session getSession() {
        return this.session;
    }

}
