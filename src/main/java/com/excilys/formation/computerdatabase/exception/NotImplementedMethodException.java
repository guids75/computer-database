package com.excilys.formation.computerdatabase.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.ui.Cli;

public class NotImplementedMethodException extends Exception {

  private static final Logger slf4jLogger = LoggerFactory.getLogger(NotImplementedMethodException.class);
  
  /** 
   * Crée une nouvelle instance de ConnectionException 
   */  
  public NotImplementedMethodException() {
  }  

  public NotImplementedMethodException(String message) {
    super("Method not implemented : " + message);
    slf4jLogger.error("Method not implemented : " + message);
  }

  /** 
   * Crée une nouvelle instance de ConnectionException 
   * @param cause L'exception à l'origine de cette exception 
   */  
  public NotImplementedMethodException(Throwable cause) {  
    super(cause); 
    slf4jLogger.error("Method not implemented");
  }  
  /** 
   * Crée une nouvelle instance de ConnectionException 
   * @param message Le message détaillant exception 
   * @param cause L'exception à l'origine de cette exception 
   */  
  public NotImplementedMethodException(String message, Throwable cause) {  
    super("Method not implemented : " + message, cause); 
    slf4jLogger.error("Method not implemented : " + message);
  } 

}