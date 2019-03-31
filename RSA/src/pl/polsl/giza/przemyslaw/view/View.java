/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.giza.przemyslaw.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Class responsible for getting the user input, together with the displaying the program
 * information and outputs, to the default output stream.
 * @author Przemysław Giza
 * @version 1.1
 */
public class View {
    private final String[] UI_MENU_CONTENT={    //array of strings that contains whole menu text
        "Available options:",
        "1. Encrypt chain with default key",
        "2. Decrypt chain with default key",
        "3. Encrypt sentence with default key",
        "4. Decrypt sentence with default key",
        "5. Show modulus",
        "6. Show public key",
        "7. Show private key",
        "8. Specify model",
        "Any other option means EXIT"
    };
    private final List<String> UI_HELP_CONTENT;  //Linked list, which will hold Strings representing help for the user.
    /**
     * Creating the object, to be able to read the input, from the console (default user input source)
     */
    private final Scanner USER_INPUT = new Scanner(System.in);
    /**
     * Dummy constructor which shows the use of AT LEAST ONE (List, which is not prohibited array) generic collection, by feeding the help the collection.
     */
    public View(){
        UI_HELP_CONTENT=new LinkedList();
        UI_HELP_CONTENT.add("Help for the program has been invoked.");
        UI_HELP_CONTENT.add("Avaiable switches:");
        UI_HELP_CONTENT.add("[-ec chain] Encrypts the chain of numbers separated with spaces");
        UI_HELP_CONTENT.add("[-dc chain privateKey modulus] Decrypts the chain of numbers separated with spaces");
        UI_HELP_CONTENT.add("[-es sentence] Encrypts the sentence (for example in natural language)");
        UI_HELP_CONTENT.add("[-ds chain privateKey modulus] Decrypts the chain of numbers and presents it in form of natural language");
        UI_HELP_CONTENT.add("\nExamplary switch combination and usage:");
        UI_HELP_CONTENT.add("-ec \"12 5 8\"");
        UI_HELP_CONTENT.add("-es \"Ala ma kota\"");
        UI_HELP_CONTENT.add("-dc \"128 3783 518\" 103 143");
    }
    /**
     * Read single line from console and return it as string
     * @return function returns one sentence entered by the user.
     */
    public String readUserLine(){
        return USER_INPUT.nextLine();
    }
    /**
     * Read single int from console and return it as int.
     * @return integer specified by the user
     */
    public int readUserInt(){
        return USER_INPUT.nextInt();
    }
    /**
     * Reads single user long from console
     * @return user specified long
     */
    public long readUserLong(){
        return USER_INPUT.nextLong();
    }
    /**
     * Used to read remaining content of the buffer until new line (enter) is reached
     * @throws Error during flushing input
     */
    public void flushScanner(){
        try{
            USER_INPUT.nextLine();
        }catch(Exception e){
            System.out.println("Flushing error!");
        }
    }
    /**
     * Prints given string and feeds new line
     * @param gvnString 
     */
    public void println(String gvnString){
        System.out.println(gvnString);
    }
    /**
     * Prints given string
     * @param gvnString 
     */
    public void print(String gvnString){
        System.out.print(gvnString);
    }
    /**
     * Prints the menu on the default output, with use of the for-each loop
     */
    public void printMenu(){
        for(String line : UI_MENU_CONTENT)
            System.out.println(line);
    }
    /**
     * Prints the help to the default output, with use of functional operation for-each
     */
    public void printHelp(){
        UI_HELP_CONTENT.forEach((line) -> {
            System.out.println(line);
        });
    }
    
    public void printModulusExceed(){
        
    }
}
