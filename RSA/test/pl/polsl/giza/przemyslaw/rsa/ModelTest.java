/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.giza.przemyslaw.rsa;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;
import pl.polsl.giza.przemyslaw.exceptions.ModulusExceedException;
import pl.polsl.giza.przemyslaw.exceptions.WrongRangeException;
import pl.polsl.giza.przemyslaw.rsa.markers.*;

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

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    /**
     * Tests if the object of Model class is initialized without any errors, with
     * proper range of primes defined.
     */
    @Category(NormalTests.class)
    @Test
    public void testModelProperRange(){
        try{
            instance = new Model(3,15);
        }catch(WrongRangeException e){
            fail("The object was not initialized!");
        }
    }
    
    /**
     * Test of the constructor with specifying prime numbers range, of class Model.
     * Check whether the exception is thrown for the incorrect initialization
     * of the object. If it is not, then the test is failed.
     */
    @Category(AbnormalTests.class)
    @Test
    public void testModelReverseRange(){
        try{
            instance = new Model(3123,-50);
            fail("Wrong object initialization was not recognized!");
        }catch(WrongRangeException e){
            //this point should be reached, if the exception will be thorwn correctly.
        }
    }
    /**
     * Tests if the object of model class can be initialized, with prime number
     * values ranged, set to the same number on both sides. If not, then the
     * test is passed.
     */
    @Category(BorderTests.class)
    @Test
    public void testModelSameBorders(){
        try{
            instance = new Model(12,12);
            fail("The model constructor did not throw any error, for indentical borders");
        }catch(Exception e){
            //should reach this point
        }
    }
    
    /**
     * Test of encryptChain method, of class Model.
     * Checks if the sentence if properly decrypted with the default 
     * values of keys and modulus.
     */
    @Category(NormalTests.class)
    @Test
    public void testEncryptChain_String() throws Exception {
        //System.out.println("encryptChain with default key");
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
    @Category(NormalTests.class)
    @Test
    public void testEncryptChainProper_3args() throws Exception {
        ////System.out.println("encryptChain with specified key");
        String sentence = "5 48 7 19";
        int publicKey = 5;
        long gvnModulus = 38243;
        instance = new Model();
        String expResult = "3125 29102 16807 28547";
        String result = instance.encryptChain(sentence, publicKey, gvnModulus);
        assertEquals(expResult, result);
    }
    /**
     *  Tests if the value, which is exactly the same, as the modulus, can be 
     *  properly encrypted.
     */
    @Category(BorderTests.class)
    @Test
    public void testEncryptChainEqualModulus_3args() throws Exception {
        String sentence = "5 48 7 19";
        int publicKey = 5;
        long gvnModulus = 48;
        instance = new Model();
        try{
            String result = instance.encryptChain(sentence, publicKey, gvnModulus);
            fail("Value is not lesser, than the modulus, but the exception was not thrown!");
        }catch(ModulusExceedException e){
            //this point should be reachead
        }
    }
    
    /**
     *  Tests if the exception will be raised for the value of modulus lesser,
     * than the value located inside the chain
     */
    @Category(AbnormalTests.class)
    @Test
    public void testEncryptChainLesserModulus_3args() throws Exception {
        String sentence = "5 48 7 19";
        int publicKey = 5;
        long gvnModulus = 48;
        instance = new Model();
        try{
            String result = instance.encryptChain(sentence, publicKey, gvnModulus);
            fail("Modulus is lesser than the highest value in the chain, but the exception was not raised!");
        }catch(ModulusExceedException e){
            //this point should be reachead
        }
    }
    
    /**
     * Test of decryptChain method, of class Model.
     * Checks if the specified chain of values can be
     * properly decrypted.
     */
    @Category(NormalTests.class)
    @Test
    public void testDecryptChainProper_String() throws Exception {
        //System.out.println("decryptChain with default key");
        String sentence = "23923";
        instance = new Model();
        instance.redefineModel(40637, 5, 68303);
        String expResult = "1198";
        String result = instance.decryptChain(sentence);
        assertEquals(expResult, result);
    }
    /**
     * Checks if the exception will be thrown for decryptChain method,
     * if the modulus value is exactly the same as the chain value.
     */
    @Category(BorderTests.class)
    @Test
    public void testDecryptChainEqualModulus_String() throws Exception {
        ////System.out.println("decryptChain with default key");
        String sentence = "23923";
        instance = new Model();
        instance.redefineModel(40637, 5, 23923);
        try{
            String result = instance.decryptChain(sentence);
            fail("The exception was not thrown for value of chain equal to the modulus!");
        }catch(ModulusExceedException e){
            //this point should not be reached
        }
    }
    
    /**
     * Checks if the value of chain bigger than modulus will cause exception.
     */
    @Category(AbnormalTests.class)
    @Test
    public void testDecryptChainLesserModulus_String() throws Exception {
        String sentence = "23923";
        instance = new Model();
        instance.redefineModel(40637, 5, 200);
        try{
            String result = instance.decryptChain(sentence);
            fail("The exception was not thrown for value of chain bigger than the value of modulus!");
        }catch(ModulusExceedException e){
            //this point should not be reached
        }
    }
    
    /**
     * Test of decryptChain method, of class Model.
     * Checking if the given chain of values, separated with spaces,
     * can be properly decrypted, with use of specified key.
     */
    @Category(NormalTests.class)
    @Test
    public void testDecryptChain_3args() throws Exception {
        //System.out.println("decryptChain with specified key");
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
    @Category(NormalTests.class)
    @Test
    public void testEncryptSentenceProperModulus() throws Exception {
        //System.out.println("encryptSentence with default key");
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
    @Category(NormalTests.class)
    @Test
    public void testDecryptSentenceProperModulus_String() throws Exception {
        //System.out.println("decryptSentence");
        String sentence = "16901 54844 16901 66317 52312 16901 66317 15276 41552 59116 16901";
        instance = new Model();
        instance.redefineModel(76877, 5, 97067);
        String expResult = "ala ma kota";
        String result = instance.decryptSentence(sentence);
        assertEquals(expResult, result);
    }

    /**
     * Checks if the exception will be thrown for the value of modulus
     * equal to max value of chain
     */
    @Category(BorderTests.class)
    @Test
    public void testDecryptSentenceEqualModulus_String() throws Exception {
        ////System.out.println("decryptSentence");
        String sentence = "16901 54844 16901 66317 52312 16901 66317 15276 41552 59116 16901";
        instance = new Model();
        instance.redefineModel(76877, 5, 66317);
        String expResult = "ala ma kota";
        try{
            String result = instance.decryptSentence(sentence);
            fail("Exception was not thrown for the value of modulus equal, to the max value of chain");
        }catch(ModulusExceedException e){
            
        }
    }
    
    /**
     * Checks if the exception will be thrown for the modulus value lesser
     * than max value of the chain
     */
    @Category(AbnormalTests.class)
    @Test
    public void testDecryptSentenceLesserModulus_String() throws Exception {
        String sentence = "16901 54844 16901 66317 52312 16901 66317 15276 41552 59116 16901";
        instance = new Model();
        instance.redefineModel(76877, 5, 5000);
        String expResult = "ala ma kota";
        try{
            String result = instance.decryptSentence(sentence);
            fail("Exception was not thrown for the value of modulus lesser, than the max value of chain");
        }catch(ModulusExceedException e){
            
        }
    }
    
    /**
     * Test of decryptSentence method, of class Model.
     * Testing if the function decrypts properly given chain of values
     * into its decrypted representation.
     */
    @Category(NormalTests.class)
    @Test
    public void testDecryptSentenceProperModulus_3args() throws Exception {
        //System.out.println("decryptSentence");
        String sentence = "16901 54844 16901 66317 52312 16901 66317 15276 41552 59116 16901";
        int key = 76877;
        long gvnModulus = 97067;
        instance = new Model();
        String expResult = "ala ma kota";
        String result = instance.decryptSentence(sentence, key, gvnModulus);
        assertEquals(expResult, result);
    }
    
    /**
     * Checks if the exception will be thrown for the value of modulus
     * equal to the max value of the chain.
     */
    @Category(BorderTests.class)
    @Test
    public void testDecryptSentenceEqualModulus_3args() throws Exception {
        //System.out.println("decryptSentence");
        String sentence = "16901 54844 16901 66317 52312 16901 66317 15276 41552 59116 16901";
        int key = 76877;
        long gvnModulus = 66317;
        instance = new Model();
        String expResult = "ala ma kota";
        try{
            String result = instance.decryptSentence(sentence, key, gvnModulus);
            fail("Exception was not thrown for modulus equal to max value of chain");
        }catch(ModulusExceedException e){
            
        }
    }
    
    /**
     * Checks if the exception will be thrown for the value of modulus
     * lesser than the max value of the chain.
     */
    @Category(AbnormalTests.class)
    @Test
    public void testDecryptSentenceLesserModulus_3args() throws Exception {
        //System.out.println("decryptSentence");
        String sentence = "16901 54844 16901 66317 52312 16901 66317 15276 41552 59116 16901";
        int key = 76877;
        long gvnModulus = -13000;
        instance = new Model();
        String expResult = "ala ma kota";
        try{
            String result = instance.decryptSentence(sentence, key, gvnModulus);
            fail("Exception was not thrown for modulus lesser than max value of chain");
        }catch(ModulusExceedException e){
            
        }
    }

    /**
     * Test of returnPublicKey method, of class Model.
     * Tests if the returned value of the public key is really
     * currently set one.
     */
    @Category(NormalTests.class)
    @Test
    public void testReturnPublicKey() {
        //System.out.println("returnPublicKey");
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
    @Category(NormalTests.class)
    @Test
    public void testReturnPrivateKey() {
        //System.out.println("returnPrivateKey");
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
    @Category(NormalTests.class)
    @Test
    public void testReturnModulus() {
        //System.out.println("returnModulus");
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
    @Category(NormalTests.class)
    @Test
    public void testRedefineModel() {
        //System.out.println("redefineModel");
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
