package com.dinesh.imbackendapp.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.imbackendapp.domain.Incident;
import com.dinesh.imbackendapp.services.IncidentService;
import com.dinesh.imbackendapp.services.MapValidationErrorService;

@RestController
@RequestMapping("api/incident")
@CrossOrigin
public class IncidentController {
	
	@Autowired
	private IncidentService incidentService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	
	
	@PostMapping("") 
	public ResponseEntity<?> createNewIncident(@Valid @RequestBody Incident incident, BindingResult result,Principal principal){
		
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap != null) return errorMap;
		
		Incident incident1 = incidentService.saveOrUpdateIncident(incident, principal.getName());
		return new ResponseEntity<Incident>(incident1, HttpStatus.CREATED);	
	}
	
	@GetMapping("/{incidentId}")
	public ResponseEntity<?> getIncidentById(@PathVariable String incidentId,Principal principal){
		
		Incident incident = incidentService.findIncidentByIdentifier(incidentId,principal.getName());
		
		return new ResponseEntity<Incident>(incident, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Incident> getAllIncidents(Principal principal){return incidentService.findAllIncidents(principal.getName());}

	@DeleteMapping("/{incidentId}")
	public ResponseEntity<?> deleteIncident(@PathVariable String incidentId,Principal principal){
		incidentService.deleteIncidentByIdentifier(incidentId,principal.getName());;
		
		return new ResponseEntity<String>("Incident with ID: '"+incidentId+"' was deleted", HttpStatus.OK);
	}
}
