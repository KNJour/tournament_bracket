package com.keith.tournamentbracket.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keith.tournamentbracket.exceptions.NotFoundException;
import com.keith.tournamentbracket.models.Player;
import com.keith.tournamentbracket.models.Tournament;
import com.keith.tournamentbracket.repositories.PlayerRepository;
import com.keith.tournamentbracket.repositories.TournamentRepository;
import com.keith.tournamentbracket.seeding.BracketSeeder;

public class TournamentServiceLogic implements TournamentService{
	private final TournamentRepository tournamentRepo;
    private final PlayerRepository     playerRepo;
    private final BracketSeeder        seeder;
}
