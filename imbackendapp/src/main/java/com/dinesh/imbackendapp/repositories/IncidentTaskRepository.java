package com.dinesh.imbackendapp.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dinesh.imbackendapp.domain.IncidentTask;

@Repository
public interface IncidentTaskRepository extends CrudRepository<IncidentTask, Long> {
	List<IncidentTask> findByIncidentIdentifierOrderByPriority(String id);
	IncidentTask findByIncidentSequence(String sequence);
}