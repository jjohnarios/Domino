/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino_project;

import Gui.CardinalGui;
import Gui.HungarianGui;
import Gui.MultiplayerGui;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public abstract class MultiplayerGame implements Runnable {

    protected MultiplayerGui gui;
    protected ArrayList<Player> players;
    protected MultiplayerBoard board;

    public MultiplayerGame(int num) {
         int numberOfPlayers;
         
        
        numberOfPlayers=num;
        players = new ArrayList<>();
        if(this instanceof HungarianGame){
            gui=new HungarianGui();
            ((HungarianGui)gui).setVisible(true);
        }else{
           gui=new CardinalGui();
            ((CardinalGui)gui).setVisible(true);
           //CARDINAL KANTO
        }
        String name;
        if(this instanceof HungarianGame){
            name = ((HungarianGui)(gui)).askName();
        }else{
           name = ((CardinalGui)(gui)).askName();
           
        }
        
        players.add(new Human(name));
        for (int i = 1; i < numberOfPlayers; i++) {
            players.add(new Bot("Bot " + i));
        }

    }

    protected void order() {
        int pos = 0;
        int max = -1;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getHighiestDoubleTile() > max) {
                max = players.get(i).getHighiestDoubleTile();
                pos = i;
                
            }
        }
        ArrayList<Player> a=new ArrayList<>();
        for (int i = 0; i < pos; i++) {
            a.add(players.get(i));
        }
        for(int i=0;i<pos;i++){
            players.remove(a.get(i));
        }
        for(int i=0;i<pos;i++){
            players.add(a.get(i));
        }
       
    }

    protected void fillObjects(int amount) {
        board.fillBoard();

        for (int i = 0; i < players.size(); i++) {
            players.get(i).fillhand(board.giveLastTiles(amount));
        }

    }
    
      protected void ClearObjects() {
        board.clearBoard();
        for(int i=0;i<players.size();i++){
           players.get(i).ClearHand();
       }
    }
    
     protected int getHuman(){
        for(int i=0;i<players.size();i++){
            if(players.get(i) instanceof Human) return i;
        }
        return 0;
    }
     
     protected int getminHandPoint(){
        int pos=0;
        int min=300;
         for(int i=0;i<players.size();i++){
           if(players.get(i).getHandpoints()<min){
               min = players.get(i).getHandpoints();
               pos=i;
           }
       }
         return pos;
    }
     
      protected int getMaxPos(){
        int pos=0;
        int max=0;
         for(int i=0;i<players.size();i++){
           if(players.get(i).GetPoints()>max){
               max = players.get(i).GetPoints();
               pos=i;
           }
       }
         return pos;
    }
      
      protected boolean isPlayerAbleToPlay(Player p){
          if(p.getHand().isEmpty()){
              return false;
          }
          for(int i=0;i<p.getHand().getSize();i++){
              if(board.checkIfFits(p.getHand().getTileByIndex(i))){
                  return true;
              }
          }
          return false;
      }
      
      protected int getPosOfTileByPlayer() {
        if(this instanceof HungarianGame){
            ((HungarianGui)(gui)).setFalseButtonPressed();
        }else{
           ((CardinalGui)(gui)).setFalseButtonPressed();
           //CARDINAL KANTO
        }
          
        try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                
            }
        int pos;
        if(this instanceof HungarianGame){
                 pos = ((HungarianGui)(gui)).readFromHand();
            }else{
                 pos = ((CardinalGui)(gui)).readFromHand();
                //CARDINAL KANTO
            }
        
        
        while (pos==-1 || !board.checkIfFits( players.get(getHuman()).getTileByIndex(pos))) {
           
            if(pos!=-1){
                gui.showInvalidInput();
            }
            if(this instanceof HungarianGame){
                ((HungarianGui)(gui)).setFalseButtonPressed();
            }else{
                ((CardinalGui)(gui)).setFalseButtonPressed();
                //CARDINAL KANTO
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
            if(this instanceof HungarianGame){
                 pos = ((HungarianGui)(gui)).readFromHand();
            }else{
                 pos = ((CardinalGui)(gui)).readFromHand();
                //CARDINAL KANTO
            }
        }
        return pos;
    }
      
     protected abstract void handlePointsOfWinner();
     
}
