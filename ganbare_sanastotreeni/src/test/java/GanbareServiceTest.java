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

/**
 *
 * @author palolaur
 */
public class GanbareServiceTest {
    
    GanbareService service;
   
    
    @Before
    public void setUp() {
        service = new GanbareService();
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void prepareLexiconFormsAnArrayWithItems(){
         service.prepareLexicon("lexicon.txt");
         
         boolean array = service.getLexicon().isEmpty();
         
         assertFalse(array);
         
     }
     
     @Test
     public void prepareLexiconThrowsErrorIfUnreadableFile(){
         boolean output = service.prepareLexicon("invalidFile");
         
         assertEquals(false, output);
         
     }
     
     @Test
     public void newSessionCanBeCreated(){
         service.prepareLexicon("lexicon.txt");
         service.newSession("suomi", true, true, true, 5);
         
         
         String session = service.getSession().toString();
         
         assertEquals("Sessiomäärittelyt: \nKyselykieli: suomi\nVerbit: true\nAdjektiivit: true\nSubstantiivit: true\nSession pituus: 5", session);
         
     }
     
}
