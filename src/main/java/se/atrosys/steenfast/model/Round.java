package se.atrosys.steenfast.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Map;

/**
 * TODO write documentation
 */
@Entity
@Data
@Builder
public class Round {
	private @Id @GeneratedValue Long id;

	@OneToMany
	@MapKeyJoinColumn
	private Map<Player, Score> scores;
}
