package com.sanjay.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanjay.ipldashboard.model.Match;
import com.sanjay.ipldashboard.model.Team;
import com.sanjay.ipldashboard.repository.MatchRepository;
import com.sanjay.ipldashboard.service.TeamService;

@RestController
@CrossOrigin
public class TeamController {
	
	private TeamService teamService;
	private MatchRepository matchRepository;
	
	@Autowired
	public TeamController(TeamService teamService, MatchRepository matchRepository) {
		this.teamService = teamService;
		this.matchRepository = matchRepository;
	}
	
	@GetMapping("/team")
	public Iterable<Team> getTeams() {
		return teamService.getTeams();
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		
		return teamService.getTeam(teamName);
	}
	
	@GetMapping("/team/{teamName}/matches")
	public List<Match> getTeam(@PathVariable String teamName , @RequestParam("year") Integer year) {
		
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year + 1, 1, 1);
		
		return matchRepository.findMatchByTeamAndYear(teamName , startDate , endDate);
	}
}
