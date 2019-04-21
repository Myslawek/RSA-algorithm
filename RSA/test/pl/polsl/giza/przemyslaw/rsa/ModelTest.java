/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.giza.przemyslaw.rsa;

import org.junit.*;
import static org.junit.Assert.*;
import pl.polsl.giza.przemyslaw.exceptions.WrongRangeException;

/**
 * Class used for testing the public methods of the 
 * @author Przemysław Giza
 * @version 1.2
 * @since 1.2
 */
public class ModelTest {
    /**
     * Empty (not initialized) instance of the Model class object. It is used
     * to initialize the object for performing tests.
     */
    Model instance;
    /**
     * Might be used someday.
     */
    public ModelTest() {
    }
    
    /**
     * Test of the constructor with specifying prime numbers range, of class Model.
     * Check whether the exception is thrown for the incorrect initialization
     * of the object. If it is not, then the test is failed.
     */
    @Test
    public void testModel(){
        try{
            instance = new Model(3123,-50);
            fail("Wrong object initialization was not recognized!");
        }catch(WrongRangeException e){
            //this point should be reached, if the exception will be thorwn correctly.
        }
    }
    
    
    /**
     * Test of encryptChain method, of class Model.
     * Checks if the sentence if properly decrypted with the default 
     * values of keys and modulus.
     */
    @Test
    public void testEncryptChain_String() throws Exception {
        System.out.println("encryptChain with default key");
        String sentence = "1198";
        instance = new Model();
        instance.redefineModel(40637, 5, 68303);
        String expResult = "23923";
        String result = instance.encryptChain(sentence);
        assertEquals(expResult, result);
    }

    /**
     * Test of encryptChain method, of class Model. 
     * It tests whether the specified string, which is
     * chain of number separated with spaces, is properly
     * encrypted.
     */
    @Test
    public void testEncryptChain_3args() throws Exception {
        System.out.println("encryptChain with specified key");
        String sentence = "5 48 7 19";
        int publicKey = 5;
        long gvnModulus = 38243;
        instance = new Model();
        String expResult = "3125 29102 16807 28547";
        String result = instance.encryptChain(sentence, publicKey, gvnModulus);
        assertEquals(expResult, result);
    }

    /**
     * Test of decryptChain method, of class Model.
     * Checks if the specified chain of values can be
     * properly decrypted.
     */
    @Test
    public void testDecryptChain_String() throws Exception {
        System.out.println("decryptChain with default key");
        String sentence = "23923";
        instance = new Model();
        instance.redefineModel(40637, 5, 68303);
        String expResult = "1198";
        String result = instance.decryptChain(sentence);
        assertEquals(expResult, result);
    }

    /**
     * Test of decryptChain method, of class Model.
     * Checking if the given chain of values, separated with spaces,
     * can be properly decrypted, with use of specified key.
     */
    @Test
    public void testDecryptChain_3args() throws Exception {
        System.out.println("decryptChain with specified key");
        String sentence = "3125 29102 16807 28547";
        int key = 22709;
        long gvnModulus = 38243;
        instance = new Model();
        String expResult = "5 48 7 19";
        String result = instance.decryptChain(sentence, key, gvnModulus);
        assertEquals(expResult, result);
    }

    /**
     * Test of encryptSentence method, of class Model.
     * Testing if the specified string is properly encoded
     * into its values, separated with spaces, representation.
     */
    @Test
    public void testEncryptSentence() throws Exception {
        System.out.println("encryptSentence with default key");
        String sentence = "ala ma kota";
        instance = new Model();
        String expResult = "16901 54844 16901 66317 52312 16901 66317 15276 41552 59116 16901";
        instance.redefineModel(76877, 5, 97067);
        String result = instance.encryptSentence(sentence);
        assertEquals(expResult, result);
    }

    /**
     * Test of decryptSentence method, of class Model.
     * Checks if the chain of the values separated with spaces
     * is properly decrypted.
     */
    @Test
    public void testDecryptSentence_String() throws Exception {
        System.out.println("decryptSentence");
        String sentence = "16901 54844 16901 66317 52312 16901 66317 15276 41552 59116 16901";
        instance = new Model();
        instance.redefineModel(76877, 5, 97067);
        String expResult = "ala ma kota";
        String result = instance.decryptSentence(sentence);
        assertEquals(expResult, result);
    }

    /**
     * Test of decryptSentence method, of class Model.
     * Testing if the function decrypts properly given chain of values
     * into its decrypted representation.
     */
    @Test
    public void testDecryptSentence_3args() throws Exception {
        System.out.println("decryptSentence");
        String sentence = "16901 54844 16901 66317 52312 16901 66317 15276 41552 59116 16901";
        int key = 76877;
        long gvnModulus = 97067;
        instance = new Model();
        String expResult = "ala ma kota";
        String result = instance.decryptSentence(sentence, key, gvnModulus);
        assertEquals(expResult, result);
    }

    /**
     * Test of returnPublicKey method, of class Model.
     * Tests if the returned value of the public key is really
     * currently set one.
     */
    @Test
    public void testReturnPublicKey() {
        System.out.println("returnPublicKey");
        instance = new Model();
        instance.redefineModel(10, 5, 12);
        int expResult = 5;
        int result = instance.returnPublicKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of returnPrivateKey method, of class Model.
     * Tests if the returned value is exactly the same as
     * the private key inside the model.
     */
    @Test
    public void testReturnPrivateKey() {
        System.out.println("returnPrivateKey");
        instance = new Model();
        instance.redefineModel(17, 321312, 1312321);
        int expResult = 17;
        int result = instance.returnPrivateKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of returnModulus method, of class Model.
     * Checking if the returned values is the same as the
     * value of modulus specified inside the object model.
     */
    @Test
    public void testReturnModulus() {
        System.out.println("returnModulus");
        instance = new Model();
        instance.redefineModel(10, 10, 34334);
        long expResult = 34334;
        long result = instance.returnModulus();
        assertEquals(expResult, result);
    }

    /**
     * Test of redefineModel method, of class Model.
     * This test actually can not be performed without getters.
     * So in general it checks if the values set for the model, meet
     * targeted values.
     */
    @Test
    public void testRedefineModel() {
        System.out.println("redefineModel");
        int privateKey = 1503;
        int publicKey = 93128;
        long modulus = 49853;
        instance = new Model();
        instance.redefineModel(privateKey, publicKey, modulus);
        if(instance.returnModulus()!=modulus)
            fail("Modulus value is not the same as desired!");
        if(instance.returnPrivateKey()!=privateKey)
            fail("private key value is not the same as desired!");
        if(instance.returnPublicKey()!=publicKey)
            fail("public key value is not the same as desired!");
    }
    
}
