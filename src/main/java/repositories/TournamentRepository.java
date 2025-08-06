package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keith.tournamentbracket.models.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

	List<Tournament> findAll();
}
