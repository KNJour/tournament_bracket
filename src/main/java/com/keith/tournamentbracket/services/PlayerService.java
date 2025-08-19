package com.keith.tournamentbracket.services;

import java.util.List;

import com.keith.tournamentbracket.models.Player;

public interface PlayerService {
List<Player> listAll();

Player create(String name);

List<Player> unassignedPlayers(Long tournamentId);

void delete(Long id);
}
