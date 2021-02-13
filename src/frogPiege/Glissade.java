package frogPiege;


import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;
import org.ietf.jgss.GSSManager;
import java.awt.*;

public class Glissade {
    private Game game;
    private Case pos;

    public Glissade(Game game,int y){
        this.game=game;
        this.pos=new Case(this.game.randomGen.nextInt(this.game.width),y);
    }
    public void addToGraphics(){
        int sc=this.game.getfrog().getScore();
        this.game.getGraphic().add(new Element(this.pos.absc,this.pos.ord-sc, Block.glissade));
    }

    boolean coverCase(Case pos){
        //System.out.println(this.pos.ord+" grounuille "+(this.game.getfrog().getScore()+1));
        return pos.absc==this.pos.absc&&this.pos.ord==this.game.getfrog().getScore()+1;}
}