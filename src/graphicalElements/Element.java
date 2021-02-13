package graphicalElements;

import java.awt.*;

import gameCommons.Block;
import gameCommons.Case;


public class Element extends Case {

    public Block block;
    public Element(int absc, int ord, Block color) {
        super(absc, ord);
        this.block = color;
    }

    public Element(Case c, Block color) {
        super(c.absc, c.ord);
        this.block = color;
    }

    
}
