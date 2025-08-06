package com.keith.tournamentbracket.seeding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.keith.tournamentbracket.models.Match;
import com.keith.tournamentbracket.models.Player;
import com.keith.tournamentbracket.models.Team;
import com.keith.tournamentbracket.models.Tournament;

import repositories.MatchRepository;
import repositories.TeamRepository;

@Component
public class BracketSeeder {

    private final TeamRepository  teamRepo;
    private final MatchRepository matchRepo;

    @Autowired
    public BracketSeeder(TeamRepository teamRepo, MatchRepository matchRepo) {
        this.teamRepo  = teamRepo;
        this.matchRepo = matchRepo;
    }

//random
    public void seedRandomPlayers(Tournament tournament) {
        deleteExistingTeamsAndMatches(tournament);
        List<Player> shuffled = new ArrayList<>(tournament.getPlayers());
        Collections.shuffle(shuffled);
        List<Team> teams = buildTeamsFromPlayers(shuffled, tournament);
        buildMatchesFromTeams(teams, tournament);
    }

    //manually seed
    public void seedManualTeams(Tournament tournament) {
    	//checks if empty
        if (tournament.getTeams().isEmpty())           
            throw new IllegalStateException("manual wasnt edefined");

        deleteExistingMatches(tournament);              
        buildMatchesFromTeams(new ArrayList<>(tournament.getTeams()), tournament);
    }


//team builder
    private List<Team> buildTeamsFromPlayers(List<Player> players, Tournament t) {
        int teamSize = t.getTeamSize();
        List<Team> teams = new ArrayList<>();
        int counter = 1;

        for (int i = 0; i < players.size(); i += teamSize) {
            List<Player> slice = players.subList(i, Math.min(i + teamSize, players.size()));
            Team team = new Team(counter++, t);
            slice.forEach(team.getPlayers()::add);
            teamRepo.save(team);
            teams.add(team);
        }
        return teams;
    }

    private void buildMatchesFromTeams(List<Team> teams, Tournament t) {
        int matchSize = t.getMatchSize();
        for (int i = 0; i < teams.size(); i += matchSize) {
            Set<Team> slice =
                    new HashSet<>(teams.subList(i, Math.min(i + matchSize, teams.size())));
            matchRepo.save(new Match(slice, 1, t));
        }
    }

    private void deleteExistingTeamsAndMatches(Tournament t) {
        deleteExistingMatches(t);
        if (!t.getTeams().isEmpty()) {
            teamRepo.deleteAll(t.getTeams());
            t.getTeams().clear();
        }
    }
    private void deleteExistingMatches(Tournament t) {
        List<Match> old = matchRepo.findByTournamentId(t.getId());
        matchRepo.deleteAll(old);
    }
}

