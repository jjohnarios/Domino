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
public interface DominoLine {
    
    public void addTileOnBeginning(Tile tile);
    public void addTileAtTheEnd(Tile tile);
    public short fitsOnBeginning(Tile tile);
    public short fitsAtTheEnd(Tile tile);
    public boolean isEmpty();
    public SetOfTiles getSetOfTiles();
    public void Clear();
    
    
}
