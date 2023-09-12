package fr.rb.fireforest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import fr.rb.fireforest.business.Cell;
import fr.rb.fireforest.business.CellEtat;
import fr.rb.fireforest.business.Configuration;
import fr.rb.fireforest.service.AutomataService;
import lombok.NoArgsConstructor;
@Service

public class AutomataServiceImpl implements AutomataService {
	Random random = new Random();
	

	@Override
	public List<Cell> nextState(Configuration configuration, List<Cell> cells) {
		int size = configuration.getDefinition();
		int compteur = 0;
		float propagation = configuration.getPbPropagation()*configuration.getMateriau().getInflammabilite();
		List<Cell> tmpCells = new ArrayList<>();
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j< size; j++) {
				
				if (cells.get(compteur).getCellEtat() == CellEtat.INTACT ) {// on verifie si la case du dessus propage le feu sur la case itérée
					if ((compteur-size >= 0)&&
						(cells.get(compteur-size).getCellEtat() == CellEtat.EN_FEU)&&
						(random.nextInt(100) < propagation)) {
						tmpCells.add(new Cell(i,j,CellEtat.EN_FEU));
					}
					else if((compteur+size < cells.size())&&
							(cells.get(compteur+size).getCellEtat() == CellEtat.EN_FEU)&&//case du dessous
							(random.nextInt(100) < propagation)) {
						tmpCells.add(new Cell(i,j,CellEtat.EN_FEU));
					}
					else if((compteur+1 < cells.size())&&
							(cells.get(compteur+1).getLatitude() == cells.get(compteur).getLatitude())&&//case de droite
							(cells.get(compteur+1).getCellEtat() == CellEtat.EN_FEU)&&
							(random.nextInt(100) < propagation)) {
						tmpCells.add(new Cell(i,j,CellEtat.EN_FEU));
					}
					else if((compteur-1 > -1)&&
							(cells.get(compteur-1).getLatitude() == cells.get(compteur).getLatitude())&&//case de gauche
							(cells.get(compteur-1).getCellEtat() == CellEtat.EN_FEU)&&
							(random.nextInt(100) < propagation)) {
						tmpCells.add(new Cell(i,j,CellEtat.EN_FEU));
					}
					else {
						tmpCells.add(new Cell(i,j,CellEtat.INTACT));
					}
				}
				else if (cells.get(compteur).getCellEtat() == CellEtat.EN_FEU) {
					tmpCells.add(new Cell(i,j,CellEtat.CENDRE));
				}
				else {
					tmpCells.add(new Cell(i,j,CellEtat.CENDRE));
				}
				
				compteur++;
			}
		}
		return tmpCells;
	}

	@Override
	//on initialise la grille en utilisant une des configurations
	public List<Cell> initGrid(Configuration configuration) {
		List<Cell> cells = new ArrayList<>();
		for (int i = 0; i < configuration.getDefinition(); i++) {
			for (int j = 0; j < configuration.getDefinition(); j++) {
				Cell cell = new Cell(i, j, CellEtat.INTACT);
				cells.add(cell);
			}
		}
		//on genere les foyers initiaux en prenant garde aux doublons
		for (int i = 0; i < configuration.getNbFoyer(); i++) {
			int cellRand = random.nextInt(cells.size());
			if (cells.get(cellRand).getCellEtat() == CellEtat.INTACT) {
				cells.get(cellRand).setCellEtat(CellEtat.EN_FEU);
			} else {
				i--;
			}
		}
		
		return cells;
		
	}

}
