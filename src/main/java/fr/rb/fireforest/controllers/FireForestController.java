package fr.rb.fireforest.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.rb.fireforest.business.Cell;
import fr.rb.fireforest.business.Configuration;
import fr.rb.fireforest.business.Materiau;
import fr.rb.fireforest.service.AutomataService;
import fr.rb.fireforest.service.ConfigurationService;
import fr.rb.fireforest.service.MateriauService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class FireForestController {
	private final AutomataService automataService;
	private final ConfigurationService configurationService;
	private final MateriauService materiauService;
	private List<Cell> thisCells = new ArrayList<>();
	private static Configuration configuration;

	@GetMapping(value = { "/", "/index" })
	public synchronized ModelAndView getGrid() {// la methode utilisant une variable static le synchronized évite les
												// risques du au multithreading.

		ModelAndView mav = new ModelAndView();
		// on teste les differentes possibilités

		// si il y a une configuration chargée et que la grille est vide on l'initialise
		// avec la configuration
		if (thisCells.isEmpty() && configuration != null) {
			thisCells = automataService.initGrid(configuration);

		}
		// si la grille est vide et qu'aucune configuration n'est chargée on charge la
		// configuration par defaut
		else if (thisCells.isEmpty()) {
			configuration = configurationService.recupererConfigurations().get(0);
			thisCells = automataService.initGrid(configuration);

		}
		// si la grille est en cours et qu'une configuration est chargée on continue sur
		// cette configuration
		else if (configuration != null) {
			thisCells = automataService.nextState(configuration, thisCells);
		}

		else {
			// si la grille est en cours et qu'aucune configuration n'est chargée on charge
			// la configuration par defaut.
			configuration = configurationService.recupererConfigurations().get(0);
			thisCells = automataService.nextState(configuration, thisCells);

		}
		// thisCells est la liste principale qui sera affichée puis renvoyé aux services
		// afin de traitements.
		mav.addObject("cells", thisCells);
		mav.setViewName("index");
		mav.addObject("configs", configurationService.recupererConfigurations());
		mav.addObject("materiaux", materiauService.recupererMateriaux());
		// on a besoin d'une configuration temporaire pendant le remplissage du
		// formulaire
		mav.addObject("config", new Configuration());
		mav.addObject("configs", configurationService.recupererConfigurations());
		// sqrtCellsSize permet de connaitre la racine carrée de la taille totale de
		// thisCells afin de former une grille carrée
		mav.addObject("sqrtCellsSize", (int) Math.sqrt(thisCells.size()));
		return mav;

	}

	@PostMapping("configuration")
	// on sauvegarde une configuration par le biais du formulaire de la jsp notée
	// ModelAttribute config.
	public ModelAndView postConfiguration(@Valid @ModelAttribute Configuration config) {
		Materiau materiau = materiauService.recupererMateriauParId(config.getMateriau().getNom());
		config.setMateriau(materiau);
		configurationService.enregistrerConfiguration(config);
		return new ModelAndView("redirect:/index");

	}

	@PostMapping("loadconfiguration") // le synchronized avec une variable static évite les risques du au
										// multithreading.
	public synchronized ModelAndView postLoadConfiguration(
			@RequestParam("selectedConfiguration") String selectedConfiguration) {
		configuration = configurationService.recupererConfigurationParId(selectedConfiguration);
		thisCells.clear();
		return new ModelAndView("redirect:/index");

	}

	@PostMapping("next") // methode simple qui consiste a avancer d'une étape sur le feu en
							// rafraichissant la page
	public ModelAndView nextState() {
		return new ModelAndView("redirect:/index");
	}

}
