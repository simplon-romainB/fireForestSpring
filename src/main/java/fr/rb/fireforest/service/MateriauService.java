package fr.rb.fireforest.service;

import java.util.List;

import fr.rb.fireforest.business.Materiau;

public interface MateriauService {
	void ajouterMateriau(Materiau materiau);
	
	List<Materiau> recupererMateriaux();
	
	Materiau recupererMateriauParId(String nom);
	
	
}
