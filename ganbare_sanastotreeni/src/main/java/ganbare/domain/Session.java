package ganbare.domain;
import ganbare.dao.LexiconDao;

import java.util.*;

public class Session {

    private ArrayDeque<String> questions;
    private ArrayDeque<String> answers;

    private int sessionLength;

    private int currentQuestionNum;
    private int correctAnswers;
    private int totalAnswers;

    private String currentQ = "";
    private String currentA = "";
    
    private LexiconDao lexiconDao;

    public Session(String language, ArrayList<String[]> lexicon, int sessionLength, LexiconDao lexiconDao) {
        questions = new ArrayDeque<>();
        answers = new ArrayDeque<>();

        this.sessionLength = sessionLength;

        Collections.shuffle(lexicon);

        for (int i = 0; i < sessionLength; i++) {
            String[] word = lexicon.get(i);

            if (language.equals("suomi")) {
                questions.add(word[0]);
                answers.add(word[2]);
            } else {
                questions.add(word[1]);
                answers.add(word[0]);
            }
        }
        
        this.lexiconDao = lexiconDao;

    }

    public int getLength() {
        return this.sessionLength;
    }

    public ArrayDeque<String> getQuestionStack() {
        return this.questions;
    }

    public ArrayDeque<String> getAnswerStack() {
        return this.answers;
    }

    public String getQuestion() {

        this.currentQ = questions.poll();
        this.currentA = answers.poll();
        return this.currentQ;

    }

    public int getCorrectAnswers() {
        return this.correctAnswers;
    }

    public boolean incrementCounter() {
        if (currentQuestionNum >= sessionLength - 1) {
            return false;
        }
        currentQuestionNum++;
        return true;
    }

    public String getCurrentQuestion() {
        return this.currentQ;
    }

    public String getCurrentAnswer() {
        return this.currentA;
    }

    public int getCurrentQuestionNum() {
        return currentQuestionNum;
    }

    public String getFeedback(String userInput) {
        totalAnswers++;

        String input = userInput.toLowerCase().trim();
        String answer = this.currentA.toLowerCase().trim();

        if (input.equals(answer)) {
            correctAnswers++;
            return "Oikea vastaus!";
        }
        
        boolean synonymFound = false;
        
        try {
            synonymFound = lexiconDao.getFinnishSynonyms(answer).contains(userInput);
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }
        
        if (synonymFound){
            correctAnswers++;
            return "Oikea vastaus!";
        }

        return "Oikea vastaus oli: " + this.currentA
                + "\nVastasit: " + userInput;
    }

    public String getReview() {
        return "Oikeita vastauksia: " + this.correctAnswers + "\n"
                + "Kysymyksiä yhteensä: " + (this.totalAnswers);
    }

}
