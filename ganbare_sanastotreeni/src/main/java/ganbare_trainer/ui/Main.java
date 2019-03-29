/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganbare_trainer.ui;
import ganbare_trainer.domain.GanbareService;

/**
 *
 * @author palolaur
 */
import java.util.*;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        
        GanbareService service = new GanbareService();
        service.prepareLexicon();
 
        TextUi textUi = new TextUi(scanner, service);
               
        
        textUi.start();
        
        
    }
    
    
    
}
