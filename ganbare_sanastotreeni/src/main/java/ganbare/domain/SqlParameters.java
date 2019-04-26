package ganbare.domain;

/**
 * Parameters for the SQL queries. Changes string values of word classes into
 * integer values which can be used with the lexicon database. If user has not
 * set a certain class for the session, its value is then 0. Otherwise it has
 * its corresponding database value (e.g. substantive is 1, adjective is 2, etc.)
 */
public class SqlParameters {

    private String language;

    private int substantives;
    private int adjectives;
    private int verbs;
    private int adverbs;

    private int sessionLength;

    /**
     *
     * @param language user set language in which questions are presented
     * @param substantives boolean value of whether or not substantives are
     * included in the session
     * @param adjectives boolean value of whether or not adjectives are included
     * in the session
     * @param verbs boolean value of whether or not verbs are included in the
     * session
     * @param adverbs boolean value of whether or not adverbs are included in
     * the session
     * @param sessionLength number of the questions to be asked in the practice
     * session
     */
    public SqlParameters(String language, boolean substantives, boolean adjectives, boolean verbs, boolean adverbs, int sessionLength) {

        if (substantives) {
            this.substantives = 1;
        }
        if (adjectives) {
            this.adjectives = 2;
        }
        if (verbs) {
            this.verbs = 3;
        }
        if (adverbs) {
            this.adverbs = 4;
        }

        this.language = language;
        this.sessionLength = sessionLength;

    }

    /**
     *
     * @param substantives boolean value of whether or not substantives are
     * included in the session
     * @param adjectives boolean value of whether or not adjectives are included
     * in the session
     * @param verbs boolean value of whether or not verbs are included in the
     * session
     * @param adverbs boolean value of whether or not adverbs are included in
     * the session
     */
    public SqlParameters(boolean substantives, boolean adjectives, boolean verbs, boolean adverbs) {

        if (substantives) {
            this.substantives = 1;
        }
        if (adjectives) {
            this.adjectives = 2;
        }
        if (verbs) {
            this.verbs = 3;
        }
        if (adverbs) {
            this.adverbs = 4;
        }

    }

    /**
     *
     * @return language in which questions are presented
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @return integer (database) value of the substantives
     */
    public int getSubstantives() {
        return substantives;
    }

    /**
     *
     * @return integer (database) value of the adjectives
     */
    public int getAdjectives() {
        return adjectives;
    }

    /**
     *
     * @return integer (database) value of the verbs
     */
    public int getVerbs() {
        return verbs;
    }

    /**
     *
     * @return integer (database) value of the adverbs
     */
    public int getAdverbs() {
        return adverbs;
    }

    /**
     * 
     * @return number of the questions to be asked
     */
    public int getSessionLength() {
        return sessionLength;
    }

}
