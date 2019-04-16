package ganbare.domain;

public class SqlParameters {

    private String language;

    private int substantives;
    private int adjectives;
    private int verbs;
    private int adverbs;
    //private int interrogatives;

    private int sessionLength;

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

//    public int[] getClassSet(){
//        int[] wordClass = new int[4];
//        wordClass[0] = substantives;
//        wordClass[1] = adjectives;
//        wordClass[2] = verbs;
//        wordClass[3] = adverbs;
//        
//        return wordClass;
//    }
    public String getLanguage() {
        return language;
    }

    public int getSubstantives() {
        return substantives;
    }

    public int getAdjectives() {
        return adjectives;
    }

    public int getVerbs() {
        return verbs;
    }

    public int getAdverbs() {
        return adverbs;
    }

    public int getSessionLength() {
        return sessionLength;
    }

}
