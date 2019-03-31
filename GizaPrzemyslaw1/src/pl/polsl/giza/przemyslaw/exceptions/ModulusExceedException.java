/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.giza.przemyslaw.exceptions;

/**
 * Exception which is thrown, when the value to be transformed by the RSA algorithm exceeds the set value of the modulus.
 * @author Przemysław Giza
 */
public class ModulusExceedException extends Exception{
    /**
     * Constructor which sets the default message for the given exception and delegates it to the class from which it inherited
     */
    public ModulusExceedException() {
        super("The value to be encrypted/decrypted exceeds the modulus value, thus the result will not be valid.");
    }
    /**
     * Sets the message of the exception, as it is specified by the parameter and delegates it.
     * @param message the message to be set as the exception message
     */
    public ModulusExceedException(String message){
        super(message);
    }
}
