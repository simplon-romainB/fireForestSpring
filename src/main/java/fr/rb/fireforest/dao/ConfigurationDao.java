package fr.rb.fireforest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.rb.fireforest.business.Configuration;

@Repository
public interface ConfigurationDao extends JpaRepository<Configuration, String> {

}
