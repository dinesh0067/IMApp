package com.dinesh.imbackendapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dinesh.imbackendapp.domain.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> { 
	Backlog findByIncidentIdentifier(String Identifier);
}