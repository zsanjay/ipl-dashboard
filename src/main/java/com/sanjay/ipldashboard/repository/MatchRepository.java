package com.sanjay.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sanjay.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long>{

	List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2 , Pageable pageable);
	
	List<Match> getByTeam1OrTeam2(String teamName1, String teamName2);
	
	@Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :dateStart and :dateEnd order by m.date desc")
	List<Match> findMatchByTeamAndYear(@Param("teamName") String teamName , @Param("dateStart") LocalDate dateStart ,@Param("dateEnd") LocalDate dateEnd);
	
	default List<Match> findLatestMatchesByTeam(String teamName , int count) {
		return getByTeam1OrTeam2OrderByDateDesc(teamName , teamName , PageRequest.of( 0 , count));
	}
	
//	default List<Match> findMatchByTeamNameAndYear(String teamName , Integer year)  {
//		
//		List<Match> matches = getByTeam1OrTeam2(teamName , teamName);
//		
//		return matches.stream().filter(match -> match.getDate().getYear() == year).collect(Collectors.toList());
//		
//	}
}
