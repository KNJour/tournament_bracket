package com.keith.tournamentbracket.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.keith.tournamentbracket.dto.PlayerDTO;
import com.keith.tournamentbracket.models.Player;
import com.keith.tournamentbracket.services.PlayerService;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

	private final PlayerService playerService;

	@Autowired
	public PlayerRestController(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	@PostMapping
	public Player create(@RequestBody PlayerDTO dto) {
		return playerService.create(dto.name);
	}
	
	@GetMapping
	public List<Player> List() {
		return playerService.listAll();
	}
}
