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

    private int length;
    private int currentQuestion;
    private ArrayList<String> sessionLexicon;
    private int correctAnswers;

    public Session(int length, ArrayList<String> lexicon) {
        this.length = length;
        this.sessionLexicon = lexicon;
        Collections.shuffle(sessionLexicon);

    }

    public int getLength() {
        return this.length;
    }

    public String getQuestion() {
        String[] words = sessionLexicon.get(currentQuestion).split(":");
        return words[0];
    }

    public String getAnswer() {
        String[] words = sessionLexicon.get(currentQuestion).split(":");
        return words[1];

    }

    public boolean correctAnswer(String userInput) {
        if (userInput.equals(getAnswer())) {
            correctAnswers++;
            return true;
        }

        return false;
    }

    public int getCorrectAnswers() {
        return this.correctAnswers;
    }

    public void incrementCounter() {
        currentQuestion++;
    }

    public int currentQuestion() {
        return currentQuestion;
    }

    @Override
    public String toString() {
        return "Oikeita vastauksia: " + this.correctAnswers + "\n"
                + "Kysymyksiä yhteensä: " + this.currentQuestion();
    }

}
