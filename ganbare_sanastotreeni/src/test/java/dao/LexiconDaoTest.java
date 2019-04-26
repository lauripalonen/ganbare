package dao;

import ganbare.dao.LexiconDao;
import ganbare.domain.SqlParameters;
import ganbare.domain.Word;
import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LexiconDaoTest {

    LexiconDao ld;
    SqlParameters sqlParams;

    public LexiconDaoTest() {
        
        
    }

    @Before
    public void setUp() {
        ld = new LexiconDao("jdbc:h2:./lexiconTest");

        sqlParams = new SqlParameters("suomi", true, true, true, true, 5);

    }

    @After
    public void tearDown() {
        try {
            ld.formatTestLexicon();
        } catch (Exception e){
            System.out.println("Virhe: " + e);
        }
        
    }

    @Test
    public void createLexiconReturnsUnemptyArraylist() {
        ArrayList<Word> words = new ArrayList<>();
        try {
            words = ld.createLexicon(sqlParams);
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }
        
        assertTrue(!words.isEmpty());

    }
    
    @Test
    public void createLexiconReturnsEmptyArrayListIfNoClassesSelected(){
        SqlParameters newSqlParams = new SqlParameters("suomi", false, false, false, false, 10);
        ArrayList<Word> words = new ArrayList<>();
        
        
        try {
            words = ld.createLexicon(newSqlParams);
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }
        
        assertTrue(words.isEmpty());
        
        
    }
    
    @Test
    public void formatTestLexiconRemovesAllTwentyThreeWords(){
        
        try {
            ld.formatTestLexicon();
        } catch (Exception e){
            System.out.println("Virhe: " + e);
        }
        
        int total = 0;
        
        try {
            total = ld.getTotalCount();
        } catch (Exception e){
            System.out.println("Virhe: " + e);
        }
        
        assertEquals(23, total);
        
        
        
    }
    
    @Test
    public void getTotalCountReturnsTotalNumberOfWords(){
        
        int count = 0;
        
        try {
            count = ld.getTotalCount();
        } catch (Exception e){
            System.out.println("Virhe: " + e);
        }
        
        assertEquals(23, count);
    }
    
    @Test
    public void getCountReturnCorrectNumberOfWords(){
        
        int count = 0;
        
        try {
            count = ld.getCount(1);
            
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }
        
        assertEquals(10, count);
    }
    
    @Test
    public void addWordGrowsLexiconSize(){
        int count = 0;
        
        try {
            ld.addWord("testi", "テスト", "tesuto", 1, 0);
            count = ld.getTotalCount();
        } catch (Exception e){
            System.out.println("Virhe: " + e);
        }
        
        assertEquals(24, count);
        
    }

}
