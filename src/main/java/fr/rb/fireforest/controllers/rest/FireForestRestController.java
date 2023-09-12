package fr.rb.fireforest.controllers.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.rb.fireforest.business.Configuration;
import fr.rb.fireforest.dto.ConfigurationDto;
import fr.rb.fireforest.service.ConfigurationService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
public class FireForestRestController {
	
	ConfigurationService configurationService;
	
	@GetMapping("configurations")
	public List<Configuration> getConfiguration() {
		return configurationService.recupererConfigurations();
	}
	
	@PostMapping("sauvegarderConfiguration")
	public Configuration postConfiguration(@Valid @RequestBody ConfigurationDto configurationDto, BindingResult result) {
		return configurationService.enregistrerConfiguration(configurationDto);
	}

}
