package com.dinesh.imbackendapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dinesh.imbackendapp.domain.Backlog;
import com.dinesh.imbackendapp.domain.Incident;
import com.dinesh.imbackendapp.domain.IncidentTask;
import com.dinesh.imbackendapp.exceptions.IncidentNotFoundException;
import com.dinesh.imbackendapp.repositories.BacklogRepository;
import com.dinesh.imbackendapp.repositories.IncidentRepository;
import com.dinesh.imbackendapp.repositories.IncidentTaskRepository;

@Service
public class IncidentTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private IncidentTaskRepository incidentTaskRepository;
    
    @Autowired
    private IncidentRepository incidentRepository;
    
    @Autowired
    private IncidentService incidentService;


    public IncidentTask addIncidentTask(String incidentIdentifier, IncidentTask incidentTask, String username){


        //ITs to be added to a specific incident, incident != null, BL exists
    	Backlog backlog =  incidentService.findIncidentByIdentifier(incidentIdentifier, username).getBacklog(); //backlogRepository.findByProjectIdentifier(projectIdentifier);
        //set the bl to it
        incidentTask.setBacklog(backlog);
        //we want our incident sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
        Integer BacklogSequence = backlog.getITSequence();
        // Update the BL SEQUENCE
        BacklogSequence++;
        backlog.setITSequence(BacklogSequence);

        //Add Sequence to Incident Task
        incidentTask.setIncidentSequence(incidentIdentifier+"-"+BacklogSequence);
        incidentTask.setIncidentIdentifier(incidentIdentifier);

        //INITIAL priority when priority null
        
        //INITIAL status when status is null
        if(incidentTask.getStatus()==""|| incidentTask.getStatus()==null){
        	incidentTask.setStatus("TO_DO");
        }
        
        if(incidentTask.getPriority()==null || incidentTask.getPriority()==0){ //In the future we need incidentTask.getPriority()== 0 to handle the form
            incidentTask.setPriority(3);
        }

        return incidentTaskRepository.save(incidentTask);
  }
    
    public Iterable<IncidentTask>findBacklogById(String id,String username){
    	incidentService.findIncidentByIdentifier(id, username);
        return incidentTaskRepository.findByIncidentIdentifierOrderByPriority(id);
    }
    

    public IncidentTask findITByIncidentSequence(String backlog_id, String it_id,String username){

    	  //make sure we are searching on an existing backlog
    	incidentService.findIncidentByIdentifier(backlog_id, username);

        //make sure that our task exists
        IncidentTask incidentTask = incidentTaskRepository.findByIncidentSequence(it_id);

        if(incidentTask == null){
            throw new IncidentNotFoundException("Incident Task '"+it_id+"' not found");
        }

        //make sure that the backlog/incident id in the path corresponds to the right incident
        if(!incidentTask.getIncidentIdentifier().equals(backlog_id)){
            throw new IncidentNotFoundException("Incident Task '"+it_id+"' does not exist in incident: '"+backlog_id);
        }
        return incidentTask;
        }
    
    public IncidentTask updateByIncidentSequence(IncidentTask updatedTask, String backlog_id, String it_id,String username){
    	IncidentTask incidentTask = findITByIncidentSequence(backlog_id, it_id, username);

    	incidentTask = updatedTask;

        return incidentTaskRepository.save(incidentTask);
    }
    
    public void deleteITByIncidentSequence(String backlog_id, String it_id,String username){
        IncidentTask incidentTask = findITByIncidentSequence(backlog_id, it_id,username);

		/*
		 * Backlog backlog = incidentTask.getBacklog(); List<IncidentTask> its =
		 * backlog.getIncidentTasks(); its.remove(incidentTask);
		 * backlogRepository.save(backlog);
		 */

        incidentTaskRepository.delete(incidentTask);
    }
    //Update incident tapt
   

    //find existing incident task

    //replace it with updated task

    //save update
   
}