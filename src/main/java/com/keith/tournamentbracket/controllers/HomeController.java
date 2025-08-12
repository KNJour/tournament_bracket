package com.keith.tournamentbracket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.keith.tournamentbracket.services.PlayerService;
import com.keith.tournamentbracket.services.TournamentService;
@Controller
public class HomeController {

	private final TournamentService tournamentService;
    private final PlayerService playerService;

    @Autowired
    public HomeController(TournamentService tournamentService, PlayerService playerService) {
        this.tournamentService = tournamentService;
        this.playerService = playerService;
    }
    
    @GetMapping("/")
    public String dashboard(Model model) {
    	model.addAttribute("tournaments", tournamentService.listAll());
    	model.addAttribute("players", playerService.listAll());
    	return "dashboard.jsp";
    }
    
    
    //New Tournament
    @GetMapping("/newtournament")
    public String newTournament() {
    	return "newtournament.jsp";
    }
    
   
    
    
//    
//    New Player
    @GetMapping("/newplayer")
    public String newPlayer() {
    	return "newplayer.jsp";
    }
}
