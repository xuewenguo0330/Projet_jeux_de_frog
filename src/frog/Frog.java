package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {
	
	protected Game game;
	protected Case position;
	protected Direction direction;
	
	public Frog(Game game) {
		this.game = game;
		this.position = new Case(this.game.width/2,0);
		this.direction = Direction.up;
	}
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 * */
	public Case getPosition() {
		return this.position;
	}
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key) {
		//try switch after
		if(key == Direction.up && this.position.ord < this.game.height-1) {
			this.position = new Case(this.position.absc, this.position.ord+1);
		}else if(key == Direction.down && this.position.ord > 0) {
			this.position = new Case(this.position.absc, this.position.ord-1);
		}else if(key == Direction.left && this.position.absc > 0) {
			this.position = new Case(this.position.absc-1, this.position.ord);
		}else if(key == Direction.right && this.position.absc < this.game.width-1) {
			this.position = new Case(this.position.absc+1, this.position.ord);
		}else {
			System.out.println("la grenouille atteigne le bord ");
		}
	}

	//public void changePos(Case newPos){ this.position = newPos; }
	@Override
	public int getScore() {throw new RuntimeException("not implemented");}
	public void Bonus() {throw new RuntimeException("not implemented");}
	public int getGrade() {throw new RuntimeException("not implemented");}

}
