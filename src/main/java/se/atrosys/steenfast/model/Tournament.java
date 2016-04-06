package se.atrosys.steenfast.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.SortedSet;

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
