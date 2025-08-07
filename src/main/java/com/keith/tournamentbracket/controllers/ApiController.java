package com.keith.tournamentbracket.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.keith.tournamentbracket.dto.TestingDTO;
import com.keith.tournamentbracket.models.Tournament;
import com.keith.tournamentbracket.services.TournamentService;

@RestController
@RequestMapping("/api/tournaments")
public class ApiController {
	
  private final TournamentService tournamentService;
  
  @Autowired
  public ApiController(TournamentService tournamentService) {
	  this.tournamentService = tournamentService;
  }
  
  //gets the json from postman for testing
  @PostMapping
  public Tournament create(@RequestBody TestingDTO dto) {
      return tournamentService.create(dto.name, dto.teamSize, dto.matchSize);
  }
  
  @GetMapping             
  public List<Tournament> list() {
      return tournamentService.listAll();
  }
}
