package pl.polsl.giza.przemyslaw.rsa;

import java.math.BigInteger;
import java.util.Random;

/**
 * The RSACypher implements the RSA encryption algorithm for a user input.
 * @author Przemysław Giza
 * @version 1.0
 */
public class Model {
    private final int UPPER_BOUND;  //holds the value of the max prime number to be generated
    private final int LOWER_BOUND;  //hold the value of min prime number to be generated
    private final Random RAND = new Random();   //used to create random values
    private final int FIRST_PRIME;  //first prime number p
    private final int SECOND_PRIME;  //second prime number q
    private long modulus;  //product of p and q
    private final int TOTIENT; //euler function value (q-1)(p-1)
    private int privateKey; //holds the value of private key of the RSA algorithm
    private int publicKey;  //hold the value of public key of the RSA algorithm
    
    /**
     * This constructor sets the boundaries for the prime numbers generation as for range from 100 to 1000
     */
    public Model(){
        this(100,1000);
    }
     /**
      * In this constructor the user specifies the range of the prime numbers within range of: [lowerBound, upperBound]
      * @param lowerBound lower bound for prime numbers searching
      * @param upperBound upper bound for prime numbers searching
      */
    public Model(int lowerBound, int upperBound){
        if(lowerBound>upperBound){  //PODMIENIC NA THROWA I ZROBIC EXCEPTION
            int temp;
            temp=lowerBound;
            lowerBound=upperBound;
            upperBound=temp;
        }   //exceptions will be added in future builds (hope so). FOR same bounds
        LOWER_BOUND=lowerBound;
        UPPER_BOUND=upperBound;
        FIRST_PRIME=generatePrimeNumber();
        SECOND_PRIME=generatePrimeNumber();
        modulus = FIRST_PRIME*SECOND_PRIME;
        TOTIENT=(FIRST_PRIME-1)*(SECOND_PRIME-1);
        publicKey=generateEncryptionKey();
        privateKey=generateDecryptionKey();
    }
    
    /**
     * Checks if the gvnNumber is the prime one.
     * @param gvnNumber Number to check, if it is prime
     * @return True, if the number is prime. False if the number is not prime
     */
    private boolean primalityTest(int gvnNumber){
        for(int i=2;i<=Math.sqrt(gvnNumber);++i){
            if(gvnNumber%i==0)
                return false;
        }
        return true;
    }
    /**
     * Generating the prime numbers from range between LOWER_BOUND and UPPER_BOUND
     * @return number which is prime
     */
    private int generatePrimeNumber(){
        int primeNumber;
        while(true){
        primeNumber=RAND.nextInt(UPPER_BOUND-LOWER_BOUND+1)+LOWER_BOUND;
        if(primalityTest(primeNumber)==true)
            return primeNumber;
        }
    }
    /**
     * Finding greatest common divider (GCD) by the Euclid's method
     * @param a first given number
     * @param b second given number
     * @return greatest common divider for first (a) and second (b) number
     */
    private long findGCD(long a, long b){
        long temp;
        while(b != 0){
            temp=a;
            a=b;
            b=temp%b;
        }
        return a;
    }
    
    /**
     * Finds the number for which the  Carmichael's TOTIENT function has GCD equal to 1
     * @return The public key for the RSA encryption
     */
    private int generateEncryptionKey(){    //public key
         for(int i=2;i<TOTIENT;++i){
            if(findGCD(TOTIENT, i)==1)
                return i;
        }
        return 0; //error
    }
    
    /**
     * Finds the value, which fulfills the equation i*publicKey%TOTIENT=1
     * @return The private key for the RSA decryption
     */
    private int generateDecryptionKey(){    //private key
        for(int i=1;;++i){
            if(i*publicKey%TOTIENT==1){
                return i;
            }
        }
    }
    
    /**
     * Function for encoding DIGITS only, separated by the spaces!
     * @param sentence given message to encode
     * @param key the key for which the encoding should happen
     * @param gvnModulus value of modulus used in encoding
     * @return sentence modulated by the key and modulus. Simply encoded/decoded message
     */
    private String cypheringAlgorithm(String sentence, int key, long gvnModulus){
        if(sentence.length()<1)
            return null;
        StringBuilder result = new StringBuilder();
        StringBuilder number = new StringBuilder();
        BigInteger encryptedValue;
        for(int i=0;i<sentence.length();++i){   //iterate over string
            if(sentence.charAt(i)==' '&&number.length()>0){    //space found, number is complete. number.length()>0 LATER
                //encrypt
                encryptedValue = new BigInteger(number.toString());
                encryptedValue = encryptedValue.pow(key).mod(BigInteger.valueOf(gvnModulus));
                
                if(result.length()>0){
                    result.append(" ");
                }
                result.append(encryptedValue);
                number.delete(0, number.length());
            }
            else{
                number.append(sentence.charAt(i));  
            }
        }
        if(number.length()>0){
            encryptedValue = new BigInteger(number.toString());
            encryptedValue = encryptedValue.pow(key).mod(BigInteger.valueOf(gvnModulus));
            if(result.length()>0){
                result.append(" ");
            }
            result.append(encryptedValue);
        }
        return result.toString();
    }
    
    /**
     * Encrypts specified by the user chain of numbers with default public key and modulus
     * @param sentence the chain to encrypt
     * @return encrypted chain
     */
    public String encryptChain(String sentence){
        return cypheringAlgorithm(sentence, publicKey, modulus);
    }
    /**
     * Encrypts specified by the user chain of numbers with given public key and modulus. The user has to ensure to give proper values for the encryption
     * @param sentence chain to encrypt
     * @param key proper public key
     * @param gvnModulus proper modulus
     * @return encrypted chain
     */
    public String encryptChain(String sentence, int key, long gvnModulus){
        return cypheringAlgorithm(sentence, key, gvnModulus);
    }
    
    /**
     * Decrypts the sentence with default private key, encrypted with the default public key.
     * @param sentence chain specified by the user
     * @return decrypted chain
     */
    public String decryptChain(String sentence){
        return cypheringAlgorithm(sentence, privateKey, modulus);
    }
    /**
     * Decrypts specified by the user chain of numbers with given private key and modulus. The user has to ensure to give proper values for the decryption
     * @param sentence chain to encrypt
     * @param key proper private key
     * @param gvnModulus proper modulus
     * @return decrypted chain
     */
    public String decryptChain(String sentence, int key, long gvnModulus){
        return cypheringAlgorithm(sentence, key, gvnModulus);
    }
    /**
     * Converts the specified by the user sentence, for example "Ala ma kota", to the
     * chain of the numbers, so that cypheringAlgorithm can be used.
     * @param sentence message specified by the user in natural language
     * @return chain of numbers, which corresponds to the sentence
     */
    private String sentenceToChain(String sentence){
        StringBuilder chain = new StringBuilder();
        for(int i=0;i<sentence.length();++i){
            if(chain.length()>0)
                chain.append(" ");
            chain.append((int)sentence.charAt(i));
        }
        return chain.toString();
    }
    /**
     * Converts the decrypted chain of the numbers to their natural language representation
     * @param chain chain of numbers representing sentence
     * @return decoded chain in form of natural language sentence
     */
    private String chainToSentence(String chain){
        StringBuilder sentence = new StringBuilder();
        StringBuilder asciiValue = new StringBuilder();
        for(int i=0;i<chain.length();++i){
            if(chain.charAt(i)!=' ') {//ignore spaces
                asciiValue.append(chain.charAt(i));
            }
            else{   //space, so complete ascii value is avaiable
                sentence.append((char)Integer.parseInt(asciiValue.toString()));
                asciiValue.delete(0, asciiValue.length());
            }   
        }
        if(asciiValue.length()>0)
            sentence.append((char)Integer.parseInt(asciiValue.toString()));
        return sentence.toString();
    }
    /**
     * Encrypts sentence into chain of numbers
     * @param sentence sentence which should be encrypted
     * @return chain of encrypted numbers representing the sentence
     */
    public String encryptSentence(String sentence){
        return cypheringAlgorithm(sentenceToChain(sentence), publicKey,modulus);
    }
    
    /**
     * Decrypts given chain, and transfers the decrypted chain values into
     * natural language message.
     * @param sentence chain of numbers, which should be decrypted
     * @return decrypted chain in form of readable message
     */
    public String decryptSentence(String sentence){
        String chain=cypheringAlgorithm(sentence, privateKey, modulus);
        return chainToSentence(chain);
    }
    /**
     * Decrypts the sentence with specified by the user private key and modulus
     * @param sentence the sentence to decrypt
     * @param key private key for which the sentence should be decrypted
     * @param gvnModulus modulus for which the sentence should be decrypted
     * @return decrypted sentence in for of natural language
     */
    public String decryptSentence(String sentence, int key, long gvnModulus){
        String chain=cypheringAlgorithm(sentence, key, gvnModulus);
        return chainToSentence(chain);
    }
    /**
     * Returns default public key
     * @return public key
     */
    public int returnPublicKey(){
        return publicKey;
    }
    
    /**
     * Return default private key
     * @return private key
     */
    public int returnPrivateKey(){
        return privateKey;
    }
    /**
     * Returns default modulus
     * @return modulus
     */
    public long returnModulus(){
        return modulus;
    }
    /**
     * Overwrites default (previously calculated) values of private key, public key and modulus, so that encryption for different system can be done.
     * @param privateKey value of private key, which should overwrite default one
     * @param publicKey value of public key, which should overwrite default one
     * @param modulus value of modulus, which should overwrite default one
     */
    public void redefineModel(int privateKey, int publicKey, long modulus){
        this.privateKey=privateKey;
        this.publicKey=publicKey;
        this.modulus=modulus;
    }
}
