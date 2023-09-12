package fr.rb.fireforest.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.rb.fireforest.business.Configuration;
import fr.rb.fireforest.business.Materiau;
import fr.rb.fireforest.dao.ConfigurationDao;
import fr.rb.fireforest.dao.MateriauDao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AjoutDonneesInitiales implements CommandLineRunner {
	private final ConfigurationDao configurationDao;
	private final MateriauDao materiauDao;

	@Override
	public void run(String... args) throws Exception {

		ajouterBouleau();
		ajouterSapin();
		ajouterConfiguration();
		ajouterConfigurationMedium();
		ajouterConfigurationHigh();

	}

	private void ajouterBouleau() {
		Materiau materiau = new Materiau();
		materiau.setNom("bouleau");
		materiau.setInflammabilite(1);
		materiauDao.save(materiau);
	}
	private void ajouterSapin() {
		Materiau materiau = new Materiau();
		materiau.setNom("sapin");
		materiau.setInflammabilite(1.3F);
		materiauDao.save(materiau);
	}

	private void ajouterConfiguration() {
		Configuration configuration = new Configuration();
		configuration.setNom("defaut");
		configuration.setMateriau(materiauDao.findById("bouleau").orElse(null));
		configuration.setNbFoyer(3);
		configuration.setDefinition(50);
		configuration.setPbPropagation(60);
		configurationDao.save(configuration);
	}
	
	private void ajouterConfigurationMedium() {
		Configuration configuration = new Configuration();
		configuration.setNom("medium");
		configuration.setMateriau(materiauDao.findById("bouleau").orElse(null));
		configuration.setNbFoyer(4);
		configuration.setDefinition(75);
		configuration.setPbPropagation(60);
		configurationDao.save(configuration);
	}
	private void ajouterConfigurationHigh() {
		Configuration configuration = new Configuration();
		configuration.setNom("high");
		configuration.setMateriau(materiauDao.findById("bouleau").orElse(null));
		configuration.setNbFoyer(4);
		configuration.setDefinition(90);
		configuration.setPbPropagation(65);
		configurationDao.save(configuration);
	}
	

}
