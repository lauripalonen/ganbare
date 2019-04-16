package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ganbare.domain.GanbareService;
import ganbare.domain.Session;

/**
 *
 * @author palolaur
 */
public class GanbareServiceTest {

    GanbareService service;

    @Before
    public void setUp() {
        service = new GanbareService();

        this.service.newSession("suomi", true, true, true, true, 5);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void concstructorCreatesNewDao() {
        boolean notNull = this.service.getLexiconDao() != null;

        assertTrue(notNull);

    }

    @Test
    public void sessionIsNullAfterConstruction() {
        GanbareService newService = new GanbareService();

        boolean nullSession = newService.getSession() == null;

        assertTrue(nullSession);

    }

    @Test
    public void newSessionCreatesANewSession() {

        boolean notNull = this.service.getSession() != null;

        assertTrue(notNull);
    }

    @Test
    public void newSessionReturnsFalseIfIncorrectLanguage() {
        GanbareService newService = new GanbareService();

        boolean output = newService.newSession("klingon", true, true, true, true, 5);

        assertFalse(output);
    }

    @Test
    public void newSessionReturnsFalseIfNoClassesSelected() {
        GanbareService newService = new GanbareService();

        boolean output = newService.newSession("suomi", false, false, false, false, 5);

        assertFalse(output);
    }

    @Test
    public void newSessionReturnsFalseIfSessionLengthIsZero() {
        GanbareService newService = new GanbareService();

        boolean output = newService.newSession("suomi", true, true, true, true, 0);

        assertFalse(output);
    }

    @Test
    public void newSessionReturnsFalseIfSessionLengthIsNegative() {
        GanbareService newService = new GanbareService();

        boolean output = newService.newSession("suomi", true, true, true, true, -2);

        assertFalse(output);
    }

    @Test
    public void getQuestionReturnsCorrectQuestion() {

        String initialQuestion = this.service.getQuestion();
        String correctQuestion = this.service.getSession().getCurrentQuestion();

        assertEquals(correctQuestion, initialQuestion);

    }

    @Test
    public void getSessionLengthReturnCorrectInt() {

        int length = this.service.getSessionLength();

        assertEquals(5, length);
    }

    @Test
    public void getFeedbackReturnsCorrectFeedbackForCorrectAnswer() {

        this.service.getQuestion();
        String answer = this.service.getSession().getCurrentAnswer();

        String feedback = this.service.getFeedback(answer);

        assertEquals("Oikea vastaus!", feedback);
    }
    
    @Test
    public void nextQuestionIncreasesSessionCounter(){        
        this.service.nextQuestion();
        
        int count = this.service.getSession().getCurrentQuestionNum();
        
        assertEquals(1, count);
    }
    
    @Test
    public void nextQuestionReturnsNewQuestion(){
        String q01 = this.service.nextQuestion();
        String q02 = this.service.nextQuestion();
        
        assertFalse(q01.equals(q02));
    }
    
    @Test
    public void getCurrentQuestionNumEqualsCurrentNumPlusOne(){
        this.service.nextQuestion();
        
        int currentNum = this.service.getCurrentQuestionNum();
        
        assertEquals(2, currentNum);
    }
    
    @Test
    public void getReviewReturnsCorrectReview(){
        for(int i = 0; i < 5; i++){
            this.service.getQuestion();
            
            String answer;
            
            if(i % 2 == 0){
                answer = this.service.getSession().getCurrentAnswer();
            } else {
                answer = "Incorrect Answer";
            }
            
            this.service.getFeedback(answer);
            
        }
        
        String review = this.service.getSessionReview();
        
        assertEquals("Oikeita vastauksia: 3\nKysymyksiä yhteensä: 5", review);
    }
    
    @Test
    public void getTotalWordsReturnsZeroIfNoClassesSelected(){
        int count = this.service.getTotalWords(false, false, false, false);
        
        assertEquals(0, count);
    }
    
    @Test
    public void getTotalWordsReturnsIntGreaterThanZeroIfAClassIsSelected(){
        int count = this.service.getTotalWords(true, false, true, false);
        
        assertTrue(count > 0);
    }
    
    @Test
    public void getLexiconDaoReturnsLexiconDao(){
        boolean notNull = this.service.getLexiconDao() != null;
        
        assertTrue(notNull);
    }
    
    @Test
    public void nextQuestionReturnsNullIfNoQuestionsLeft(){
        for(int i = 0; i < 5; i++){
            this.service.nextQuestion();
        }
        
        String question = this.service.nextQuestion();
        
        assertEquals(null, question);
    }
    

}
