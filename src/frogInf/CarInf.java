package frogInf;

import java.awt.Color;

import environment.Car;
import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class CarInf extends Car{

	//TODO Constructeur(s)
	public CarInf(Game game, Case frontPosition,  boolean leftToRight) {
		super(game, frontPosition,  leftToRight);
	}

	/*moveCarInf
	 * b boolean si b ==true alors on peut deplacer la voiture vers sa direction
	 * */
	public void moveCarInf(boolean b){
		if(b) {
			if (this.leftToRight ) {
				this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
			}else{
				this.leftPosition=new Case(this.leftPosition.absc-1,this.leftPosition.ord);
			}
		}
		this.addToGraphicsInf();
	}

	//On ajoute les voitures dans l'écran et on les faire descendres(ou monter) lorsqu'on veux
	// que la grenouille monte(descend) pour donner une impression que la grenouille move en haut ou en bas
	private void addToGraphicsInf() {
		for (int i = 0; i < this.length; i++) {
			Block color = Block.carl;
			if (this.leftToRight){
				color = Block.car;
			}
			int sc = this.game.getfrog().getScore();
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord - sc , color));
		}
	}
	
}
