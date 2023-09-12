package fr.rb.fireforest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.rb.fireforest.business.Configuration;
import fr.rb.fireforest.dao.ConfigurationDao;
import fr.rb.fireforest.dto.ConfigurationDto;
import fr.rb.fireforest.mapper.FireForestMapper;
import fr.rb.fireforest.service.ConfigurationService;
import fr.rb.fireforest.service.MateriauService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfigurationServiceImpl implements ConfigurationService {
	private ConfigurationDao configurationDao;
	private MateriauService materiauService;
	private FireForestMapper fireForestMapper;

	@Override
	public List<Configuration> recupererConfigurations() {
		return configurationDao.findAll();
	}

	@Override
	public Configuration recupererConfigurationParId(String nom) {

		return configurationDao.findById(nom).orElse(null);
	}

	@Override
	public Configuration enregistrerConfiguration(Configuration configuration) {

		return configurationDao.save(configuration);
	}

	@Override
	public Configuration enregistrerConfiguration(ConfigurationDto configurationDto) {
		Configuration configuration = Configuration.builder().nom(configurationDto.getNom())
				.definition(configurationDto.getDefinition()).pbPropagation(configurationDto.getPbPropagation())
				.materiau(materiauService.recupererMateriauParId(configurationDto.getMateriauDto().getNom()))
				.nbFoyer(configurationDto.getNbFoyer()).build();
		fireForestMapper.toEntity(configurationDto);
		return configurationDao.save(configuration);

	}

}
