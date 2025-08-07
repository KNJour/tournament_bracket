package com.keith.tournamentbracket.services;

import java.util.List;

import com.keith.tournamentbracket.models.SeedMode;
import com.keith.tournamentbracket.models.Tournament;

public interface TournamentService {
    Tournament create(String name, int teamSize, int matchSize);
    
    //all tournaments newest first
    List<Tournament> listAll();
    //registering players
    Tournament addPlayers(Long tournamentId, List<Long> playerIds);
    
    
//    Build round-1 matches using seedmode algo
    void seedBracket(Long tournamentId, SeedMode mode);
}
