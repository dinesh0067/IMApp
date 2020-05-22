package com.dinesh.imbackendapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinesh.imbackendapp.domain.Backlog;
import com.dinesh.imbackendapp.domain.Incident;
import com.dinesh.imbackendapp.domain.User;
import com.dinesh.imbackendapp.exceptions.IncidentIdException;
import com.dinesh.imbackendapp.exceptions.IncidentNotFoundException;
import com.dinesh.imbackendapp.repositories.BacklogRepository;
import com.dinesh.imbackendapp.repositories.IncidentRepository;
import com.dinesh.imbackendapp.repositories.UserRepository;

@Service
 public class IncidentService {
	
	@Autowired
	private IncidentRepository incidentRepository;
	
	@Autowired
    private BacklogRepository backlogRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	   public Incident saveOrUpdateIncident(Incident incident, String username){
		   
		    if(incident.getId() != null){
		    	Incident existingincident = incidentRepository.findByIncidentIdentifier(incident.getIncidentIdentifier());
	            if(existingincident !=null &&(!existingincident.getIncidentLeader().equals(username))){
	                throw new IncidentNotFoundException("Incident not found in your account");
	            }else if(existingincident == null){
	                throw new IncidentNotFoundException("Incident with ID: '"+incident.getIncidentIdentifier()+"' cannot be updated because it doesn't exist");
	            }
	        }
	        try{
	        	
	        	User user = userRepository.findByUsername(username);
	        	incident.setUser(user);
	            incident.setIncidentLeader(user.getUsername());
	        	incident.setIncidentIdentifier(incident.getIncidentIdentifier().toUpperCase());
	        	
	        	if(incident.getId()==null){
	                Backlog backlog = new Backlog();
	                incident.setBacklog(backlog);
	                backlog.setIncident(incident);
	                backlog.setIncidentIdentifier(incident.getIncidentIdentifier().toUpperCase());
	            }

	            if(incident.getId()!=null){
	            	incident.setBacklog(backlogRepository.findByIncidentIdentifier(incident.getIncidentIdentifier().toUpperCase()));
	            }	        	
	        	
	            return incidentRepository.save(incident);
	        }catch (Exception e){
	            throw new IncidentIdException("Incident ID '"+incident.getIncidentIdentifier().toUpperCase()+"' already exists");
	        }

	    }
	   
	   public Incident findIncidentByIdentifier(String incidentId, String username) {
		   
	        //Only want to return the Incident if the user looking for it is the owner

		   Incident incident = incidentRepository.findByIncidentIdentifier(incidentId.toUpperCase());

	        if(incident == null){
	            throw new IncidentIdException("Incident ID '"+incidentId+"' does not exist");

	        }

	        if(!incident.getIncidentLeader().equals(username)){
	            throw new IncidentNotFoundException("Incident not found in your account");
	        }
		   return incident;
		   
	   }
	   
	   public Iterable<Incident> findAllIncidents(String username){
	        return incidentRepository.findAllByIncidentLeader(username);
	    }
	   
	   public void deleteIncidentByIdentifier(String incidentId,String username) {
		   incidentRepository.delete(findIncidentByIdentifier(incidentId, username));
	   }

}
