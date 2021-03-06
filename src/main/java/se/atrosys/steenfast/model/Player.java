package se.atrosys.steenfast.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * TODO write documentation
 */
@Entity
@Data
@Builder
public class Player {
	private @Id @GeneratedValue Long id;

	private String name;

	@Tolerate
	public Player() {}
}
