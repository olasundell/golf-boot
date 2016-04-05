package se.atrosys.steenfast.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;
import se.atrosys.steenfast.model.Tournament;
import se.atrosys.steenfast.repository.TournamentRepository;

import java.util.Arrays;
import java.util.List;

/**
 * TODO write documentation
 */
@Component
public class SampleDataSetup implements ApplicationListener<ContextRefreshedEvent> {
	private TournamentRepository tournamentRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public void setTournamentRepository(TournamentRepository tournamentRepository) {
		logger.info("Getting tournament repository by autowiring");
		this.tournamentRepository = tournamentRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("Context refreshed event received, starting data import");

		tournamentRepository.deleteAll();

		List<Tournament> tournaments = Arrays.asList(Tournament.builder().name("Första").build(),
				Tournament.builder().name("Andra").build());

		tournamentRepository.save(tournaments);

		logger.info("Data import complete");
	}
}
