package net.kzn.onlineshopping.exception;

import java.io.Serializable;

public class InvoiceNotFoundException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvoiceNotFoundException() {
		this("Invoice is not available!");
	}
	
	public InvoiceNotFoundException(String message) {
		this.message = System.currentTimeMillis() + ": " + message;
	}

	public String getMessage() {
		return message;
	}
}
