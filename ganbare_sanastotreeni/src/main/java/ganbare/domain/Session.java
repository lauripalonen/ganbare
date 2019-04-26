package ganbare.domain;

import ganbare.dao.LexiconDao;

import java.util.*;

public class Session {

    private ArrayList<Word> lexicon;

    private String language;
    private int sessionLength;

    private int currentQuestionNum;
    private int correctAnswers;
    private int totalAnswers;

    private Word currentWord;
    private String currentQuestion;
    private String currentAnswer;

    public Session(String language, ArrayList<Word> lexicon, int sessionLength) {
        this.language = language;
        this.lexicon = lexicon;

        this.sessionLength = sessionLength;

        Collections.shuffle(lexicon);

    }

    public int getLength() {
        return this.sessionLength;
    }

    public String newQuestion() {
        if (currentQuestionNum >= sessionLength) {
            return null;
        }

        currentWord = lexicon.get(currentQuestionNum);

        if (language.equals("suomi")) {
            currentQuestion = currentWord.getFinnish();
            currentAnswer = currentWord.getRomaji();
        } else {
            currentQuestion = currentWord.getKana();
            currentAnswer = currentWord.getFinnish();
        }

        currentQuestionNum++;

        return currentQuestion;

    }

    public String getCurrentQuestion() {
        return this.currentQuestion;
    }

    public String getCurrentAnswer() {
        return this.currentAnswer;
    }

    public int getCorrectAnswers() {
        return this.correctAnswers;
    }

    public int getCurrentQuestionNum() {
        return currentQuestionNum;
    }

    public String getFeedback(String userInput) {
        totalAnswers++;

        String input = userInput.toLowerCase().trim();
        String answer = this.currentAnswer.toLowerCase().trim();

        if (input.equals(answer)) {
            correctAnswers++;
            return "Oikea vastaus!";

        } else if (language.equals("日本語")) {
            if (equalsSynonym(input)) {
                return "Oikea vastaus!";
            }
        }

        return "Oikea vastaus oli: " + this.currentAnswer
                + "\nVastasit: " + userInput;
    }

    public boolean equalsSynonym(String userInput) {
        for (String synonym : currentWord.getFinnishSynonyms()) {
            if (userInput.equals(synonym.toLowerCase().trim())) {
                return true;
            }

        }

        return false;
    }

    public String getReview() {
        return "Oikeita vastauksia: " + this.correctAnswers + "\n"
                + "Kysymyksiä yhteensä: " + (this.totalAnswers);
    }

}
