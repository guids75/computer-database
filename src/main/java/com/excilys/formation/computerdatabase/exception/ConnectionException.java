package com.excilys.formation.computerdatabase.exception;

public class ConnectionException extends Exception {

	public ConnectionException(String message){
		super("Connection problem " + message);
	}
	
}
