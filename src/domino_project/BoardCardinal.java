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
public class BoardCardinal extends MultiplayerBoard{
    
    public BoardCardinal(){
       
        line=new CardinalDominoLine();
    }
    
    
    @Override
    public boolean checkIfFits(Tile tile){
        if(line.isEmpty()){
            return true;
        }
        if(tile.getSum()==7 || tile.getSum()==0){
            return true;
        }
        int fits = ((CardinalDominoLine)line).fitsOnBeginning(tile);
        switch (fits) {
            case 1:
                return true;
            case 2:
                return true;
            default:
                int fits2 = ((CardinalDominoLine)line).fitsAtTheEnd(tile);
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
    
    
     
}
