package com.ecc.hibernate_xml.util.app;

public class ExceptionHandler {
	public static String printException(Exception cause) {
		cause.printStackTrace();
		if (cause.getCause() != null) {
			System.out.println("Caused by: ");
			cause.getCause().printStackTrace();
		}
		return "Error: " + cause.getMessage();
	}
}