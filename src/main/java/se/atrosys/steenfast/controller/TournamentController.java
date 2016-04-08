package se.atrosys.steenfast.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import se.atrosys.steenfast.model.Tournament;
import se.atrosys.steenfast.repository.TournamentRepository;

/**
 * TODO write documentation
 */
@Controller
public class TournamentController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	TournamentRepository tournamentRepository;

	@RequestMapping("/tournament")
	public String tournament(@RequestParam(value="tournament", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);

		return "tournament";
	}

	@RequestMapping(value = "/tournaments", method = RequestMethod.GET)
	public String tournaments(Model model) {
		Iterable<Tournament> all = tournamentRepository.findAll();
		model.addAttribute("tournaments", all);

		return "tournaments";
	}

	@RequestMapping(value = "/logintest", method = RequestMethod.POST)
	public String loginTest(@RequestBody String body) {
		logger.info(body);

		return body;
	}
}
