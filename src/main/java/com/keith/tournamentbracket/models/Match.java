package com.keith.tournamentbracket.models;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;


//what connects players and a tournament

@Entity
@Table(name = "matches")
public class Match {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int round;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "tournament_id")
	    private Tournament tournament;
	
	
	
	 @ManyToMany
	 @JoinTable(
	        name= "match_teams",
	        joinColumns=@JoinColumn(name = "match_id"),
	        inverseJoinColumns=@JoinColumn(name = "team_id")
	    )
	    private Set<Team> teams = new HashSet<>();

	
	
	//constructors
	
	public Match() {}
	
	public Match(Set<Team> teams, int round, Tournament tournament) {
        this.teams = teams;
        this.round = round;
        this.tournament = tournament;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

}
