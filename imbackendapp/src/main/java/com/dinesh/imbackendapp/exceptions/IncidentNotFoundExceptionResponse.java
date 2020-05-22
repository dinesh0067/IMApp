package com.dinesh.imbackendapp.exceptions;

public class IncidentNotFoundExceptionResponse {

	private String IncidentNotFound;
	public IncidentNotFoundExceptionResponse(String incidentNotFound) {
		IncidentNotFound = incidentNotFound;
		
		
    }
	public String getIncidentNotFound() {
		return IncidentNotFound;
	}
	public void setIncidentNotFound(String incidentNotFound) {
		IncidentNotFound = incidentNotFound;
	}

}
