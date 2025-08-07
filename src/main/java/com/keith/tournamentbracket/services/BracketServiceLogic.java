package com.keith.tournamentbracket.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keith.tournamentbracket.models.*;
import com.keith.tournamentbracket.repositories.*;
import com.keith.tournamentbracket.seeding.BracketSeeder;

import jakarta.transaction.Transactional;

import com.keith.tournamentbracket.exceptions.NotFoundException;

@Service
@Transactional
public class BracketServiceLogic  implements BracketService{
	
	    private final TournamentRepository tRepo;
	    private final PlayerRepository pRepo;
	    private final TeamRepository teamRepo;
	    private final MatchRepository mRepo;
	    private final BracketSeeder seeder;
	    
	    @Autowired
	    public BracketServiceLogic(TournamentRepository t, PlayerRepository p, TeamRepository team, MatchRepository m, BracketSeeder seeder) {
	    	this.tRepo = t;
	    	this.pRepo = p;
	    	this.teamRepo = team;
	    	this.mRepo = m;
	    	this.seeder = seeder;
	    }
	    //listall the teams--------------
	    @Override
	    @Transactional
	    public List<Team> showTeams(Long tid) {
	    	return teamRepo.findByTournamentId(tid);
	    }
//list matches _____--------------------
	    @Override
	    @Transactional
	    public List<Match> showMatches(Long tid) {
	        return mRepo.findByTournamentId(tid);
	    }
	    @Override
	    public Team createTeam(Long tid, List<Long> playerIds) {
	    	
	    	//finds tourney and gets team size
	    Tournament tourney = tRepo.findById(tid).orElse(null);
	    if (tourney == null) {
	    	throw new IllegalArgumentException("Tournament not found/ no matching id");
	    }
	    int teamSize = tourney.getTeamSize();
	    
	    if (playerIds.size() != teamSize) {
	        throw new IllegalArgumentException(
	            "Must have teams of " + teamSize + " players.");
	    }
	    
	    //All players
	    List<Player> players = pRepo.findAllById(playerIds);
	    if (players.size() != playerIds.size()) {
	        throw new IllegalArgumentException("ERROR________ some player IDs invalid");
	    }
	    
	    //All Matches
	    
	    
	    //for loop for registered check and on team check
	    for (Player player : players) {

	        if (!player.getTournaments().contains(tourney)) {
	            throw new IllegalStateException(player.getName() +" is not registered for this tournament.");
	        }
	        
	        //on a team already check
	        boolean placed = false;
	        for (Team team : player.getTeams()) {
	        		if (team.getTournament().getId().equals(tid)) {
	        			placed = true;
	        			break;
	        		}
	        }
	        if (placed) {
	        	throw new IllegalStateException(player.getName() + "is already on another team");
	        }
	        
	    }
	    //end loop
	    //team numbers
	     int teamNum = 1;
	     for (Team existing : tourney.getTeams()) {
	    	 if (existing.getTeamNumber() >= teamNum) {
	    		 teamNum = existing.getTeamNumber() + 1;
	    	 }
	     }
	    
	     Team team = new Team(teamNum, tourney);
	     for (Player player : players) {
	    	 team.getPlayers().add(player);
	    	
	     }
	     return teamRepo.save(team);
	    
	    }
	    //Seeding the bracket depending which mode
	    @Override
	    public void seed(Long tid, SeedMode mode) {

	        Tournament t = tRepo.findById(tid).orElse(null);
	        if (t == null) {
	            throw new IllegalArgumentException("Tournament not found");
	        }

	        if (mode == SeedMode.RANDOM) {
	            seeder.seedRandomPlayers(t);
	        } else if (mode == SeedMode.MANUAL) {
	            seeder.seedManualTeams(t);
	        } else {
	            throw new IllegalArgumentException("Unknown seed mode");
	        }
	    }
}
