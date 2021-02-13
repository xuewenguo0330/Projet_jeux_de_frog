package environment;

import java.awt.Color;
import java.util.ArrayList;

import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.ILane;
import graphicalElements.Element;

public class Lane implements ILane {
	private Game game;
	private int ord;
	private int speed;
	private ArrayList<Car> cars;
	private boolean leftToRight;
	private double density;
	private int tic=0;

	// TODO : Constructeur(s)
	public Lane(Game game, int ord, double density){
		this.game=game;
		this.ord=ord;
		this.density=density;
		this.cars=new ArrayList();
		this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops);
		this.leftToRight=game.randomGen.nextBoolean();
		
		for(int i = 0; i < 4 * game.width; ++i) {
			this.moveCars(true);
			this.mayAddCar();
		}

	}

	public Lane(Game game, int ord) {
		this(game, ord, game.defaultDensity);
	}

	// Toutes les voitures se déplacent d'une case au bout d'un nombre "tic d'horloge" égal à leur vitesse
	// Notez que cette méthode est appelée à chaque tic d'horloge
	// Les voitures doivent etre ajoutes a l interface graphique meme quand
	// elle ne bougent pas
	// A chaque tic d'horloge, une voiture peut être ajoutée
	public void update() {
		addToGraphics();
		this.tic++;
		if(this.tic>this.speed){
			this.moveCars(true);
			this.mayAddCar();
			this.tic=0;//attendre la prochine movement
		}
		else{
			this.moveCars(false);
		}
	}

	// TODO : ajout de methodes
    //On déplace tous les voitures sur l'écran et on relève les voitures hors de l'écran
	public void moveCars(boolean b){
		ArrayList<Car>cars=this.getCars();
		for(int i=0;i<cars.size();i++){
			cars.get(i).MoveCar(b);
		}
		RemoveOutEcrancars();
	}

	/*retirer les voitures en dehors de l'ecran */
	public void RemoveOutEcrancars(){
		ArrayList<Car> deleteCars=new ArrayList<>();
		for(Car c : this.cars){
			if(c.NoAppearsInEcran()){
				deleteCars.add(c);
			}
		}
		for(Car dc : deleteCars){
			this.cars.remove(dc);
		}
	}

	/*IsSafe pour les voitures. Elle détermine si une voiture n'est pas en danger au Case c
	* le plus gauche est vide*/
	public boolean isSafe(Case c){
		for(int i=0;i<this.cars.size();i++){
			Car v =this.cars.get(i);
			if (c.ord != v.getLeftPosition().ord) {
				return false;
			} else {
				return c.absc >=v.getLeftPosition().absc && c.absc < v.getLeftPosition().absc + v.getLength();
			}
		}
		return true;
	}

	//isSafe pour déterminer si la grenouille n'est pas en danger dans sa position actuelle(Case c)
	public boolean isSafeFrog(Case c){
		for(Car v : this.cars) {
			if(v.getLeftPosition().ord == c.ord){
				if(v.getLeftPosition().absc <= c.absc && v.getLeftPosition().absc + v.getLength() > c.absc) {
					return false;
				}
			}
		}
		return true;
	}

	//Ajouter les voies dans l'écran
	public void addToGraphics() {
		for(int i = 0; i < this.game.width; i++) {
			this.game.getGraphic().add(new Element(i, this.ord, Block.lane));
		}
	}

    //Renvoyer la liste de voiture dans la voie courante
	public ArrayList<Car> getCars(){ return this.cars; }

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
	 */

	/**
	 * Ajoute une voiture au début de la voie avec probabilité égale à la
	 * densité, si la première case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase()) &&this.game.randomGen.nextDouble() < density) {
				cars.add(new Car(this.game, this.getBeforeFirstCase(), this.leftToRight));
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, this.ord);
		} else
			return new Case(game.width -1, this.ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, this.ord);
		} else
			return new Case(game.width, this.ord);
	}

	@Override
	public boolean get_block(Case pos) {throw new RuntimeException("not implemented");}
	public boolean avoir_bonus(Case pos) {throw new RuntimeException("not implemented");}
	public boolean glissante(Case pos) {throw new RuntimeException("not implemented");}

}

