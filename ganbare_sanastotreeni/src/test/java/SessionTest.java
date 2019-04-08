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
    ArrayList<String> lexicon;

    @Before
    public void setUp() {
        this.session = new Session("suomi", true, true, false, 5);

        this.lexicon = new ArrayList<>();

        lexicon.add("アパート:apaato:kerrostalo:1:8");
        lexicon.add("バスケットボール:basukettobooru:koripallo:1:8");
        lexicon.add("チョコレート:chokoreeto:suklaa:1:8");
        lexicon.add("だんす:dansu:tanssi:1:8");
        lexicon.add("こどものひ:kodomonohi:lasten päivä:1:8");
        lexicon.add("まえ:mae:aiemmin:1:8");
        lexicon.add("ちゅうごく:chuugoku:Kiina:1:8");

        this.session.prepareSessionLexicon(lexicon);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorSetsCorrectValues() {
        Session newSession = new Session("suomi", true, false, true, 10);

        String output = newSession.toString();

        assertEquals("Sessiomäärittelyt: \nKyselykieli: suomi\nVerbit: true\nAdjektiivit: false\nSubstantiivit: true\nSession pituus: 10", output);

    }

    @Test
    public void sessionLexiconOfCorrectSizeIsPrepared() {
        int lexiconSize = session.getSessionLexicon().size();

        assertEquals(5, lexiconSize);
    }

    @Test
    public void sessionLengthEqualsSessionLexiconSize() {

        int sessionLength = session.getLength();

        assertEquals(5, sessionLength);

    }

    @Test
    public void getQuestionReturnsErrorIfSessionLexiconIsNotPrepared() {
        Session newSession = new Session("suomi", true, true, true, 5);

        String output = "";

        try {
            output = newSession.getQuestion();
        } catch (Exception e) {
            output = e.getMessage();
        }

        assertEquals("Sanastossa ei ole sanoja!", output);

    }

    @Test
    public void getQuestionReturnsCorrectFinnishWord() {

        String[] words = session.getSessionLexicon().get(0).split(":");
        String word = words[2];

        String question = session.getQuestion();

        assertEquals(word, question);

    }
    
    @Test
    public void getQuestionReturnsCorrectJapaneseWord(){
        Session newSession = new Session("日本語", true, true, true, 5);
        
        newSession.prepareSessionLexicon(lexicon);
        
        String[] words = newSession.getSessionLexicon().get(0).split(":");
        String word = words[0];
        
        String question = newSession.getQuestion();
        
        assertEquals(word, question);
        
        
    }

    @Test
    public void getAnswerReturnsCorrectJapaneseWord() {

        String[] words = session.getSessionLexicon().get(0).split(":");
        String word = words[1];

        String answer = session.getAnswer();

        assertEquals(word, answer);
    }
    
    @Test
    public void getAnswerReturnsCorrectFinnishWord() {
        Session newSession = new Session("日本語", true, true, true, 5);
        
        newSession.prepareSessionLexicon(lexicon);
        
        String[] words = newSession.getSessionLexicon().get(0).split(":");
        String word = words[2];
        
        String answer = newSession.getAnswer();
        
        assertEquals(word, answer);
    }

    @Test
    public void correctAnswerReturnsTrueIfCorrect() {

        String userAnswer = session.getAnswer();

        Boolean correct = session.correctAnswer(userAnswer);

        assertEquals(true, correct);

    }

    @Test
    public void correctAnswerReturnsFalseIfWrong() {

        String userAnswer = "VääräVastaus";

        Boolean correct = session.correctAnswer(userAnswer);

        assertEquals(false, correct);
    }
    
    @Test
    public void zeroCorrectAnswersWhenNoQuestionsAnswered(){
        int correctAnswers = session.getCorrectAnswers();
        
        assertEquals(0, correctAnswers);
        
    }
    
    @Test
    public void answeringCorrectlyIncreasesCorrectAnswers(){
        String userAnswer = session.getAnswer();
        
        session.correctAnswer(userAnswer);
        
        int correctAnswers = session.getCorrectAnswers();
        
        assertEquals(1, correctAnswers);
    }
    
    
    @Test
    public void incrementCounterIncreasesQuestionNum(){
        session.incrementCounter();
        session.incrementCounter();
        
        int questionNum = session.getQuestionNum();
        
        assertEquals(2, questionNum);
    }
    
    @Test
    public void incrementCounterDoesNotIncrementOverSessionLength(){
        
        session.incrementCounter();
        session.incrementCounter();
        session.incrementCounter();
        session.incrementCounter();
        session.incrementCounter();
        session.incrementCounter();
        session.incrementCounter();
        
        int questionNum = session.getQuestionNum();
        
        assertEquals(4, questionNum);
        
    }
    
    @Test
    public void feedbackIsGivenCorrectlyWhenAnswerIsCorrect(){
        String userInput = session.getAnswer();
        
        String feedback = session.getFeedback(true, userInput);
        
        assertEquals("Oikea vastaus!", feedback);
    }
    
    @Test
    public void feedbackIsGivenCorrectlyWhenAnswerIsNotCorrect(){
        String correctAnswer = session.getAnswer();
        
        String feedback = session.getFeedback(false, "VääräVastaus");
        
        assertEquals(("Oikea vastaus oli: " + correctAnswer + "\nVastasit: VääräVastaus"), feedback);
        
    }
    
    @Test
    public void getReviewReturnsCorrectReview(){
        String userInput = session.getAnswer();
        
        session.correctAnswer(userInput);
        session.incrementCounter();
        
        userInput = "VääräVastaus";
        session.correctAnswer(userInput);
        session.incrementCounter();  
        
        String review = session.getReview();
        
        assertEquals("Oikeita vastauksia: 1\nKysymyksiä yhteensä: 2", review);
    }
    

}
