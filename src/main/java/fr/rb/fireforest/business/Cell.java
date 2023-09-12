package fr.rb.fireforest.business;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Cell {
	
	@NotNull
	private int latitude = 0;
	@NotNull
	private int longitude = 0;
	private CellEtat cellEtat;
	//L'objet cell utilise l'enum cell Etat pour afficher ses etats
	
}
