/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino_project;

/**
 *
 * @author Apostolos
 */
public interface Player {
    
    
    public void fillhand(SetOfTiles set); 
    public void ClearHand();
    public int  getHandpoints();
     public int getHighiestDoubleTile();
     public int GetPoints();
     public boolean removeTileByIndex(int x);
     public Tile getTileByIndex(int x);
     public boolean handIsEmpty();
      public void addPoints(int x);
      public SetOfTiles getHand();
      public String getName();
}
