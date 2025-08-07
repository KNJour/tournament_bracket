package com.keith.tournamentbracket.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keith.tournamentbracket.models.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
	List<Match> findByTournamentId(Long tournamentId);
}
