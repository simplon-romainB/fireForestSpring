package fr.rb.fireforest.business;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
public class Configuration {
	@ManyToOne
	@ToString.Exclude
	private Materiau materiau;
	@Id
	private String nom;
	private int definition;
	private int nbFoyer;
	private int pbPropagation;
	//la configuration qui possède un materiau qui change la probabilité de propagation
}
