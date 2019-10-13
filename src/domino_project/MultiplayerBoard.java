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
public class MultiplayerBoard extends Board{
    
    protected SetOfTiles set;
    
    public MultiplayerBoard(){
        set=new SetOfTiles();
        
    }
    
    public void fillBoard(){
        set=new SetOfTiles();
        set.fillSet28();
    }
    public void clearBoard(){
         set=new SetOfTiles();
         line.Clear();
    }
    
    public SetOfTiles giveLastTiles(int x){
        SetOfTiles m=new SetOfTiles();
        for(int i=0;i<x && !set.isEmpty();i++){
            m.addTile(set.getLastTile());
            set.removeLastTile();
        }
        return m;
    }
    public void addTileOnBeggining(Tile tile1){
        line.addTileOnBeginning(tile1);
    }
    
    public void addTileAtTheEnd(Tile tile1){
        line.addTileAtTheEnd(tile1);
    }
   
}
