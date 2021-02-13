package frogInf;


import frog.Frog;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class FrogInf extends Frog implements IFrog {
	protected int score = 0;
	
	public FrogInf(Game game) {
		super(game);
	}

	public int getScore(){ return this.score;}

	//move() pour la grenouille de jeux Infinie, on fix l'ordonné de la grenouille dans l'écran
	// et on stocke l'ordonnée de grenouille par rapport aux voies dans son score
	public void move(Direction key) {
		//try switch after
		if(key == Direction.up) {
			this.position = new Case(this.position.absc, 1);
			this.score ++;
		}else if(key == Direction.down && this.score > 0) {
			this.position = new Case(this.position.absc, 1);
			this.score--;
		}else if(key == Direction.left && this.position.absc > 0) {
			this.position = new Case(this.position.absc-1, this.position.ord);
		}else if(key == Direction.right && this.position.absc < this.game.width-1) {
			this.position = new Case(this.position.absc+1, this.position.ord);
		}else {
			System.out.println("la grenouille atteigne le bord ");
		}
	}

}
