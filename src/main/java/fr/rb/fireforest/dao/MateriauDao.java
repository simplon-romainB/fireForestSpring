package fr.rb.fireforest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.rb.fireforest.business.Materiau;
@Repository
public interface MateriauDao extends JpaRepository<Materiau, String> {

}
