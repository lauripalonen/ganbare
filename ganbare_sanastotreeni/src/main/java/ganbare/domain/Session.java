package ganbare.domain;

import ganbare.dao.LexiconDao;

import java.util.*;

/**
 * Session class manages items related to lexicon practice session,
 * including questions, answers, language, session length, etc.
 */
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

    /**
     * 
     * @param language language in which questions are presented, set by user
     * @param lexicon ArrayList of words created by lexiconDao
     * @param sessionLength Length of the practice session set by user
     */
    public Session(String language, ArrayList<Word> lexicon, int sessionLength) {
        this.language = language;
        this.lexicon = lexicon;

        this.sessionLength = sessionLength;

        Collections.shuffle(lexicon);

    }

    /**
     * 
     * @return number of the questions to be presented 
     */
    public int getLength() {
        return this.sessionLength;
    }

    /**
     * Returns a new question and increases the current question
     * counter. Returns null if question counter has surpassed the session
     * length. Sets current question and answer to correct words corresponding
     * to the questioning language.
     * 
     * @return new question from the lexicon ArrayList,
     * where index corresponds to the current question number
     */
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

    /**
     * 
     * @return current question
     */
    public String getCurrentQuestion() {
        return this.currentQuestion;
    }

    /**
     * 
     * @return current answer
     */
    public String getCurrentAnswer() {
        return this.currentAnswer;
    }

    /**
     * 
     * @return number of questions to which user has given correct answer
     */
    public int getCorrectAnswers() {
        return this.correctAnswers;
    }

    /**
     * 
     * @return number of the current question
     */
    public int getCurrentQuestionNum() {
        return currentQuestionNum;
    }

    /**
     * 
     * @param userInput User's answer on the current question
     * @return feedback corresponding to the user's input
     */
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
    /**
     * 
     * @return review corresponding to the number of user's correct answers
     * and number of total questions
     */
    public String getReview() {
        return "Oikeita vastauksia: " + this.correctAnswers + "\n"
                + "Kysymyksiä yhteensä: " + (this.totalAnswers);
    }

}
