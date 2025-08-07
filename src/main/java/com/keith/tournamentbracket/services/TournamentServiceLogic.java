package com.keith.tournamentbracket.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.keith.tournamentbracket.models.SeedMode;
import com.keith.tournamentbracket.exceptions.NotFoundException;
import com.keith.tournamentbracket.models.Player;
import com.keith.tournamentbracket.models.Tournament;
import com.keith.tournamentbracket.repositories.PlayerRepository;
import com.keith.tournamentbracket.repositories.TournamentRepository;
import com.keith.tournamentbracket.seeding.BracketSeeder;

@Service
@Transactional
public class TournamentServiceLogic implements TournamentService{
	private final TournamentRepository tournamentRepository;
	private final PlayerRepository playerRepository;
	private final BracketSeeder seeder;
	
	
	@Autowired
	public TournamentServiceLogic(TournamentRepository tournamentRepo, PlayerRepository playerRepo, BracketSeeder seeder) {
			this.tournamentRepository = tournamentRepo;
			this.playerRepository = playerRepo;
			this.seeder = seeder;
		}
	
	//create
	@Override
    public Tournament create(String name, int teamSize, int matchSize) {
		
        Tournament tournament = new Tournament(name, teamSize, matchSize);
        
        return tournamentRepository.save(tournament);
    }
	
	//Read
	@Override
    @Transactional(readOnly = true)
    public List<Tournament> listAll() {
		
        return tournamentRepository.findAll(Sort.by("createdAt").descending());
    }
	
	@Override
	public Tournament addPlayers(Long tournamentId, List<Long> ids) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(()-> new NotFoundException("Tournament not found"));
		
		List<Player> players = playerRepository.findAllById(ids);
		if (players.size() != ids.size()) {
			throw new NotFoundException("One or more player IDs invalid");
		}
		
		//for every person in players it adds them to the tournament
		for (Player person : players) {
		    tournament.addPlayer(person);      
		}
		return tournament;
	}
	
	 @Override
	    public void seedBracket(Long tid, SeedMode mode) {

	        Tournament tournament = tournamentRepository.findById(tid).orElseThrow(() -> new NotFoundException("Tournament"));

	        switch (mode) {
	            case RANDOM -> seeder.seedRandomPlayers(tournament);
	            case MANUAL -> seeder.seedManualTeams(tournament);
	            default -> throw new IllegalArgumentException("Unknown seed mode");
	        }
	    }
	
}
