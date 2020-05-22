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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinesh.imbackendapp.domain.IncidentTask;
import com.dinesh.imbackendapp.services.IncidentTaskService;
import com.dinesh.imbackendapp.services.MapValidationErrorService;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private IncidentTaskService incidentTaskService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addITtoBacklog(@Valid @RequestBody IncidentTask incidentTask,
                                            BindingResult result, @PathVariable String backlog_id, Principal principal){

        ResponseEntity<?> erroMap = mapValidationErrorService.MapValidationService(result);
        if (erroMap != null) return erroMap;

        IncidentTask incidentTask1 = incidentTaskService.addIncidentTask(backlog_id, incidentTask,principal.getName());

        return new ResponseEntity<IncidentTask>(incidentTask1, HttpStatus.CREATED);

    }
    
    @GetMapping("/{backlog_id}")
    public Iterable<IncidentTask> getIncidentBacklog(@PathVariable String backlog_id,Principal principal){

        return incidentTaskService.findBacklogById(backlog_id,principal.getName());

    }
    
    @GetMapping("/{backlog_id}/{it_id}")
    public ResponseEntity<?> getIncidentTask(@PathVariable String backlog_id, @PathVariable String it_id,Principal principal){
    	IncidentTask incidentTask = incidentTaskService.findITByIncidentSequence(backlog_id, it_id,principal.getName());
        return new ResponseEntity<IncidentTask>( incidentTask, HttpStatus.OK);
    }
    
    @PatchMapping("/{backlog_id}/{it_id}")
    public ResponseEntity<?> updateIncidentTask(@Valid @RequestBody IncidentTask incidentTask, BindingResult result,
                                               @PathVariable String backlog_id, @PathVariable String it_id,Principal principal ){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        IncidentTask updatedTask = incidentTaskService.updateByIncidentSequence(incidentTask,backlog_id,it_id,principal.getName());

        return new ResponseEntity<IncidentTask>(updatedTask,HttpStatus.OK);

    }
    
    @DeleteMapping("/{backlog_id}/{it_id}")
    public ResponseEntity<?> deleteIncidentTask(@PathVariable String backlog_id, @PathVariable String it_id,Principal principal){
    	incidentTaskService.deleteITByIncidentSequence(backlog_id, it_id,principal.getName());

        return new ResponseEntity<String>("Incident Task "+it_id+" was deleted successfully", HttpStatus.OK);
    }


}
