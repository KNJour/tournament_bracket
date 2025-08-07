package com.keith.tournamentbracket.services;
import java.util.List;
import com.keith.tournamentbracket.models.*;


public interface BracketService  {

	Team createTeam(Long tournamentId, List<Long> playerIds);
	
	List<Team> showTeams(Long tournamentId);
	
	
	//bracket seeding, manual or random mode
	void seed(Long tournamentId, SeedMode mode);
	
	List<Match> showMatches(Long tournamentId);
}
