/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino_project;
import Gui.Solo1Gui;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User
 */
public class Solo1Game implements Runnable{

    private BoardSolo1 board;
   // private UserInterface cui;
    private Solo1Gui gui;
    private boolean victory=false;
    public Solo1Game(Solo1Gui gui) {
        
        this.gui = gui;
        //gui.setVisible(true);
        gui.setDefaultCloseOperation(gui.DISPOSE_ON_CLOSE);
        
        
        board = new BoardSolo1();
    }

    private int readTile() {
        int row;
        gui.setFalseButtonPressed();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Solo1Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        row = gui.readTileFromPanel();
        
        
         
        
        Tile tile= new Tile(11,11);
        boolean done;
        if (row!=-1 && board.getPanel().getLastOfRow(row) != null) {
            tile = board.getPanel().getLastOfRow(row);
            done = true;
        } else {
            done = false;
        }

        while (!done||  !board.checkIfFits(tile) ) {
            //cui.createWhiteSpaces(1); OXI GIA GUI
            
            if(row!=-1){
                gui.showInvalidInput();
            }
            
            
           
            gui.setFalseButtonPressed();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Solo1Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            row = gui.readTileFromPanel();
            
            
            if (row!=-1 && board.getPanel().getLastOfRow(row) != null) {
                tile = board.getPanel().getLastOfRow(row);
                done = true;
            } else {
                done = false;
            }

        }
        
        return row;
    }

    private void PlaceTile(Tile tile, int row) {
        int fits = board.getLine().fitsOnBeginning(tile);
        int fits2 = board.getLine().fitsAtTheEnd(tile);
        int choice = 1;
        if (fits > 0 && fits2 > 0) {
            // 8a ginei h 1 h 2
            choice = gui.giveChoice();
        }
        board.placeTileToLine(tile, choice);

        board.getPanel().removeLastOfRow(row);
    }

    private void showBoard() {
        gui.showPanel(board.getPanel());
        //cui.createWhiteSpaces(2); OXI GIA GUI
        if (!board.getLine().isEmpty()) {
            BasicDominoLine l= (BasicDominoLine) board.getLine();
            gui.showSetOfTiles(l.getSetOfTiles());
        }
        //cui.createWhiteSpaces(2);  OXI GIA GUI
    }

    public void gameLoop() {
        //System.out.println("Ga eLoop");
        gui.welcomeToSolo1(board.getPanel());  
        //gui.showPanel(board.getPanel());
        //cui.createWhiteSpaces(3);  OXI GIA GUI
        while (board.continuePlaying()) {
            //System.out.println("aaa");
            int row = readTile();
            Tile tile = board.getPanel().getLastOfRow(row);
            PlaceTile(tile, row);
            showBoard();

        }

        //epistrefei an nikhse h oxi 
        
            gui.showResult(board.getPanel().panelIsEmpty());
        
        
        
    }
    public PanelOfTiles getPanel(){
        return board.getPanel();
    }

    @Override
    public void run() {
        gameLoop();
    }
    
    
}
