package frogRiver;

import java.awt.Color;

import environment.Car;
import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import graphicalElements.Element;

public class Driftwood extends Car{
	protected final Color colorLtR = new Color(230,191,131);
	protected final Color colorRtL = new Color(190,166,100);
	
	public Driftwood(Game game, Case frontPosition,  boolean leftToRight) {
		super(game, frontPosition,  leftToRight);
		this.length = 3;
	}

    /*Si b == ture
    * si la grenouille est sur le rondin, alors la grenouille se déplace avec ce rondin-là avec la même vitesse
    * puis on déplace le rondin vers sa direction  */
	public void moveWood(boolean b){
		if(b) {
			if(withFrog()){
				if(leftToRight){
					this.game.getfrog().move(Direction.right);
				}else{
					this.game.getfrog().move(Direction.left);
					}
			}
			if (this.leftToRight ) {
				this.leftPosition = new Case(this.leftPosition.absc + 1, this.leftPosition.ord);
			}else{
				this.leftPosition=new Case(this.leftPosition.absc-1,this.leftPosition.ord);
			}
		}
		this.addToGraphicsRiver();
	}

	//return si le frog est sur ce rondin
	public boolean withFrog(){
		Case fgPos = this.game.getfrog().getPosition();
		return (this.leftPosition.ord == (fgPos.ord + this.game.getfrog().getScore())
				&& this.leftPosition.absc <= fgPos.absc
				&& fgPos.absc <this.leftPosition.absc+this.length);
	}

	// On remet le rondin sur l'écran et si la grenouille et sur ce rondin, on la remet aussi.
	private void addToGraphicsRiver() {
		for (int i = 0; i < this.length; i++) {
			Block block= Block.wood;
			if (this.leftToRight){
				 block= Block.woodl;
			}
			int sc = this.game.getfrog().getScore();
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord - sc , block));
			}
		if(withFrog()){
			game.getGraphic()
					.add(new Element(  this.game.getfrog().getPosition(), Block.frog));
		}
	}
	

    
}
