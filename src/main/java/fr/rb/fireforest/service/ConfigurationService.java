package fr.rb.fireforest.service;

import java.util.List;

import fr.rb.fireforest.business.Configuration;
import fr.rb.fireforest.dto.ConfigurationDto;

public interface ConfigurationService {
	List<Configuration> recupererConfigurations();
	
	Configuration recupererConfigurationParId(String nom);
	
	Configuration enregistrerConfiguration(Configuration configuration);
	
	Configuration enregistrerConfiguration(ConfigurationDto configurationDto);
}
