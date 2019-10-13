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
public class Board {
    protected DominoLine line;
   
    
    
    public void placeTileToLine(Tile tile, int choice , DominoLine l) {
        int fits,fits2;
        if(l instanceof CardinalDominoLine ){
            fits = ((CardinalDominoLine)line).fitsOnBeginning(tile);
            fits2 =((CardinalDominoLine)line).fitsAtTheEnd(tile);
        }else {
             fits = ((BasicDominoLine)line).fitsOnBeginning(tile);
             fits2 =((BasicDominoLine)line).fitsAtTheEnd(tile);
        }
        
        if (fits > 0 && choice == 1) {
            switch (fits) {
                case 1:
                    tile.reverseTile();
                    line.addTileOnBeginning(tile);
                    break;
                case 2:
                    line.addTileOnBeginning(tile);

                    break;
            }
        } else {
            switch (fits2) {
                case 1:

                    line.addTileAtTheEnd(tile);

                    break;
                case 2:
                    tile.reverseTile();
                    line.addTileAtTheEnd(tile);

                    break;

            }
        }
    }
    
    public void placeTileToLine(Tile tile, int choice ) {
        int fits = line.fitsOnBeginning(tile);
        int fits2 = line.fitsAtTheEnd(tile);

        if (fits > 0 && choice == 1) {
            switch (fits) {
                case 1:
                    tile.reverseTile();
                    line.addTileOnBeginning(tile);
                    break;
                case 2:
                    line.addTileOnBeginning(tile);

                    break;
            }
        } else {
            switch (fits2) {
                case 1:

                    line.addTileAtTheEnd(tile);

                    break;
                case 2:
                    tile.reverseTile();
                    line.addTileAtTheEnd(tile);

                    break;

            }
        }
    }
    
    
    public boolean checkIfFits(Tile tile) {
        if (line.isEmpty()) {
            return true;
        }
        int fits = line.fitsOnBeginning(tile);
        switch (fits) {
            case 1:
                return true;
            case 2:
                return true;
            default:
                int fits2 = line.fitsAtTheEnd(tile);
                switch (fits2) {
                    case 1:
                        return true;
                    case 2:
                        return true;
                    default:
                        return false;
                }
        }
    }
    
    public DominoLine getLine() {
        return line;
    }
    
}
