/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ganbare_trainer.domain.GanbareService;

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
         service.prepareLexicon();
         
         boolean array = service.getLexicon().isEmpty();
         
         assertFalse(array);
         
     }
}
