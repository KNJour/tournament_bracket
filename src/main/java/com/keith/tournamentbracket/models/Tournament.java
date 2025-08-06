package com.keith.tournamentbracket.models;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tournaments")
public class Tournament {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	@NotEmpty(message="tournament name is required!")
    @Size(min=2, max=50, message="Username must be between 2 and 50 characters")
    private String name;
	
	private int teamSize = 1;
	private int matchSize = 2;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
	@PrePersist
	protected void onCreate(){
	    this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}
	@ManyToMany(mappedBy = "tournaments")
    private Set<Player> players = new HashSet<>();
	
	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Team> teams = new HashSet<>();
	
//	Constructors Section
	public Tournament() {}
	
	public Tournament(String name, int teamSize, int matchSize) {
		this.name = name;
		this.teamSize = teamSize;
		this.matchSize = matchSize;
	}
	

	//keeping both sides consistant
	
	public void addPlayer(Player p) {
	    players.add(p);               
	    p.getTournaments().add(this);   
	}

	public void removePlayer(Player p) {
	    players.remove(p);
	    p.getTournaments().remove(this);
	}
	
	//getters and setters 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	public int getMatchSize() {
		return matchSize;
	}
	public void setMatchSize(int matchSize) {
		this.matchSize = matchSize;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Set<Player> getPlayers() {
		return players;
	}
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	public Set<Team> getTeams() {
		return teams;
	}
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	
	
}
