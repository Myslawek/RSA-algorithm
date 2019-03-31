package pl.polsl.giza.przemyslaw.main;
import java.util.InputMismatchException;
import pl.polsl.giza.przemyslaw.exceptions.*;
import pl.polsl.giza.przemyslaw.rsa.*;
import pl.polsl.giza.przemyslaw.view.*;
/**
 * Controller main class, which displays the user interface and operates
 * on the RSA cipher algorithm.
 * @version 1.0
 * @author Przemysław Giza
 */
public class Main {
    /**
     * This is the main function, which manages communication with user and responding in appropriate way.
     * @param args these are the arguments given by the user in the parameter mode
     */
    public static void main(String[] args){
        View view = new View();
        Model model;
        try{
        model = new Model(-3,12);
        }catch(WrongRangeException e){
            view.println("Specified range of prime numbers is not a proper one! Setting default range.");
            model=new Model();
        }
        if(args.length<1){  //UI console mode
            int userOption;
            String userLine = null;
            view.println("Weclcome to RSA cypher!");
            do{
                view.printMenu();
                view.print("Option: ");
                try{
                    userOption=view.readUserInt();
                    view.flushScanner();
                }catch(InputMismatchException e){
                    view.println("Input value was not a number!");
                    userOption=0;
                }
                if(userOption>0&&userOption<5){
                    if(userOption<3)
                        view.println("Specify the chain of numbers (separated with spaces).");
                    else
                        view.println("Specify the sentence.");
                    view.print("Input: ");
                    userLine = view.readUserLine();
                }
                try{
                    switch(userOption){
                        case 1:
                            try{
                                view.println("Ecrypted chain: "+model.encryptChain(userLine));
                            }catch(NumberFormatException e){
                                view.println("The input chain was not a sequence of numbers separated with spaces!");
                            }
                            break;
                        case 2:
                            try{
                                view.println("Decrypted chain: "+model.decryptChain(userLine));
                            }catch(NumberFormatException e){
                                view.println("The input chain was not a sequence of numbers separated with spaces!");
                            }
                            break;
                        case 3:
                            view.println("Encrypted sentence: "+model.encryptSentence(userLine));
                            break;
                        case 4:
                            try{
                                view.println("Decrypted sentence: "+model.decryptSentence(userLine));
                            }catch(NumberFormatException e){
                                view.println("The input chain was not a sequence of numbers separated with spaces!");
                            }
                            break;
                        case 5:
                            view.println("Modulus: "+model.returnModulus());
                            break;
                        case 6:
                            view.println("Public key: "+model.returnPublicKey());
                            break;
                        case 7:
                            view.println("Private key: "+model.returnPrivateKey());
                            break;
                        case 8:
                            long modulus, previousModulus=model.returnModulus();
                            int publicKey, previousPublicKey=model.returnPublicKey();
                            int privateKey, previousPrivateKey=model.returnPrivateKey();
                            try{
                            view.print("Specify modulus:");
                            modulus=view.readUserLong();
                            view.print("Specify public key:");
                            publicKey=view.readUserInt();
                            view.print("Specify private key:");
                            privateKey=view.readUserInt();
                            model.redefineModel(privateKey, publicKey, modulus);
                            }catch(InputMismatchException e){
                                view.println("One or more specified values caused exception, changes will be reverted. Provide proper values!");
                                model.redefineModel(previousPrivateKey, previousPublicKey, previousModulus);
                                view.flushScanner();
                            }
                            break;
                        default:
                            view.println("Closing program...");
                    }
                }catch(ModulusExceedException e){
                    view.println(e.getMessage());
                }
            }while(userOption>0&&userOption<9);
        }
        else{  //console arguments mode
            if(args.length==2||args.length==4){ //cyphering mode
                try{
                    switch(args[0]){
                        case "-ec":
                            try{
                                view.println("Encrypted chain: "+model.encryptChain(args[1]));  //TO DO: TRY-CATCH
                                view.println("private key: "+model.returnPrivateKey());
                                view.println("modulus: "+model.returnModulus());
                            }catch(NumberFormatException e){
                                view.println("The input chain was not a sequence of numbers separated with spaces!");
                            }
                            break;
                        case "-dc":
                            try{
                                view.println("Decrypted chain: "+model.decryptChain(args[1],Integer.parseInt(args[2]),Long.parseLong(args[3])));
                            }catch(NumberFormatException e){
                                view.println("Provided values were not proper!");
                            }
                            break;
                        case "-es":
                            view.println("Encrypted sentence: "+model.encryptSentence(args[1]));
                            view.println("private key: "+model.returnPrivateKey());
                            view.println("modulus: "+model.returnModulus());
                            break;
                        case "-ds":
                            try{
                                view.println("Decrypted sentence: "+model.decryptSentence(args[1],Integer.parseInt(args[2]),Long.parseLong(args[3])));
                            }catch(NumberFormatException e){
                                view.println("Provided values were not proper!");
                            }
                            break;
                        default:
                            view.println("Wrong usage of program! Use -h switch for help.");
                            break;
                    }
                }catch(ModulusExceedException e){
                    view.println(e.getMessage());
                }
                
            }
            else{   //help invoked
                view.printHelp();
            }
        }
    }
}
