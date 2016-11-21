package com.excilys.formation.computerdatabase.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.ui.Cli;

public class ConnectionException extends RuntimeException {

  private static final Logger slf4jLogger = LoggerFactory.getLogger(ConnectionException.class);
  
  /** 
   * Cr�e une nouvelle instance de ConnectionException 
   */  
  public ConnectionException() {
  }  

  public ConnectionException(String message) {
    super("Connection problem : " + message);
    slf4jLogger.error("Connection problem : " + message);
  }

  /** 
   * Cr�e une nouvelle instance de ConnectionException 
   * @param cause L'exception � l'origine de cette exception 
   */  
  public ConnectionException(Throwable cause) {  
    super(cause); 
    slf4jLogger.error("Connection problem");
  }  
  /** 
   * Cr�e une nouvelle instance de ConnectionException 
   * @param message Le message d�taillant exception 
   * @param cause L'exception � l'origine de cette exception 
   */  
  public ConnectionException(String message, Throwable cause) {  
    super("Connection problem : " + message, cause);
    slf4jLogger.error("Connection problem : " + message);
  } 

}