/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.giza.przemyslaw.rsa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class used for testing the public methods of the 
 * @author Przemysław Giza
 * @version 1.2
 * @since 1.2
 */
public class ModelTest {
    
    public ModelTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of encryptChain method, of class Model.
     */
    @Test
    public void EncryptChain_Stringtest() throws Exception {
        System.out.println("encryptChain");
        String sentence = "1198";
        Model instance = new Model();
        instance.redefineModel(40637, 5, 68303);
        String expResult = "23923";
        String result = instance.encryptChain(sentence);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encryptChain method, of class Model.
     */
    @Test
    public void testEncryptChain_3args() throws Exception {
        System.out.println("encryptChain");
        String sentence = "";
        int key = 0;
        long gvnModulus = 0L;
        Model instance = new Model();
        String expResult = "";
        String result = instance.encryptChain(sentence, key, gvnModulus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decryptChain method, of class Model.
     */
    @Test
    public void testDecryptChain_String() throws Exception {
        System.out.println("decryptChain");
        String sentence = "";
        Model instance = new Model();
        String expResult = "";
        String result = instance.decryptChain(sentence);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decryptChain method, of class Model.
     */
    @Test
    public void testDecryptChain_3args() throws Exception {
        System.out.println("decryptChain");
        String sentence = "";
        int key = 0;
        long gvnModulus = 0L;
        Model instance = new Model();
        String expResult = "";
        String result = instance.decryptChain(sentence, key, gvnModulus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encryptSentence method, of class Model.
     */
    @Test
    public void testEncryptSentence() throws Exception {
        System.out.println("encryptSentence");
        String sentence = "";
        Model instance = new Model();
        String expResult = "";
        String result = instance.encryptSentence(sentence);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decryptSentence method, of class Model.
     */
    @Test
    public void testDecryptSentence_String() throws Exception {
        System.out.println("decryptSentence");
        String sentence = "";
        Model instance = new Model();
        String expResult = "";
        String result = instance.decryptSentence(sentence);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decryptSentence method, of class Model.
     */
    @Test
    public void testDecryptSentence_3args() throws Exception {
        System.out.println("decryptSentence");
        String sentence = "";
        int key = 0;
        long gvnModulus = 0L;
        Model instance = new Model();
        String expResult = "";
        String result = instance.decryptSentence(sentence, key, gvnModulus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnPublicKey method, of class Model.
     */
    @Test
    public void testReturnPublicKey() {
        System.out.println("returnPublicKey");
        Model instance = new Model();
        int expResult = 0;
        int result = instance.returnPublicKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnPrivateKey method, of class Model.
     */
    @Test
    public void testReturnPrivateKey() {
        System.out.println("returnPrivateKey");
        Model instance = new Model();
        int expResult = 0;
        int result = instance.returnPrivateKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnModulus method, of class Model.
     */
    @Test
    public void testReturnModulus() {
        System.out.println("returnModulus");
        Model instance = new Model();
        long expResult = 0L;
        long result = instance.returnModulus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of redefineModel method, of class Model.
     */
    @Test
    public void testRedefineModel() {
        System.out.println("redefineModel");
        int privateKey = 0;
        int publicKey = 0;
        long modulus = 0L;
        Model instance = new Model();
        instance.redefineModel(privateKey, publicKey, modulus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
