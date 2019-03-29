/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganbare_trainer.ui;
import ganbare_trainer.domain.User;
import ganbare_trainer.domain.GanbareService;
/**
 *
 * @author palolaur
 */
import java.util.*;

public class TextUi {
    private Scanner scanner;
    private GanbareService service;
    
    public TextUi(Scanner scanner, GanbareService service){
        this.scanner = scanner;
        this.service = service;
        
    }
    
    public void start(){
        
        
        System.out.println("Hei! Syötä käyttäjätietosi: ");
        System.out.println("Käyttäjätunnus: ");
        String username = scanner.nextLine();
        System.out.println("Salasana: ");
        String password = scanner.nextLine();
        
        System.out.println("Kirjaudu [k] vai rekisteröidy[r]?");
        String input = scanner.nextLine();
        
        if(input.equals("k")){
            login(username, password);
        } else if (input.equals("r")){
            register(username, password);
        } else {
            System.out.println("Epäkelpo syöte");
        }
        
    }
    
    public void login(String username, String password){
        //handle credential check from database
        User user = new User(username);
        service.addUser(user);
        
        
        options();
        
    }
    
    public void register(String username, String password){
        //handle adding new user to the database
        User user = new User(username);
        service.addUser(user);
        
        options();
        
    }
    
    public void options(){
        System.out.println("");
        System.out.println("*****ガンバレ!!!******");
        System.out.println("ようこそ　" + service.getUser() + "-さん！");
        
        
        System.out.println("1. Kysytäänkö japaniksi [j] vai suomeksi [s]?");
        String quizLanguage = scanner.nextLine();
        System.out.println("2. Mitä sanaluokkia kysytään?");
        String[] wordClass = new String[5];
        
        System.out.println("  2.1: Verbit [y/n]:");
        wordClass[0] = scanner.nextLine();
        System.out.println("  2.2: Adjektiivit [y/n]:");
        wordClass[1] = scanner.nextLine();
        System.out.println("  2.3: Substantiivit [y/n]:");
        wordClass[2] = scanner.nextLine();
        
        System.out.println("Valitse session pituus [5-20]: ");
        int sessionLength = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Valitsit session pituudeksi: " + sessionLength);
        
        //Confirm that the session configuration is valid.
        //
        //Load correct session package from database and
        //save the package to GanbareService.
        service.newSession(sessionLength);
        
        beginSession();
        
        
    }
    
    public void beginSession(){
        ArrayList<String> sessionLexicon = service.getSession();
       
        int sessionLength = sessionLexicon.size();
        int questionNum = 0;
        
        int correctAnswers = 0;
       
        
        while(true){
            if (questionNum > sessionLength-1){
                break;
                
            }
            String[] word = sessionLexicon.get(questionNum).split(":");
            String question = word[0];
            String answer = word[1].toLowerCase();
            
            System.out.println("Kysymys " + (questionNum+1) + "/" + sessionLength + ": ");
            System.out.println(question);
            
            System.out.println("Vastaus: ");
            String userInput = scanner.nextLine().toLowerCase();
            System.out.println("");
            
            if (userInput.equals(answer)){
                System.out.println("Oikea vastaus!");
                correctAnswers++;
            } else {
                System.out.println("Oikea vastaus oli: " + answer);
                System.out.println("Vastasit: " + userInput);
            }
            
            System.out.println("");
            
            questionNum++;
            
            
        }
        
        review(correctAnswers, sessionLength);
        
        
        
    }
    
    public void review(int correctAnswers, int sessionLength){
        System.out.println("Sessio päättyi!");
        System.out.println("Oikeita vastauksia: " + correctAnswers);
        System.out.println("Kysymyksiä yhteensä: " + sessionLength);
        System.out.println("");
        System.out.println("Siirrytäänkö alkuvalikkoon [a] vai suljetaanko sovellus[x]?");
        String userInput = scanner.nextLine();
        
        if(userInput.equals("a")){
            options();
        } else if (userInput.equals("x")){
            System.out.println("Kiitos pelaamisesta!");
        } else {
            System.out.println("Epäkelpo syöte");
        }
    }
    
    
}
