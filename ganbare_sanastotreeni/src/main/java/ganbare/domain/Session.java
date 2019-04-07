package ganbare.domain;

import java.util.*;

public class Session {

    private String questionLanguage;
    private boolean verbs;
    private boolean adjectives;
    private boolean substantives;
    private int length;

    private int questionNum;
    private ArrayList<String> sessionLexicon;
    private int correctAnswers;

    public Session(String language, boolean verbs, boolean adjectives, boolean substantives, int length) {
        this.questionLanguage = language;
        this.verbs = verbs;
        this.adjectives = adjectives;
        this.substantives = substantives;
        this.length = length;
    }

    public void prepareSessionLexicon(ArrayList<String> lexicon) {
        this.sessionLexicon = new ArrayList<>();
        Collections.shuffle(lexicon);

        for (int i = 0; i < this.length; i++) {
            this.sessionLexicon.add(lexicon.get(i));
        }

    }

    public int getLength() {
        return this.length;
    }

    public String getQuestion() {
        String[] words = sessionLexicon.get(questionNum).split(":");

        if (this.questionLanguage.equals("suomi")) {
            return words[2];
        }
        return words[0];
    }

    public String getAnswer() {
        String[] words = sessionLexicon.get(questionNum).split(":");
        if (this.questionLanguage.equals("suomi")) {
            return words[1];
        }
        return words[2];

    }

    public boolean correctAnswer(String userInput) {

        if (userInput.toLowerCase().equals(getAnswer().toLowerCase())) {
            correctAnswers++;
            return true;
        }

        return false;
    }

    public int getCorrectAnswers() {
        return this.correctAnswers;
    }

    public boolean incrementCounter() {
        questionNum++;

        if (this.questionNum > this.length - 1) {
            return false;
        }

        return true;

    }

    public int getQuestionNum() {
        return questionNum;
    }

    public String getFeedback(boolean correct, String userInput) {
        if (correct) {
            return "Oikea vastaus!";
        }

        return "Oikea vastaus oli: " + getAnswer()
                + "\n Vastasit: " + userInput;
    }

    public String getReview() {
        return "Oikeita vastauksia: " + this.correctAnswers + "\n"
                + "Kysymyksiä yhteensä: " + this.questionNum;
    }

}
