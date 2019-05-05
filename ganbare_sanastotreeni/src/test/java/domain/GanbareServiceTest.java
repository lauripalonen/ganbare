package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ganbare.domain.GanbareService;

public class GanbareServiceTest {

    GanbareService service;

    @Before
    public void setUp() {
        service = new GanbareService();

        this.service.newSession("suomi", true, true, true, true, 5);

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

        String initialQuestion = this.service.newQuestion();
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

        this.service.newQuestion();
        String answer = this.service.getSession().getCurrentAnswer();

        String feedback = this.service.getFeedback(answer);

        assertEquals("Oikea vastaus!", feedback);
    }

    @Test
    public void newQuestionIncreasesSessionCounter() {
        this.service.newQuestion();

        int count = this.service.getSession().getCurrentQuestionNum();

        assertEquals(1, count);
    }

    @Test
    public void nextQuestionReturnsNewQuestion() {
        String q01 = this.service.newQuestion();
        String q02 = this.service.newQuestion();

        assertFalse(q01.equals(q02));
    }

    @Test
    public void getReviewReturnsCorrectReview() {
        for (int i = 0; i < 5; i++) {
            this.service.newQuestion();

            String answer;

            if (i % 2 == 0) {
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
    public void getTotalWordsReturnsZeroIfNoClassesSelected() {
        int count = this.service.getTotalWords(false, false, false, false);

        assertEquals(0, count);
    }

    @Test
    public void getTotalWordsReturnsIntGreaterThanZeroIfAClassIsSelected() {
        int count = this.service.getTotalWords(true, false, true, false);

        assertTrue(count > 0);
    }

    @Test
    public void getLexiconDaoReturnsLexiconDao() {
        boolean notNull = this.service.getLexiconDao() != null;

        assertTrue(notNull);
    }

    @Test
    public void nextQuestionReturnsNullIfNoQuestionsLeft() {
        for (int i = 0; i < 5; i++) {
            this.service.newQuestion();
        }

        String question = this.service.newQuestion();

        assertEquals(null, question);
    }
    
    @Test
    public void addNewWordReturnsFalseIfStringParameterIsMissing(){
        
        boolean addWord = this.service.addNewWord("", "", "", "substantive", 0);
        
        assertEquals(false, addWord);
    }
    
    @Test
    public void addFinnishSynonymReturnsFalseIfStringParamterIsMissing(){
        
        boolean addSynonym = this.service.addFinnishSynonym("", "synonym");
        
        assertEquals(false, addSynonym);
    }
    
    @Test
    public void addNewUserReturnsFalseIfNameAlreadyInDatabase(){
        
        boolean newUser = this.service.newUser("testi", "123");
        
        assertEquals(false, newUser);
    }
    
    @Test
    public void loginUserReturnsFalseIfNoSuchUserExistsInDatabase(){
        
        boolean loginUser = this.service.loginUser("nonexistentuser", "password");
        
        assertEquals(false, loginUser);
    }
    

}
