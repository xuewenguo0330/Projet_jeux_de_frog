package frogPiege;


import gameCommons.Block;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import graphicalElements.Element;
import java.awt.*;

public class Mur {
    private Game game;
    private Case pos;
    private int length;

    public Mur(Game game,int y){
        this.game=game;
        this.pos=new Case(game.randomGen.nextInt((game.width)),y);
        this.length=game.randomGen.nextInt(3)+2;//+2 pour verifier longeur de mur
    }
    public void addToGraphics(){
        Color c=Color.ORANGE;
        int sr=this.game.getfrog().getScore();
        for(int i=0;i<this.length;i++){
            this.game.getGraphic().add(new Element(this.pos.absc+i,this.pos.ord-sr, Block.mur));
        }
    }

    public boolean coversCase(Case pos){

        if(this.pos.ord-1!=this.game.getfrog().getScore()+1){
            return false;
        }else{
            if(pos.absc>this.pos.absc &&pos.absc<this.pos.absc+this.length){
                System.out.println("grouneuille" +pos.absc+ "  "+this.pos.absc);
            }
            return pos.absc>=this.pos.absc &&pos.absc<this.pos.absc+this.length;

        }
    }
}