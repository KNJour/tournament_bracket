package repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.keith.tournamentbracket.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
	
	List<Player> findAll();
	
	List<Player>findByTournaments_Id(Long tournamentId);
	
	Optional<Player> findByNameIgnoreCase(String name);
}
