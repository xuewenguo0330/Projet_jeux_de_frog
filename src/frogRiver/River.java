package frogRiver;
import java.awt.Color;
import java.util.ArrayList;

import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.ILane;
import graphicalElements.Element;


public class River implements ILane {
		protected Game game;
		protected int ord;
		protected int speed;
		protected boolean leftToRight;
		protected double density;
		protected int tic = 0;
		protected ArrayList<Driftwood> woods = new ArrayList<>();

		public River(Game game, int ord, double density){
			this.game=game;
			this.ord=ord;
			this.density=density;
			this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
			this.leftToRight=game.randomGen.nextBoolean();
			for(int i = 0; i < 4 * game.width; ++i) {
				this.moveWoods(true);
				this.mayAddWood();
			}
		}
		
		public River(Game game, int ord ) {
			this(game,ord, game.defaultDensity);
		}

		public void moveWoods(boolean b){
				for(int i = 0; i< this.woods.size(); i++){
					this.woods.get(i).moveWood(b);
				}
				//System.out.println(this.cars.size());
				RemoveOutEcranWoods();
			}

			/*retirer les rondins flottants en dehors de l'ecran */
		public void RemoveOutEcranWoods(){
				ArrayList<Driftwood> deleteWoods =new ArrayList<>();
				for(Driftwood w : this.woods){
					if(w.NoAppearsInEcran()){
						deleteWoods.add(w);
					}
				}
				for(Driftwood dw : deleteWoods){
					this.woods.remove(dw);
				}
			}

		public void update() {
			addToGraphics();
				this.tic++;
				if(this.tic>this.speed){
					this.moveWoods(true);
					this.mayAddWood();
					this.tic=0;
				}
				else{
					this.moveWoods(false);
				}
			}

			// TODO : ajout de methodes
			//test si une case est safe
		public boolean isSafeFrog(Case c){
			for(Driftwood w : this.woods) {
					if(w.getLeftPosition().ord == c.ord){ // la grenouille est sur la rivière
						//si la grenouille est sur le rondin flottant
						if(w.getLeftPosition().absc <= c.absc && c.absc < w.getLeftPosition().absc + w.getLength()) {
							return (c.absc>0 && c.absc<this.game.width);
						}
					}
				}
				return false;
			}
		
		public boolean isSafe(Case c){
			for(int i=0;i<this.woods.size();i++){
				 Driftwood w =this.woods.get(i);
				if (c.ord != w.getLeftPosition().ord) {
					return false;
				} else {
					return c.absc >= w.getLeftPosition().absc && w.getLeftPosition().absc + w.getLength() > c.absc;
				}
			}
			return true;
		}

			/*
			 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
			 */

			/**
			 * Ajoute un rondin au début de la rivière avec probabilité égale à la densité , si la première
			 *  case de la rivière est vide
			 */
		private void mayAddWood() {
				if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
					if (game.randomGen.nextDouble() < this.density*10) {
						this.woods.add(new Driftwood(game, getBeforeFirstCase(), leftToRight));
						//System.out.println("yes");
					}
				}
			}

		private Case getFirstCase() {
				if (leftToRight) {
					return new Case(0, ord);
				} else
					return new Case(game.width - 1, ord);
			}

		private Case getBeforeFirstCase() {
				if (leftToRight) {
					return new Case(-1, ord);
				} else
					return new Case(game.width, ord);
			}
		
		public void addToGraphics() {
			int sc = this.game.getfrog().getScore();
	        for(int i = 0; i < this.game.width; i++) {
	            this.game.getGraphic().add(new Element(i, this.ord-sc, Block.river));
	        }
	    }

	@Override
	public boolean get_block(Case pos) {throw new RuntimeException("not implemented");}
	public boolean avoir_bonus(Case pos) {throw new RuntimeException("not implemented");}
	public boolean glissante(Case pos) {throw new RuntimeException("not implemented");}

}
