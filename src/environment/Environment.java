package environment;

import java.util.ArrayList;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.Case;

public class Environment implements IEnvironment {
    private ArrayList<Lane> roadLines;
    private Game game;

    public Environment(Game game) {
        this.game = game;
        this.roadLines = new ArrayList();
        this.roadLines.add(new Lane(game, 0, 0.0D));

        for(int i = 1; i < game.height - 1; ++i) {
            this.roadLines.add(new Lane(game, i));
        }

        this.roadLines.add(new Lane(game, game.height - 1, 0.0D));
    }

    /**
	 * Teste si une case est sure, c'est à dire que la grenouille peut s'y poser
	 * sans mourir
	 * 
	 * @param c   la case à tester
	 * @return vrai s'il n'y a pas danger
	 */
    public boolean isSafe(Case c){
        Lane lane=this.roadLines.get(c.ord);
        for(int i=0;i<lane.getCars().size();i++){
            Car car=lane.getCars().get(i);
            Case positionCar=car.getLeftPosition();
            if(c.ord ==positionCar.ord){
                if((c.absc >= positionCar.absc&&c.absc<=positionCar.absc+car.getLength()-1))
                    return false;
            }
        }
        return true;
    }

    public boolean isWinningPosition(Case c){
        return c.ord == this.game.height - 1;
    }

    public void update(){
        for(Lane v: this.roadLines){
            v.update();
        }
    }
    @Override
    public boolean get_block(Case var1) {throw new RuntimeException("not implemented");}
    public boolean get_bonus(Case var1){throw new RuntimeException("not implemented");}
    public boolean glissantt(Case pos){throw new RuntimeException("not implemented");}

}

