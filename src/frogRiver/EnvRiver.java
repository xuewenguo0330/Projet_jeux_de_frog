package frogRiver;

import java.util.ArrayList;

import frogInf.LaneInf;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.ILane;

public class EnvRiver implements IEnvironment{
	    //les attributs
		private ArrayList<ILane> lines;
		protected Game game;

		public EnvRiver(Game game) {
			this.game = game;
			this.lines = new ArrayList<ILane>();
			this.lines.add(new LaneInf(game, 0, 0.0D));
			this.lines.add(new LaneInf(game, 1, 0.0D));
			for(int i = 2; i < game.height - 1; ++i) {
				if((i==4) || i ==8 || i > this.game.height-9 && i < this.game.height-6 ) {
				   this.lines.add(new River(game, i));
				}else {
					this.lines.add(new LaneInf(game, i));
				}
			}

			this.lines.add(new LaneInf(game, game.height - 1, 0.0D));
		}

	   //On ajoute aléatoirement des voies ou des rivière
	   //pour que la grenouille peut avancer de façon infinie
		public void addVoies(){
			if(this.lines.size() - this.game.getfrog().getScore() < this.game.height+1){
				if(game.randomGen.nextBoolean()){
				       ILane voie = new LaneInf(this.game, this.lines.size());
				       this.lines.add(voie);
				}else {
					   ILane river = new River(this.game, this.lines.size());
				       this.lines.add(river);
				}
			}
		}

		
		public void update() {
			addVoies();
			for(ILane v : this.lines) {
				v.update();
			}
		}

        //Tester si la grenouille n'est pas en danger
		public boolean isSafe(Case c){
			    Case fg = new Case(c.absc, c.ord+this.game.getfrog().getScore());
			    if(fg.ord == 0 || fg.ord == 1) {return true;}
			    ILane r = this.lines.get(fg.ord);
			    return r.isSafeFrog(fg);
		}

		@Override
		public boolean isWinningPosition(Case c) {throw new RuntimeException("not implemented");}
		public boolean get_block(Case var1) {throw new RuntimeException("not implemented");}
		public boolean get_bonus(Case var1){throw new RuntimeException("not implemented");}
		public boolean glissantt(Case pos){throw new RuntimeException("not implemented");}

}
