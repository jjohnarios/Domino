/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino_project;

import Gui.HungarianGui;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class HungarianGame extends MultiplayerGame{

   
    

   public HungarianGame(int num) {
        super(num);
        
        board = new BoardHungarian();
        
        }
       
   
    
     
    private void roundloop() {
            order();
            boolean p[]= new boolean[players.size()];
            boolean done;
            for(int i=0 ;i<p.length;i++){
                p[i]=true;
             }
            done=true;
            while (done) {
                for(int i=0;i<players.size();i++){
                    if( players.get(i) instanceof Human ){
                        p[i] = humanMakesMove();
                        ((HungarianGui)(gui)).showHand(players.get(getHuman()).getHand());
                    }else{
                        p[i]= botMakesMove(i);
                    }
                    done=false;
                    
                }
                for(boolean a : p){
                        done = done || a;
                    }
            }
        

    }

   


    private void showBoardNewRound() {
        //gui.createWhiteSpaces(3);
       ((HungarianGui)(gui)).newRound();
       // gui.createWhiteSpaces(3);
        
        ((HungarianGui)(gui)).showPoints(players);
       
       
    }
    
   
    

    @Override
    protected void handlePointsOfWinner() {
        int pos=getminHandPoint();
        int sum=0;
        for(int i=0;i<players.size();i++){
            if(i==pos)continue;
            sum+=players.get(i).getHandpoints();
       }
       for(int i=0;i<players.size();i++){
           if(i==pos || players.get(i).getHandpoints() == players.get(pos).getHandpoints() ){
               players.get(i).addPoints(sum);
           }
                
         }
    
    }
    
   
    public void game() {
       // gui.welcome(this);
        
        while (players.get(this.getMaxPos()).GetPoints() < 100 ) {
            fillObjects(24/players.size());

            roundloop();

            handlePointsOfWinner();

            showBoardNewRound();

            ClearObjects();
        }

        ((HungarianGui)gui).showWinner( players.get(this.getMaxPos()).getName());
    }

    
    private void showMoveOfBot(int pos) {
       // gui.createWhiteSpaces(1);
        ((HungarianGui)(gui)).showLine((BasicDominoLine) board.getLine());
       // gui.createWhiteSpaces(2);
        ((HungarianGui)(gui)).playerIsPlaying(players.get(pos).getName());
         try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(HungarianGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        //cui.showHand(players.get(i).getHand());
    }

    private void showMoveOfPlayer() {
        //gui.createWhiteSpaces(1);
        ((HungarianGui)(gui)).showLine((BasicDominoLine) board.getLine());
       // gui.createWhiteSpaces(2);
        ((HungarianGui)(gui)).playerIsPlaying(players.get(getHuman()).getName());
        ((HungarianGui)(gui)).showHand(players.get(getHuman()).getHand());
    }

    private void humanMove() {

        int x = this.getPosOfTileByPlayer();

        int fits = board.getLine().fitsOnBeginning(players.get(getHuman()).getTileByIndex(x));
        int fits2 = board.getLine().fitsAtTheEnd(players.get(getHuman()).getTileByIndex(x));
        int choice = 1;
        if (fits > 0 && fits2 > 0) {
            // 8a ginei h 1 h 2
            choice = ((HungarianGui)(gui)).giveChoice();
        }
        board.placeTileToLine(players.get(getHuman()).getTileByIndex(x), choice, board.line);
        players.get(getHuman()).removeTileByIndex(x);
    }

    private boolean botMakesMove(int bot){
        boolean done = false;
        //showMoveOfBot(bot);
        if (board.getLine().isEmpty()) {
            done = true;
            showMoveOfBot(bot);
            //vazei aytomata to prwto tou
            board.placeTileToLine(players.get(bot).getTileByIndex(0), 1, board.line);
            players.get(bot).removeTileByIndex(0);

            
           
        }
        // prwta prepei na ginei cast alliws den vriskei tis me8odous ths setoftile pou kanoume extend
        BasicDominoLine l=(BasicDominoLine) board.getLine();
        boolean ableToMove=isPlayerAbleToPlay(players.get(bot));
        while (ableToMove) {
             showMoveOfBot(bot);
            done = true;
            int pos = ((Bot)players.get(bot)).pickTileToPlay(l.getFirstTile().getT1(), l.getLastTile().getT2());
            Tile tile = players.get(bot).getTileByIndex(pos);
            board.placeTileToLine(tile, 1, board.line);
            players.get(bot).removeTileByIndex(pos);
            ableToMove=isPlayerAbleToPlay(players.get(bot));
            
           
        }
        return done;
    }

    private boolean humanMakesMove() {
        boolean done = false;
       // showMoveOfPlayer();
        if (board.getLine().isEmpty()) {
            done = true;
                showMoveOfPlayer();
            humanMove();

            
        }
        boolean ableToMove=isPlayerAbleToPlay(players.get(getHuman()));
        while (ableToMove) {
            showMoveOfPlayer();
            done = true;
            humanMove();
            ableToMove=isPlayerAbleToPlay(players.get(getHuman()));
        }
        return done;
    }

    @Override
    public void run() {
        game();
    }

    
}
