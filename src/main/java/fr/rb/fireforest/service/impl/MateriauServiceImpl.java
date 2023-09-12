package fr.rb.fireforest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.rb.fireforest.business.Materiau;
import fr.rb.fireforest.dao.MateriauDao;
import fr.rb.fireforest.service.MateriauService;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class MateriauServiceImpl implements MateriauService {
	private MateriauDao materiauDao;

	@Override
	public void ajouterMateriau(Materiau materiau) {
		materiauDao.save(materiau);
		
	}

	@Override
	public List<Materiau> recupererMateriaux() {
		
		return materiauDao.findAll();
	}

	@Override
	public Materiau recupererMateriauParId(String nom) {
		
		return materiauDao.findById(nom).orElse(null);
	}

}
