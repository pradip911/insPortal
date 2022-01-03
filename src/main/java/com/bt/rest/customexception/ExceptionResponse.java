package com.bt.rest.customexception;

import java.util.Date;

public class ExceptionResponse {
	  //private Date timestamp;
	  private String mensaje;
	  private String detalles;
	 // private String httpCodeMessage;

	  public ExceptionResponse(String message, String details) {
	    super();
	   /* this.timestamp = timestamp;*/
	    this.mensaje = message;
	    this.detalles = details;
	    /*this.httpCodeMessage=httpCodeMessage;*/
	  }

	 
	  public String getMensaje() {
	    return mensaje;
	  }

	  public String getDetalles() {
	    return detalles;
	  }

	}
