package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ganbare.domain.Session;
import ganbare.domain.Word;
import java.util.ArrayList;

public class SessionTest {

    Session session;
    ArrayList<Word> originalLexicon;
    ArrayList<Word> sessionLexicon;

    @Before
    public void setUp() {
        this.originalLexicon = new ArrayList<>();
        this.sessionLexicon = new ArrayList<>();

        Word word01 = new Word("testi", "テスト", "tesuto", 1, 0);
        Word word02 = new Word("suomi", "フインランド", "finrando", 1, 0);
        Word word03 = new Word("japanin kieli", "にほんご", "nihongo", 1, 0);
        Word word04 = new Word("opiskella", "べんきょうする", "benkyousuru", 3, 0);
        Word word05 = new Word("yrittää parhaansa", "がんばる", "ganbaru", 3, 0);
        Word word06 = new Word("söpö", "かわいい", "kawaii", 2, 0);
        Word word07 = new Word("kissa", "ねこ", "neko", 1, 0);
        Word word08 = new Word("tunnin ajan", "じかん", "jikan", 4, 0);
        Word word09 = new Word("mitä?", "なに", "nani", 5, 0);
        Word word10 = new Word("jes", "やった", "yatta", 1, 0);

        originalLexicon.add(word01);
        originalLexicon.add(word02);
        originalLexicon.add(word03);
        originalLexicon.add(word04);
        originalLexicon.add(word05);
        originalLexicon.add(word06);
        originalLexicon.add(word07);
        originalLexicon.add(word08);
        originalLexicon.add(word09);
        originalLexicon.add(word10);

        for (Word word : this.originalLexicon) {
            sessionLexicon.add(word);
        }

        this.session = new Session("suomi", this.sessionLexicon, 5);

    }

    @Test
    public void constructorSetsCorrectSessionLength() {
        int length = this.session.getLength();

        assertEquals(5, length);
    }

    @Test
    public void newQuestionUpdatesCurrentQuestion() {
        String q01 = this.session.newQuestion();

        String q02 = this.session.newQuestion();

        boolean differentQ = !q01.equals(q02);

        assertTrue(differentQ);
    }

    @Test
    public void newQuestionUpdatesCurrentAnswer() {
        this.session.newQuestion();

        String a01 = this.session.getCurrentAnswer();

        this.session.newQuestion();

        String a02 = this.session.getCurrentAnswer();

        boolean differentA = !a01.equals(a02);

        assertTrue(differentA);
    }

    @Test
    public void newQuestionReturnsCurrentQuestion() {
        String q01 = this.session.newQuestion();
        String q02 = this.session.getCurrentQuestion();

        boolean same = q01.equals(q02);

        assertTrue(same);

    }

    @Test
    public void constructorShufflesLexicon() {
        boolean same = true;

        for (int i = 0; i < 5; i++) {
            String originalWord = this.originalLexicon.get(i).getFinnish();
            String sessionWord = this.session.newQuestion();

            if (!originalWord.equals(sessionWord)) {
                same = false;
            }

        }

        assertFalse(same);

    }

    @Test
    public void sessionLengthEqualsSessionLexiconSize() {

        int sessionLength = session.getLength();

        assertEquals(5, sessionLength);

    }

    @Test
    public void sessionBeginsWithNoCorrectAnswers() {
        int correctAnswers = this.session.getCorrectAnswers();

        assertEquals(0, correctAnswers);

    }

    @Test
    public void getFeedbackWithCorrectInputIncreasesCorrectAnswers() {

        this.session.newQuestion();

        String answer = this.session.getCurrentAnswer();

        this.session.getFeedback(answer);

        int amount = this.session.getCorrectAnswers();

        assertEquals(1, amount);
    }

    @Test
    public void getFeedbackWithIncorrectAnswerMakesNoChangeToCorrectAnswers() {
        this.session.newQuestion();

        String answer = "Incorrect Answer";

        this.session.getFeedback(answer);

        int amount = this.session.getCorrectAnswers();

        assertEquals(0, amount);
    }

    @Test
    public void getFeedbackForIncorrectAnswerReturnsCorrectFeedback() {
        this.session.newQuestion();

        String answer = "Incorrect Answer";
        String correct = this.session.getCurrentAnswer();

        String feedback = this.session.getFeedback(answer);

        assertEquals(("Oikea vastaus oli: " + correct + "\nVastasit: Incorrect Answer"), feedback);

    }

    @Test
    public void getFeedbackForCorrectAnswerReturnsCorrectFeedback() {
        this.session.newQuestion();

        String answer = this.session.getCurrentAnswer();

        String feedback = this.session.getFeedback(answer);

        assertEquals("Oikea vastaus!", feedback);
    }

    @Test
    public void getReviewReturnsCorrectReviewForAllCorrectAnswers() {

        for (int i = 0; i < 5; i++) {
            this.session.newQuestion();

            this.session.getFeedback(this.session.getCurrentAnswer());

        }

        String review = this.session.getReview();

        assertEquals("Oikeita vastauksia: 5\nKysymyksiä yhteensä: 5", review);
    }

    @Test
    public void getReviewReturnsCorrectReviewForAllIncorrectAnswers() {

        for (int i = 0; i < 5; i++) {
            this.session.newQuestion();

            this.session.getFeedback("Incorrect Answer");

        }

        String review = this.session.getReview();

        assertEquals("Oikeita vastauksia: 0\nKysymyksiä yhteensä: 5", review);
    }

    @Test
    public void getReviewReturnsCorrectReviewForSomeCorrectAnswers() {

        for (int i = 0; i < 5; i++) {
            this.session.newQuestion();

            if (i % 2 == 0) {
                this.session.getFeedback(this.session.getCurrentAnswer());
            } else {
                this.session.getFeedback("Incorrect Answer");
            }

        }

        String review = this.session.getReview();

        assertEquals("Oikeita vastauksia: 3\nKysymyksiä yhteensä: 5", review);

    }

    @Test
    public void getCurrentQuestionNumReturnsZeroWhenNoQuestionsAsked() {
        int num = this.session.getCurrentQuestionNum();

        assertEquals(0, num);
    }

}
