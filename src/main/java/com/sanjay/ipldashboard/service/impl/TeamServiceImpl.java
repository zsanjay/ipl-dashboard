package com.sanjay.ipldashboard.service.impl;

import org.springframework.stereotype.Service;

import com.sanjay.ipldashboard.model.Team;
import com.sanjay.ipldashboard.repository.MatchRepository;
import com.sanjay.ipldashboard.repository.TeamRepository;
import com.sanjay.ipldashboard.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	
	private TeamRepository teamRepository;
	private MatchRepository matchRepository;
	
	public TeamServiceImpl(TeamRepository teamRepository , MatchRepository matchRepository) {
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}

	@Override
	public Team getTeam(String teamName) {
		
		Team team = teamRepository.findByTeamName(teamName);
		team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));
		
		return team;
	}

}
