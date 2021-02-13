package frogPiege;

import frog.Frog;
import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class FrogPiege extends Frog implements IFrog {
    private int grade=0;
    protected int score = 0;
    //private boolean can=true;

    public FrogPiege(Game game) {
        super(game);
    }

    public int getScore(){ return this.score;}
    public int getGrade(){return this.grade;}
    public void Bonus(){this.grade+=100;}


    public void move(Direction key) {
        //try switch after
        if (key == Direction.up) {
            Case c=new Case(this.position.absc, 1);
            if(this.game.get_block(c)){
                return;
            }else{
                this.position = c;
                this.score++;
            }

        } else if (key == Direction.down && this.score > 0) {
            Case c=new Case(this.position.absc, 1);
            if(this.game.get_block(c)){
                return;
            }
            else {
                this.position = c;
                this.score--;
            }
        } else if (key == Direction.left && this.position.absc > 0) {
            Case c=new Case (this.position.absc - 1, this.position.ord);
            if(this.game.get_block(c)){return;}
            else {
                this.position = new Case(this.position.absc - 1, this.position.ord);}

        } else if (key == Direction.right && this.position.absc < this.game.width - 1) {
            Case c=new Case(this.position.absc + 1, this.position.ord);
            if(this.game.get_block(c)){return ;}
            else{
                this.position = c;}
        } else {
            System.out.println("la grenouille atteigne le bord ");
        }
        this.game.get_bonus(this.position);
        this.glissant();

    }
    public void glissant(){
        if (this.game.glissante(this.position)){
            this.move(Direction.up);
            this.move(Direction.up);
        }
    }

}