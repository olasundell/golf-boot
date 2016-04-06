package se.atrosys.steenfast.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
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
	@Singular
	private Map<Player, Score> scores;

	private LocalDate date;

	@Tolerate
	public Round() {}
}
