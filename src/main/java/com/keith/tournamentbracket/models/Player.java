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
	
	//Many to many inverse part
	
	@ManyToMany(mappedBy="players", fetch=FetchType.LAZY)
	private Set<Tournament> tournaments = new HashSet<>();
	
	//Constuctor Section
	
	public Player() {}
	public Player(String name) {
		this.name = name;
	}
	
	//get and set uhs
	//Getters and setters
	
		public Long getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Set<Tournament> getTournaments(){
			return tournaments;
			}
		
		//helping keep in sync
		
		public void addTournament(Tournament t) {
		    tournaments.add(t);
		    t.getPlayers().add(this);
		}

		public void removeTournament(Tournament t) {
		    tournaments.remove(t);
		    t.getPlayers().remove(this);
		}
}

