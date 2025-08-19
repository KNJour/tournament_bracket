package com.keith.tournamentbracket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.keith.tournamentbracket.models.Tournament;
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
    
   @PostMapping("/createtournament")
   public String createTournament(@RequestParam String name, @RequestParam int teamSize, @RequestParam int matchSize) {
	   tournamentService.create(name, teamSize, matchSize);
//	   return "redirect:/tournaments/" + newTournament.getId();
	   return "redirect:/";
   }
    
   @GetMapping("/tournament/view/{id}")
   public String showTournament(@PathVariable("id") Long id, Model model, @RequestParam(value="msg", required=false) String msg) {
	   Tournament tournament = tournamentService.findById(id);
	   if (tournament == null) {
		   return "redirect:/?msg=Tournament+not+found";
	   }
	   
	   model.addAttribute("tournament", tournament);
	   model.addAttribute("registered", tournament.getPlayers());
	   model.addAttribute("unassigned", playerService.unassignedPlayers(id));
	   model.addAttribute("msg", msg);
	   
	   return "showtournament.jsp";
   }
//Delete a tournament
   @PostMapping("/tournament/delete/{id}")
   public String deleteTournament(@PathVariable("id") Long id) {
	   tournamentService.delete(id);
	   return "redirect:/";
   }
//    
//    New Player
    @GetMapping("/newplayer")
    public String newPlayer(Model model) {
    	model.addAttribute("name", "");
    	return "newplayer.jsp";
    }
    
    @PostMapping("/createplayer")
    public String createPlayer(@RequestParam String name, Model model) {
    	
    	//of in invalid name comes through, it re renders the form with the previous name used
    	if (name == null || name.trim().isEmpty()) {
    		model.addAttribute("error", "Player name is required.");
            model.addAttribute("name", name); 
            return "newplayer.jsp";
    	}
    	playerService.create(name);
        
        return "redirect:/";	
    }
    
  //Delete a tournament
    @PostMapping("/player/delete/{id}")
    public String deletePlayer(@PathVariable("id") Long id) {
 	   playerService.delete(id);
 	   return "redirect:/";
    }
}
