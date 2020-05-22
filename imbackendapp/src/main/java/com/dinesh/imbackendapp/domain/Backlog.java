package com.dinesh.imbackendapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ITSequence = 0;
    private String incidentIdentifier;

  //OneToOne with incident
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="incident_id",nullable = false)
    @JsonIgnore
    private Incident incident;

    //OneToMany incidenttasks
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "backlog", orphanRemoval = true)
    private List<IncidentTask> incidentTasks = new ArrayList<>();


    public Backlog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Integer getITSequence() {
		return ITSequence;
	}

	public void setITSequence(Integer iTSequence) {
		ITSequence = iTSequence;
	}

	public String getIncidentIdentifier() {
		return incidentIdentifier;
	}

	public void setIncidentIdentifier(String incidentIdentifier) {
		this.incidentIdentifier = incidentIdentifier;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public List<IncidentTask> getIncidentTasks() {
		return incidentTasks;
	}

	public void setIncidentTasks(List<IncidentTask> incidentTasks) {
		this.incidentTasks = incidentTasks;
	}
	
	
	
	
}