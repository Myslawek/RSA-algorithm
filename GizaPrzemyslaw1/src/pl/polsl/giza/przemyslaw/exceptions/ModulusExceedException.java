/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.giza.przemyslaw.exceptions;

/**
 *
 * @author Przemysław
 */
public class ModulusExceedException extends Exception{

    public ModulusExceedException() {
        super("The value to be encrypted/decrypted exceeds the modulus value, thus the result will not be valid.");
    }
    public ModulusExceedException(String message){
        super(message);
    }
}
