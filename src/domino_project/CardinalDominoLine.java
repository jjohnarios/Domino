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
public class CardinalDominoLine extends SetOfTiles implements DominoLine{

    @Override
    public void addTileOnBeginning(Tile tile) {
        addTileByIndex(tile, 0);
    }

    @Override
    public void addTileAtTheEnd(Tile tile) {
        addTile(tile);
    }

    @Override
    public short fitsOnBeginning(Tile tile) {
       if(isEmpty()){
           return 1;
       }
       if(tile.getT1()+this.getFirstTile().getT1()==7  ){
           return 1;
       }
       else if(tile.getT2()+this.getFirstTile().getT1()== 7 || tile.getSum()==7 || tile.getSum()==0){
           return 2;
       }
       else{
           return 0;
       }
    }

    @Override
    public short fitsAtTheEnd(Tile tile) {
        if(isEmpty()){
            return 1;
        }
       if(tile.getT1()+this.getLastTile().getT2()==7 || tile.getSum()==7 || tile.getSum()==0){
           return 1;
       }
       else if(tile.getT2()+this.getLastTile().getT2()== 7){
           return 2;
       }
       else{
           return 0;
       }
    }
    
    @Override
    public boolean isEmpty(){
        return super.isEmpty();
    }
    public SetOfTiles getSetOfTiles() {
        return this;
    }

    @Override
    public void Clear() {
        for(int i=this.getSize()-1;i>=0;i--){
            this.removeByIndex(i);
        }
    }
    
}
