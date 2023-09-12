package fr.rb.fireforest.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Materiau {
	@Id
	private String nom;
	private float inflammabilite;
	@OneToMany(mappedBy = "materiau", fetch= FetchType.EAGER)
	@ToString.Exclude
	private List<Configuration> configurations;
	
	//Afin de ne pas avoir de probleme cyclique on met la liste des configurations en exclude
}
