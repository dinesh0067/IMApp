package com.dinesh.imbackendapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dinesh.imbackendapp.domain.Incident;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, Long> {

	Incident findByIncidentIdentifier(String incidentId);
	
	
	@Override
	Iterable<Incident> findAll();
	
	Iterable<Incident> findAllByIncidentLeader(String username);
	
	
	
	

}
