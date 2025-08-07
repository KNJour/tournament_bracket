package com.keith.tournamentbracket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keith.tournamentbracket.models.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

}
