package frogPiege;

import environment.Environment;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import environment.Lane;
import gameCommons.*;

public class EnvPiege implements IEnvironment {
    //les attributs
    private ArrayList<LanePiege> roadLines;
    protected Game game;
    private ArrayList<Mur> murs;

    public EnvPiege(Game game) {
        //super(game);
        this.game = game;
        this.roadLines = new ArrayList();
        this.roadLines.add(new LanePiege(game, 0, 0.0D));

        for(int i = 1; i < game.height - 1; ++i) {
            this.roadLines.add(new LanePiege(game, i));
        }

        this.roadLines.add(new LanePiege(game, game.height - 1, 0.0D));
//		for(LaneInf l:this.roadLines){
//			this.murs.addAll(l.getMurs());
//		}
    }
    public boolean get_block(Case var1) {
        for(LanePiege l:this.roadLines){
            if(l.get_block(var1))
                return true;
        }
        return false;
    }

    public boolean get_bonus(Case var1){
        for(LanePiege l:this.roadLines) {
            if(l.avoir_bonus(var1))
                return true;
        }
        return false;
    }

    public boolean glissantt(Case pos){

        for(LanePiege l:this.roadLines) {
            if(l.glissante(pos))
                return true;
        }
        return false;
    }

    public void addVoies(){
        if(this.roadLines.size() - this.game.getfrog().getScore() < this.game.height+1){
            LanePiege voie = new LanePiege(this.game, this.roadLines.size());
            this.roadLines.add(voie);

        }
    }

//
//	public void MurBlock(){
//			for(Mur mur:this.murs){
//				Case c=new Case(this.game.getfrog().getPosition().absc+1,this.game.getfrog().getPosition().ord);
//				Case r=new Case(this.game.getfrog().getPosition().absc,this.game.getfrog().getPosition().ord+1);
//			Case l=new Case(this.game.getfrog().getPosition().absc,this.game.getfrog().getPosition().ord-1);
//			if(!mur.IsCanMove(c)){
//				this.game.getfrog().canNotMove(Direction.up);
//			}else if(!mur.IsCanMove(r)){
//				this.game.getfrog().canNotMove(Direction.right);
//			}else if(!mur.IsCanMove(l)){
//				this.game.getfrog().canNotMove(Direction.left);
//			}
//		}
//	}

    public void update() {
        addVoies();
        //MurBlock();
        for(LanePiege v : this.roadLines) {
            v.update();
        }

    }

    public boolean isSafe(Case c){
        Case fg = new Case(c.absc, c.ord+this.game.getfrog().getScore());
        LanePiege v = this.roadLines.get(fg.ord);
        return v.isSafeFrog(fg);
    }
    //TODO
    @Override
    public boolean isWinningPosition(Case c) {throw new RuntimeException("not implemented");}



}