package fr.rb.fireforest.service;

import java.util.List;

import fr.rb.fireforest.business.Cell;
import fr.rb.fireforest.business.Configuration;

public interface AutomataService {
	List<Cell> initGrid(Configuration configuration);
	/**
	 * 
	 * @param configuration
	 * @param cells
	 * @return
	 */
	List<Cell> nextState(Configuration configuration, List<Cell> cells);
}
