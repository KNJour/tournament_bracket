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
@Table(name="players")
public class Player {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="a name is required")
	private String name;
	
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
	

	@ManyToMany(mappedBy ="players")
    private Set<Team> teams = new HashSet<>();

	
	
	
    @ManyToMany
    @JoinTable(
        name = "player_tournaments",
        joinColumns = @JoinColumn(name = "player_id"),
        inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private Set<Tournament> tournaments = new HashSet<>();
	
	//Constuctor Section
	
	public Player() {}
	public Player(String name) {
		this.name = name;
	}
	
		
		//keeping in sync
		
		public void addTournament(Tournament t) {
		    tournaments.add(t);
		    t.getPlayers().add(this);
		}

		public void removeTournament(Tournament t) {
		    tournaments.remove(t);
		    t.getPlayers().remove(this);
		}
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
		public Set<Team> getTeams() {
			return teams;
		}
		public void setTeams(Set<Team> teams) {
			this.teams = teams;
		}
		public Set<Tournament> getTournaments() {
			return tournaments;
		}
		public void setTournaments(Set<Tournament> tournaments) {
			this.tournaments = tournaments;
		}
		
		
}

