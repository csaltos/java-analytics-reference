package com.example.ejemplodedb;

public class LogicaException extends Exception {

	private static final long serialVersionUID = 7951034044444507672L;

	private int codigoInterno;

	public LogicaException(int codigoInterno, String message) {
		super(message + " [" + codigoInterno + "]");
		this.codigoInterno = codigoInterno;
	}

	public LogicaException(int codigoInterno, String message, Throwable cause) {
		super(message + " [" + codigoInterno + "]", cause);
		this.codigoInterno = codigoInterno;
	}

	public LogicaException(int codigoInterno, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message + " [" + codigoInterno + "]", cause, enableSuppression, writableStackTrace);
		this.codigoInterno = codigoInterno;
	}
	
	public int getCodigoInterno() {
		return codigoInterno;
	}

}
