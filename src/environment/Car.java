package environment;

import java.awt.Color;

import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	protected Game game;
	protected Case leftPosition; //l'arrière de la voiture
	protected boolean leftToRight;
	protected int length;
	protected final Color colorLtR = Color.BLACK;
	protected final Color colorRtL = Color.BLUE;

	//TODO Constructeur(s)
	public Car(Game game,Case frontPosition,boolean leftToRight){
		this.game=game;
		this.length=game.randomGen.nextInt(4);
		this.leftToRight=leftToRight;
		if(leftToRight==true){
			this.leftPosition=new Case(frontPosition.absc-this.length+1,frontPosition.ord);
		}else{
			this.leftPosition=new Case(frontPosition.absc,frontPosition.ord);
		}
	}

	//TODO : ajout de methodes
	public int getLength(){  return this.length;  }

	public Case getLeftPosition(){  return this.leftPosition; }

    /*Move car
    * b boolean si b ==true alors on peut deplacer la voiture vers sa direction
    * */
    public void MoveCar(boolean b){
    	if(b) {
			if (this.leftToRight)
				this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
			else{
				this.leftPosition=new Case(this.leftPosition.absc-1,this.leftPosition.ord);
			}
		}
		this.addToGraphics();
    }


	/*Fonction NoAppersInEcran
	 * Si la voiture n'est pas dans l'ecran, return false
	 * */
	public boolean NoAppearsInEcran() {
		return this.leftPosition.absc + this.length < 0 || this.leftPosition.absc > this.game.width;
	}

	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
        for(int i = 0; i < this.length; i++) {
            this.game.getGraphic().add(new Element(this.leftPosition.absc + i, this.leftPosition.ord, this.leftToRight ? Block.car : Block.carl));
        }
    }

}