package se.atrosys.steenfast.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;
import se.atrosys.steenfast.model.Player;
import se.atrosys.steenfast.model.Round;
import se.atrosys.steenfast.model.Score;
import se.atrosys.steenfast.model.Tournament;
import se.atrosys.steenfast.repository.PlayerRepository;
import se.atrosys.steenfast.repository.RoundRepository;
import se.atrosys.steenfast.repository.ScoreRepository;
import se.atrosys.steenfast.repository.TournamentRepository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO write documentation
 */
@Component
public class SampleDataSetup implements ApplicationListener<ContextRefreshedEvent> {
	private TournamentRepository tournamentRepository;
	private PlayerRepository playerRepository;
	private RoundRepository roundRepository;
	private ScoreRepository scoreRepository;

	private LocalDate baseDate = OffsetDateTime.now().minus(10, ChronoUnit.DAYS).toLocalDate();
	private AtomicLong dateOffset = new AtomicLong(0);

	private Random random = new Random(0);

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public void setTournamentRepository(TournamentRepository tournamentRepository) {
		this.tournamentRepository = tournamentRepository;
	}

	@Autowired
	public void setPlayerRepository(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Autowired
	public void setRoundRepository(RoundRepository roundRepository) {
		this.roundRepository = roundRepository;
	}

	@Autowired
	public void setScoreRepository(ScoreRepository scoreRepository) {
		this.scoreRepository = scoreRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("Context refreshed event received, starting data import");

		tournamentRepository.deleteAll();
		roundRepository.deleteAll();
		playerRepository.deleteAll();

		List<Player> players = createPlayers();

		Iterable<Player> save = playerRepository.save(players);

		players = new ArrayList<>();
		save.forEach(players::add);

		final Tournament första = Tournament.builder()
				.name("Första")
				.players(players)
				.round(randomRound(players))
				.round(randomRound(players))
				.round(randomRound(players))
				.build();

		final Tournament result = tournamentRepository.save(första);

		final Tournament andra = Tournament.builder()
				.name("Andra")
				.players(players)
				.round(randomRound(players))
				.round(randomRound(players))
				.round(randomRound(players))
				.build();

		tournamentRepository.save(andra);

		logger.info("Data import complete");
	}

	private Round randomRound(List<Player> players) {
		final Round.RoundBuilder builder = Round.builder();

		builder.date(baseDate.plus(dateOffset.getAndAdd(1), ChronoUnit.DAYS));

		players.stream().forEach((p) -> {
			Score score = Score.builder().score(random.nextInt(25) + 75).build();
			scoreRepository.save(score);
			builder.score(p, score);
		});

		Round round = builder.build();

		return roundRepository.save(Collections.singletonList(round)).iterator().next();
	}

	private List<Player> createPlayers() {
		final Player kalle = Player.builder().name("Kalle").build();
		final Player nina = Player.builder().name("Nina").build();
		final Player rusken = Player.builder().name("Rusken").build();
		final Player jocke = Player.builder().name("Jocke").build();

		return Arrays.asList(kalle, nina, rusken, jocke);
	}
}
