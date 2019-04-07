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
import java.util.*;

public class Session {

    private String questionLanguage;
    private boolean verbs;
    private boolean adjectives;
    private boolean substantives;
    private int length;

    private int currentQuestion;
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

    public boolean getVerbs() {
        return this.verbs;
    }

    public boolean getAdjectives() {
        return this.adjectives;
    }

    public boolean getSubstantives() {
        return this.substantives;
    }

    public String getQuestion() {
        String[] words = sessionLexicon.get(currentQuestion).split(":");
        
        if(this.questionLanguage.equals("suomi")){
            return words[2];
        }
        return words[0];
    }

    public String getAnswer() {
        String[] words = sessionLexicon.get(currentQuestion).split(":");
        if(this.questionLanguage.equals("suomi")){
            return words[1];
        }
        return words[2];

    }

    public boolean correctAnswer(String userInput) {
        System.out.println("Vastasit: " + userInput);
        System.out.println("Oikea vastaus oli: " + getAnswer());

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
        currentQuestion++;

        if (this.currentQuestion > this.length - 1) {
            return false;
        }

        return true;

    }

    public int currentQuestion() {
        return currentQuestion;
    }
    
    public String getFeedback(boolean correct, String userInput){
        if (correct){
            return "Oikea vastaus!";
        }
        
        return "Oikea vastaus oli: " + getAnswer()
                + "\n Vastasit: " + userInput;
    }

    @Override
    public String toString() {
        return "Oikeita vastauksia: " + this.correctAnswers + "\n"
                + "Kysymyksiä yhteensä: " + this.currentQuestion();
    }

}
