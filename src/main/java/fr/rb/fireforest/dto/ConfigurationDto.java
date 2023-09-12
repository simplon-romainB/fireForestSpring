package fr.rb.fireforest.dto;


import fr.rb.fireforest.business.Materiau;
import lombok.Data;
@Data
public class ConfigurationDto {
	
	private MateriauDto materiauDto;
	private String nom;
	private int definition;
	private int nbFoyer;
	private int pbPropagation;

}
