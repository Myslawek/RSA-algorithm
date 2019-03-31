/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.giza.przemyslaw.exceptions;

/**
 * Exception used when, the specified indexes are in wrong order, or do not have proper values.
 * @author Przemysław Giza
 */
public class WrongRangeException extends Exception{
    public WrongRangeException(){
        super();
    }
    public WrongRangeException(String message){
        super(message);
    }
    
}
