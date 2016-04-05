package se.atrosys.steenfast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import se.atrosys.steenfast.repository.TournamentRepository;

/**
 * TODO write documentation
 */
@Controller
public class TournamentController {
	@Autowired
	TournamentRepository tournamentRepository;

	@RequestMapping("/tournament")
	public String tournament(@RequestParam(value="tournament", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);

		return "tournament";
	}

	@RequestMapping(value = "/tournaments", method = RequestMethod.GET)
	public String tournaments(Model model) {
		model.addAttribute("tournaments", tournamentRepository.findAll());

		return "tournaments";
	}
}
