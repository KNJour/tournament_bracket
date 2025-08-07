package com.keith.tournamentbracket.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keith.tournamentbracket.models.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{
	
	List<Team> findByTournamentId(Long tournamentId);
}
