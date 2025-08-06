package com.keith.tournamentbracket.services;

import java.util.List;

import com.keith.tournamentbracket.models.SeedMode;
import com.keith.tournamentbracket.models.Tournament;

public interface TournamentService {
	List<Tournament> listAll();
	
    Tournament addTournament(String name);
    Tournament addPlayers(Long tournamentId, List<Long> playerIds);
    void seedBracket(Long tournamentId, SeedMode mode);  
}
