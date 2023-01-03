package com.sanjay.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.sanjay.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{

	Team findByTeamName(String teamName);
}