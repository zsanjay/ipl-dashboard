package com.sanjay.ipldashboard.service;

import com.sanjay.ipldashboard.model.Team;

public interface TeamService {

	Team getTeam(String teamName);

	Iterable<Team> getTeams();

}
