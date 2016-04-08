package se.atrosys.steenfast.model;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * TODO write documentation
 */
@Entity
@Builder
@Data
public class Tournament {
	private @Id @GeneratedValue Long id;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Player> players;

	@Singular
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Round> rounds;
	private String name;

	public int getTotalScoreForPlayer(Player player) {
		return rounds.stream().mapToInt((s) -> s.getScores().get(player).getScore()).sum();
	}

	@Tolerate
	public Tournament() {}
}
