package frogInf;

import environment.Environment;

import java.util.ArrayList;

import environment.Lane;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;

public class EnvInf implements IEnvironment {
	//les attributs
	private ArrayList<LaneInf> roadLines;
	protected Game game;

	public EnvInf(Game game) {
		//super(game);
		this.game = game;
		this.roadLines = new ArrayList();
		this.roadLines.add(new LaneInf(game, 0, 0.0D));
		this.roadLines.add(new LaneInf(game, 1, 0.0D));
		for(int i = 2; i < game.height - 1; ++i) {
			this.roadLines.add(new LaneInf(game, i));
		}

		this.roadLines.add(new LaneInf(game, game.height - 1, 0.0D));
	}

	/*addVoies
	* ajouter une voie pour que l'écran soit toujours rempli par les voies
	* et que la grenouille puisse avancer de façon infinie */
	public void addVoies(){
		if(this.roadLines.size() - this.game.getfrog().getScore() < this.game.height+1){

			LaneInf voie = new LaneInf(this.game, this.roadLines.size());
			this.roadLines.add(voie);
		}
	}

	//Mettre à jour pour tous les voies
	public void update() {
		addVoies();
		for(LaneInf v : this.roadLines) {
			v.update();
		}
	}

	/*isSafe pour la grenouille
	   c la position de grenouille dans l'écran
	   return true si la grenouille est sain et sauf dans sa position actuelle*/
	public boolean isSafe(Case c){
		//La position de grenouille par rapport à tous les voies
			Case fg = new Case(c.absc, c.ord+this.game.getfrog().getScore());
			LaneInf v = this.roadLines.get(fg.ord);
			return v.isSafeFrog(fg);
	}

		@Override
		public boolean isWinningPosition(Case c) {throw new RuntimeException("not implemented");}
	    public boolean get_block(Case var1) {throw new RuntimeException("not implemented");}
	    public boolean get_bonus(Case var1){throw new RuntimeException("not implemented");}
	    public boolean glissantt(Case pos){throw new RuntimeException("not implemented");}



}
