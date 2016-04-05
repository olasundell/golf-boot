package se.atrosys.steenfast.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * TODO write documentation
 */
@Entity
@Builder
@Data
public class Score {
	private @Id @GeneratedValue Long id;

	private int score;
}
