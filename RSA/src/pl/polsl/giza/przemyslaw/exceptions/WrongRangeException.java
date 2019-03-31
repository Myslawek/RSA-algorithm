/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.giza.przemyslaw.exceptions;

/**
 * Exception used when, the specified indexes are in wrong order, or do not have proper values.
 * @author Przemysław Giza
 * @version 1.1
 */
public class WrongRangeException extends Exception{
    /**
     * Default constructor which calls constructor of Exception class
     */
    public WrongRangeException(){
        super();
    }
    /**
     * Constructor which sets the exception message, to the message specified in the method parameter.
     * @param message is the message which should be set for the exception
     */
    public WrongRangeException(String message){
        super(message);
    }
    
}
