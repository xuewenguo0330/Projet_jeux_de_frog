package frogPiege;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import environment.Car;
import frogInf.CarInf;
import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Game;
import gameCommons.ILane;
import graphicalElements.Element;

public class LanePiege implements ILane {
    protected Game game;
    protected int ord;
    protected int speed;
    protected boolean leftToRight;
    protected double density;
    protected int tic = 0;
    protected ArrayList<CarInf> cars = new ArrayList<>();
    private ArrayList<Piege> pieges;
    private ArrayList<Mur> murs;
    private ArrayList<Bonus>bonus;
    private ArrayList<Glissade>glissades;
    private double densityCaseSpecial;

    public LanePiege(Game game, int ord, double density){
        this.game=game;
        this.ord=ord;
        this.density=density;
        this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
        this.leftToRight=game.randomGen.nextBoolean();
        for(int i = 0; i < 4 * game.width; ++i) {
            this.moveCarsInf(true);
            this.mayAddCarInf();

        }
        this.pieges=new ArrayList<>();
        this.murs=new ArrayList<>();
        this.bonus=new ArrayList<>();
        this.glissades=new ArrayList<>();
        this.densityCaseSpecial=0.1d;
        this.mayAddPieges();
        this.mayAddMurs();
        this.mayAddBonus();
        this.mayAddGlissade();
    }

    public LanePiege(Game game, int ord ) {
        this(game,ord, game.defaultDensity);
    }

    public void moveCarsInf(boolean b){
        for(int i = 0; i< this.cars.size(); i++){
            this.cars.get(i).moveCarInf(b);
        }
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
        if(this.tic>this.speed){
            this.moveCarsInf(true);
            this.mayAddCarInf();
            this.tic=0;
        }
        else{
            this.moveCarsInf(false);
        }
        this.CaseSpecial();
    }

    // TODO : ajout de methodes
    //test si une case est safe, et tester le cas de cases spéciales
    public boolean isSafeFrog(Case c){
        for(CarInf v : this.cars) {
            if(v.getLeftPosition().ord == c.ord){
                if(v.getLeftPosition().absc <= c.absc && v.getLeftPosition().absc + v.getLength() > c.absc) {
                    return false;
                }
            }
        }
        for(Piege p :this.pieges){
            if(p.getPiege_pos().absc==c.absc&&p.getPiege_pos().ord==c.ord)
                return false;
        }
        return true;
    }



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
     * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
     * densit�, si la premi�re case de la voie est vide
     */
    private void mayAddCarInf() {
        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                this.cars.add(new CarInf(game, getBeforeFirstCase(), leftToRight));
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

    public void mayAddPieges(){
        if (this.game.randomGen.nextDouble() < this.densityCaseSpecial) {
            this.pieges.add(new Piege(this.game, this.ord));
        }
    }

    public void mayAddMurs(){
        if(this.game.randomGen.nextDouble()<this.densityCaseSpecial){
            this.murs.add(new Mur(this.game,this.ord));
        }
    }
    public void mayAddGlissade(){
        if(this.game.randomGen.nextDouble()<this.densityCaseSpecial){
            if(this.ord!=0){
                this.glissades.add((new Glissade(this.game,this.ord)));
            }
        }
    }

    public void mayAddBonus(){
        if(this.game.randomGen.nextDouble()<this.densityCaseSpecial){
            this.bonus.add(new Bonus(this.game,this.ord));
        }
    }

    private void CaseSpecial(){
        for(Piege p:this.pieges){
            p.addToGraphics();
        }
        for(Mur m:this.murs){
            m.addToGraphics();
        }
        for(Bonus b:this.bonus){
            b.AddtoGraphics();
        }
        for(Glissade g:this.glissades){
            g.addToGraphics();
        }
    }

    public boolean avoir_bonus(Case pos){
        for(Bonus B:this.bonus){
            if(B.coverCase(pos)){
                B.ajoute();
                return true;
            }
        }
        return false;
    }
    public boolean get_block(Case pos){
        for(Mur m:this.murs){

            if(m.coversCase(pos)){
                return true;

            }
        }

        return false;
    }
    public boolean glissante(Case pos){
        for(Glissade g:this.glissades){
            if(g.coverCase(pos))
                //System.out.println("gg");

                return true;
        }
        return false;
    }

    public void addToGraphics() {
        for(int i = 0; i < this.game.width; i++) {
            this.game.getGraphic().add(new Element(i, this.ord-this.game.getfrog().getScore(), Block.lane));
        }
    }
}

