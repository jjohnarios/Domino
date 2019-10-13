/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino_project;

/**
 *
 * @author User
 */
public class BasicDominoLine extends SetOfTiles implements DominoLine {

    //private SetOfTiles line;

  
    

    public void addTileOnBeginning(Tile tile) {
        addTileByIndex(tile, 0);
    }

    public void addTileAtTheEnd(Tile tile) {
        addTile(tile);
    }

    

    

    public short fitsOnBeginning(Tile tile) {
        if (isEmpty()) {
            return 1;
        }
        return tile.matchSideOfTile(this.getFirstTile().getT1());
    }

    public short fitsAtTheEnd(Tile tile) {
        if (isEmpty()) {
            return 0;
        }
        return tile.matchSideOfTile(this.getLastTile().getT2());
    }

    public SetOfTiles getSetOfTiles() {
        return this;
    }

    @Override
    public boolean isEmpty(){
        return super.isEmpty();
    }

    @Override
    public void Clear() {
        for(int i=this.getSize()-1;i>=0;i--){
            this.removeByIndex(i);
        }
    }

}
