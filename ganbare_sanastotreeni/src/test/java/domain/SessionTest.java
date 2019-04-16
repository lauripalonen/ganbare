package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ganbare.domain.Session;
import java.util.ArrayList;

/**
 *
 * @author palolaur
 */
public class SessionTest {

    Session session;
    ArrayList<String[]> originalLexicon;
    ArrayList<String[]> sessionLexicon;

    @Before
    public void setUp() {
        this.originalLexicon = new ArrayList<>();
        this.sessionLexicon = new ArrayList<>();

        String[] word01 = {"testi", "テスト", "tesuto"};
        originalLexicon.add(word01);
        String[] word02 = {"suomi", "フインランド", "finrando"};
        originalLexicon.add(word02);
        String[] word03 = {"japanin kieli", "にほんご", "nihongo"};
        originalLexicon.add(word03);
        String[] word04 = {"opiskella", "べんきょうする", "benkyousuru"};
        originalLexicon.add(word04);
        String[] word05 = {"yrittää parhaansa", "がんばる", "ganbaru"};
        originalLexicon.add(word05);
        String[] word06 = {"söpö", "かわいい", "kawaii"};
        originalLexicon.add(word06);
        String[] word07 = {"kissa", "ねこ", "neko"};
        originalLexicon.add(word07);
        String[] word08 = {"tunnin ajan", "じかん", "jikan"};
        originalLexicon.add(word08);
        String[] word09 = {"mitä?", "なに", "nani"};
        originalLexicon.add(word09);
        String[] word10 = {"jes", "やった", "yatta"};
        originalLexicon.add(word10);

        for (String[] word : this.originalLexicon) {
            sessionLexicon.add(word);
        }

        this.session = new Session("suomi", this.sessionLexicon, 5);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorFillsQuestionStack() {
        boolean filled = !this.session.getQuestionStack().isEmpty();

        assertTrue(filled);

    }

    @Test
    public void constructorFillsAnswerStack() {
        boolean filled = !this.session.getAnswerStack().isEmpty();

        assertTrue(filled);
    }

    @Test
    public void constructorSetsCorrectSessionLength() {
        int length = this.session.getLength();

        assertEquals(5, length);
    }

    @Test
    public void constructorCreatesQuestionStackOfCorrectLength() {
        int length = this.session.getQuestionStack().size();

        assertEquals(5, length);

    }

    @Test
    public void constructorCreatesAnswerStackOfCorrectLength() {
        int length = this.session.getAnswerStack().size();

        assertEquals(5, length);
    }

    @Test
    public void getQuestionUpdatesCurrentQuestion() {
        String q01 = this.session.getCurrentQuestion();

        this.session.getQuestion();

        String q02 = this.session.getCurrentQuestion();

        boolean differentQ = !q01.equals(q02);

        assertTrue(differentQ);
    }

    @Test
    public void getQuestionUpdatesCurrentAnswer() {
        String a01 = this.session.getCurrentAnswer();

        this.session.getQuestion();

        String a02 = this.session.getCurrentAnswer();

        boolean differentA = !a01.equals(a02);

        assertTrue(differentA);
    }

    @Test
    public void getQuestionReturnsCurrentQuestion() {
        String q01 = this.session.getQuestion();
        String q02 = this.session.getCurrentQuestion();

        boolean same = q01.equals(q02);

        assertTrue(same);

    }

    @Test
    public void constructorShufflesLexicon() {
        boolean same = true;

        for (int i = 0; i < 5; i++) {
            String original = this.originalLexicon.get(i)[0];
            String session = this.session.getQuestion();

            if (!original.equals(session)) {
                same = false;
            }

        }

        assertFalse(same);

    }

    @Test
    public void AnswerAndQuestionStacksCorrelatesWithOriginalLexicon() {
        String question = this.session.getQuestion();
        String answer = this.session.getCurrentAnswer();

        boolean sameAnswer = false;

        for (String[] word : this.originalLexicon) {
            if (word[0].equals(question)) {
                sameAnswer = word[02].equals(answer);
            }
        }

        assertTrue(sameAnswer);
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

        this.session.getQuestion();

        String answer = this.session.getCurrentAnswer();

        this.session.getFeedback(answer);

        int amount = this.session.getCorrectAnswers();

        assertEquals(1, amount);
    }

    @Test
    public void getFeedbackWithIncorrectAnswerMakesNoChangeToCorrectAnswers() {
        this.session.getQuestion();

        String answer = "Incorrect Answer";

        this.session.getFeedback(answer);

        int amount = this.session.getCorrectAnswers();

        assertEquals(0, amount);
    }

    @Test
    public void getFeedbackForIncorrectAnswerReturnsCorrectFeedback() {
        this.session.getQuestion();

        String answer = "Incorrect Answer";
        String correct = this.session.getCurrentAnswer();

        String feedback = this.session.getFeedback(answer);

        assertEquals(("Oikea vastaus oli: " + correct + "\nVastasit: Incorrect Answer"), feedback);

    }

    @Test
    public void getFeedbackForCorrectAnswerReturnsCorrectFeedback() {
        this.session.getQuestion();

        String answer = this.session.getCurrentAnswer();

        String feedback = this.session.getFeedback(answer);

        assertEquals("Oikea vastaus!", feedback);
    }

    @Test
    public void getReviewReturnsCorrectReviewForAllCorrectAnswers() {

        for (int i = 0; i < 5; i++) {
            this.session.getQuestion();

            this.session.getFeedback(this.session.getCurrentAnswer());

        }

        String review = this.session.getReview();

        assertEquals("Oikeita vastauksia: 5\nKysymyksiä yhteensä: 5", review);
    }

    @Test
    public void getReviewReturnsCorrectReviewForAllIncorrectAnswers() {

        for (int i = 0; i < 5; i++) {
            this.session.getQuestion();

            this.session.getFeedback("Incorrect Answer");

        }

        String review = this.session.getReview();

        assertEquals("Oikeita vastauksia: 0\nKysymyksiä yhteensä: 5", review);
    }

    @Test
    public void getReviewReturnsCorrectReviewForSomeCorrectAnswers() {

        for (int i = 0; i < 5; i++) {
            this.session.getQuestion();

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
    public void getCurrentQuestionNumReturnsZeroWhenNoQuestionsAsked(){
        int num = this.session.getCurrentQuestionNum();
        
        assertEquals(0, num);
    }
    
    @Test
    public void incrementCounterIncreasesQuestionNumByOne(){
        this.session.incrementCounter();
        
        int num = this.session.getCurrentQuestionNum();
        
        assertEquals(1, num);
        
    }
    
    @Test
    public void incrementCounterReturnsTrueIfIncrementable(){
        
        assertTrue(this.session.incrementCounter());
        
    }
    
    @Test
    public void incrementCounterReturnFalseIfUnincrementable(){
        
        for(int i = 0; i < 5; i++){
            this.session.incrementCounter();
        }
        
        assertFalse(this.session.incrementCounter());
        
    }


}
