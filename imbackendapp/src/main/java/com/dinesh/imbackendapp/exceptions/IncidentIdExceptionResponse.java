package com.dinesh.imbackendapp.exceptions;

public class IncidentIdExceptionResponse {
	
	private String incidentIdentifier;
	
	public IncidentIdExceptionResponse(String incidentIdentifier) {
		this.incidentIdentifier = incidentIdentifier;
	}

	public String getIncidentIdentifier() {
		return incidentIdentifier;
	}

	public void setIncidentIdentifier(String incidentIdentifier) {
		this.incidentIdentifier = incidentIdentifier;
	}
	
	

}
