package se.atrosys.steenfast.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	@OneToMany(targetEntity=Player.class, mappedBy="id", fetch= FetchType.EAGER)
	private List<Player> players;

	@OneToMany(targetEntity=Round.class, mappedBy="id", fetch= FetchType.EAGER)
	private List<Round> rounds;
	private String name;

	@Tolerate
	public Tournament() {}
}
