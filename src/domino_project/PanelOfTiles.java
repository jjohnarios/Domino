/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino_project;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PanelOfTiles {
    private ArrayList<SetOfTiles> panel;
    
    public PanelOfTiles(){
        SetOfTiles A= new SetOfTiles();
        A.fillSet28();
        panel=new ArrayList();
        for(int i=0;i<4;i++){
            panel.add(new SetOfTiles());
            for(int j=0;j<7;j++){
                panel.get(i).addTile(A.getTileByIndex(i*7+j));
            }
            
        }
    
    }
    
    public int getNumberOfRows(){
        return panel.size();
    }
    
    public Tile getLastOfRow(int row){
        if(panel.get(row).isEmpty()){
            return null;
        }
        return panel.get(row).getLastTile();
    }
    
    public boolean isRowEmpty(int row){
        return panel.get(row).isEmpty();
    }
    
    public void removeLastOfRow(int row){
        if(!panel.get(row).isEmpty()){
            panel.get(row).removeLastTile();
        }
    }
    
    public SetOfTiles getSetOfRow(int row){
        return panel.get(row);
    }
    
    public boolean panelIsEmpty(){
        boolean empty=true;
        for(int i=0;i<4 && empty==true;i++){
            empty=this.isRowEmpty(i);
        }
        return empty;
    }
    
}
