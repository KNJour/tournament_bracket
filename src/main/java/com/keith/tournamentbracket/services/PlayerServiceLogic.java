package com.keith.tournamentbracket.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.keith.tournamentbracket.models.Player;
import com.keith.tournamentbracket.models.Team;
import com.keith.tournamentbracket.models.Tournament;
import com.keith.tournamentbracket.repositories.PlayerRepository;

@Service
@Transactional
public class PlayerServiceLogic implements PlayerService {

	private final PlayerRepository playerRepository;
	
	@Autowired
	public PlayerServiceLogic(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	
	}
	
	@Override
	public Player create(String name) {
		return playerRepository.save(new Player(name));
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Player> listAll() {
		return playerRepository.findAll(Sort.by("name"));
	}
	
	//Listing unnasigned players
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Player> unassignedPlayers(Long tournamentId) {
		
		List<Player> result = new ArrayList<>();
		List<Player> allPlayers = playerRepository.findAll();
		
		///for loop start
		for (Player player : allPlayers) {
		boolean in = false;
		//for loop check if in tournament
			for (Tournament tourney : player.getTournaments()) {
				if (tourney.getId().equals(tournamentId)) {
					in = true;
					break;
				}
			}
			if(!in) {
				continue;
			}
		
			boolean placed = false;
		//for loop check if placed on team
			for (Team team : player.getTeams()) {
				if (team.getTournament().getId().equals(tournamentId)) {
					placed = true;
					break;
				}
			}
			if (placed) {
				continue;
			}
			result.add(player);
	}
		return result;
		
		

	}
}
