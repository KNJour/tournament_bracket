package com.keith.tournamentbracket.models;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="teams")
public class Team {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
    private int teamNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
	
	@ManyToMany
	@JoinTable(
	        name               = "team_players",
	        joinColumns        = @JoinColumn(name = "team_id"),
	        inverseJoinColumns = @JoinColumn(name = "player_id")
	    )
	private Set<Player> players = new HashSet<>(); 
	
	public Team() {}    

    public Team(int teamNumber, Tournament tournament) {  
    	
        this.teamNumber = teamNumber;
        this.tournament = tournament;
        
        
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	
    
}
