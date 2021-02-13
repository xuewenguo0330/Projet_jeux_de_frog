package frogInf;

import java.awt.Color;
import java.util.ArrayList;

import environment.Car;
import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.ILane;
import graphicalElements.Element;

public class LaneInf implements ILane {
	protected Game game;
	protected int ord;
	protected int speed;
	protected boolean leftToRight;
	protected double density;
	protected int tic = 0;
	protected ArrayList<CarInf> cars = new ArrayList<>();

	public LaneInf(Game game, int ord, double density){
		this.game=game;
		this.ord=ord;
		this.density=density;
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
		this.leftToRight=game.randomGen.nextBoolean();
		for(int i = 0; i < 4 * game.width; ++i) {
			this.moveCarsInf(true);
			//System.out.println("je suis inf1 " + this.game.getfrog().getScore());
			this.mayAddCarInf();
		}
	}

	public LaneInf(Game game, int ord ) {
		this(game,ord, game.defaultDensity);
	}

	public void moveCarsInf(boolean b){
			for(int i = 0; i< this.cars.size(); i++){
				this.cars.get(i).moveCarInf(b);
			}
			//System.out.println(this.cars.size());
			RemoveOutEcrancars();
		}

		/*retirer les voitures dehors de l'ecran */
	public void RemoveOutEcrancars(){
			ArrayList<CarInf> deleteCars=new ArrayList<>();
			for(CarInf c : this.cars){
				if(c.NoAppearsInEcran()){
					deleteCars.add(c);
				}
			}
			for(CarInf dc : deleteCars){
				this.cars.remove(dc);
			}
		}

	public void update() {
		addToGraphics();
		this.tic++;
		if (this.tic > this.speed) {
			this.moveCarsInf(true);
			this.mayAddCarInf();
			this.tic = 0;
		} else {
			this.moveCarsInf(false);
		}
	}

		// TODO : ajout de methodes
		//test si la grenouille est sain et sauf
	public boolean isSafeFrog(Case c){
			for(CarInf v : this.cars) {
				if(v.getLeftPosition().ord == c.ord){
					if(v.getLeftPosition().absc <= c.absc && v.getLeftPosition().absc + v.getLength() > c.absc) {
						return false;
					}
				}
			}
			return true;
		}

		//tester si la voiture dans la Case c est sain et sauf
	public boolean isSafe(Case c){
		for(int i=0;i<this.cars.size();i++){
			Car v =this.cars.get(i);
			if (c.ord != v.getLeftPosition().ord) {
				return false;
			} else {
				return c.absc >=v.getLeftPosition().absc && v.getLeftPosition().absc + v.getLength() > c.absc;
			}
		}
		return true;
	}

		/*
		 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
		 */

		/**
		 * Ajoute une voiture au début de la voie avec probabilité égale à la
		 * densité, si la premiére case de la voie est vide
		 */
	private void mayAddCarInf() {
			if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
				if (game.randomGen.nextDouble() < density) {
					this.cars.add(new CarInf(game, getBeforeFirstCase(), leftToRight));
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
        for(int i = 0; i < this.game.width; i++) {
            this.game.getGraphic().add(new Element(i, this.ord-this.game.getfrog().getScore(),  Block.lane));
        }
    }

	@Override
	public boolean get_block(Case pos) {throw new RuntimeException("not implemented");}
	public boolean avoir_bonus(Case pos) {throw new RuntimeException("not implemented");}
	public boolean glissante(Case pos) {throw new RuntimeException("not implemented");}
}


