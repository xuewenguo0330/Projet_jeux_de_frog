package frogPiege;


import java.util.ArrayList;

import frogInf.CarInf;
import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;
import java.awt.Color;

public class Piege {
    private Game game;
    private Case piege_pos;

    public Piege(Game game,int pos_y){
        this.game=game;
        this.piege_pos=new Case(game.randomGen.nextInt(game.width),pos_y);
    }

    //    boolean coverCase(Case pos){return pos.absc==this.piege_pos.absc&& pos.ord==this.piege_pos.ord;}
//    boolean danger(){return this.nbPiege>1;}
//    void add_danger(){++this.nbPiege;}
//
    public Case getPiege_pos() {
        return piege_pos;
    }

    void addToGraphics(){
        int sc = this.game.getfrog().getScore();
        this.game.getGraphic().add(new Element(this.piege_pos.absc,this.piege_pos.ord-sc, Block.piege));
    }


}