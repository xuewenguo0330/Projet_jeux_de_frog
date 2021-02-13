package frogPiege;

import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;
import java.awt.*;

public class Bonus {
    private Game game;
    private Case pos;
    private boolean use=true;

    public Bonus(Game game, int y){
        this.game=game;
        this.pos=new Case(this.game.randomGen.nextInt(this.game.width),y);
    }
    public void AddtoGraphics(){
        int sr=this.game.getfrog().getScore();
        this.game.getGraphic().add(new Element(this.pos.absc,this.pos.ord-sr, Block.bonus));
    }
    public boolean coverCase(Case pos){
        return pos.absc==this.pos.absc&&pos.ord==this.game.getfrog().getScore();
    }

    public void ajoute(){this.use=false;}
}
