package com.pelletier.valuelist.exception;

public class AdapterNotFoundException extends Exception {
	public AdapterNotFoundException(String adapterKey){
		super("Adapter could not be found for " + adapterKey + ", please check Spring configuration.");
	}
}
