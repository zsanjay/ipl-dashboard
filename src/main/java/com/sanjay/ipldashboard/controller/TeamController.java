package com.sanjay.ipldashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sanjay.ipldashboard.model.Team;
import com.sanjay.ipldashboard.service.TeamService;

@RestController
public class TeamController {
	
	private TeamService teamService;
	
	@Autowired
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		
		return teamService.getTeam(teamName);
	}
}
