/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino_project;

import Gui.CardinalGui;
import Gui.HungarianGui;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class CardinalGame extends MultiplayerGame {

    
    
    public CardinalGame(int num) {
        super(num);
        board = new BoardCardinal();
    }

    public void game() {
        //gui.welcome(this);
        int amount;//plh8os tiles pou dinontai stous paiktes
        if (players.size() <= 2) {
            amount = 7;
        } else {
            amount = 5;
        }

        while (players.get(this.getMaxPos()).GetPoints() < 100) {
            fillObjects(amount);

            roundloop();

            handlePointsOfWinner();

            showBoardNewRound();

            ClearObjects();
        }

          ((CardinalGui)gui).showWinner( players.get(this.getMaxPos()).getName());

    }

    public void roundloop() {

        order();
        boolean p[] = new boolean[players.size()];
        boolean done;
        for (int i = 0; i < p.length; i++) {
            p[i] = true;
        }
        done = true;
        while (done) {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i) instanceof Human) {
                    p[i] = humanMakesMove();
                } else {
                    p[i] = botMakesMove(i);
                }

                if (players.get(i).getHand().isEmpty()) {
                    return;
                }
                done = false;
                for (boolean a : p) {
                    done = done || a;
                }
            }

        }

    }

    @Override
    protected void handlePointsOfWinner() {
        int pos = getminHandPoint();
        int sum = 0;
        for (int i = 0; i < players.size(); i++) {
            if (i == pos) {
                sum -= players.get(i).getHandpoints();
            } else {
                sum += players.get(i).getHandpoints();
            }

        }
        //sthn isopalia pairnoun kai oi duo ;
        for (int i = 0; i < players.size(); i++) {
            if (i == pos || players.get(i).getHandpoints() == players.get(pos).getHandpoints()) {
                players.get(i).addPoints(sum);
            }

        }

    }

    private boolean humanMakesMove() {
        boolean done = false;
        ((CardinalGui)(gui)).setDrawButtonFalse();
        if (board.getLine().isEmpty()) {

            done = true;
            // gui.createWhiteSpaces(1);
            // System.out.println(" Your turn    ");
            ((CardinalGui) (gui)).showTurn();
            ((CardinalGui) (gui)).showLine(board.getLine().getSetOfTiles());
            ((CardinalGui) (gui)).showCloseSet(board.set.getSize());
            //gui.createWhiteSpaces(1);
            ((CardinalGui) (gui)).showHand(players.get(getHuman()).getHand());
            askForTileFromSet();
            humanMove();
            // gui.createWhiteSpaces(1);
            ((CardinalGui) (gui)).showLine(board.getLine().getSetOfTiles());
            // gui.createWhiteSpaces(2);
            //showMoveOfHuman();
        } else {
            // gui.createWhiteSpaces(1);
            //System.out.println(" Your turn    ");
            ((CardinalGui) (gui)).showTurn();
            //gui.createWhiteSpaces(1);
            ((CardinalGui) (gui)).showLine(board.getLine().getSetOfTiles());
            boolean able = isPlayerAbleToPlay(players.get(getHuman()));
            while (!able && board.set.getSize() > 2) {
                ((CardinalGui) (gui)).showCloseSet(board.set.getSize());
                // gui.createWhiteSpaces(1);
                ((CardinalGui) (gui)).showHand(players.get(getHuman()).getHand());
                //System.out.println("\n travas karta giai den mporeis na pai3eis \n");
                ((CardinalGui) (gui)).showDrawCard();
                players.get(getHuman()).getHand().addTile(board.set.getLastTile());
                board.set.removeLastTile();
                able = isPlayerAbleToPlay(players.get(getHuman()));
            }

            if (able) {
                done = true;
                ((CardinalGui) (gui)).showCloseSet(board.set.getSize());
                // gui.createWhiteSpaces(1);
                ((CardinalGui) (gui)).showHand(players.get(getHuman()).getHand());
                askForTileFromSet();
                humanMove();
                //gui.createWhiteSpaces(1);
                ((CardinalGui) (gui)).showLine(board.getLine().getSetOfTiles());
                // gui.createWhiteSpaces(2);
            }

        }

        return done;
    }

    private void humanMove() {
        int x = getPosOfTileByPlayer();
        Tile tile = players.get(getHuman()).getHand().getTileByIndex(x);
        if (isBalader(tile)) {
            int choice = ((CardinalGui) (gui)).giveChoice();
            if (tile.getSum() != 0) {//Otan einai to 0,0 den yparxei nohma antistrofhs
                boolean reverse = ((CardinalGui) (gui)).askForReverse();
                if (reverse) {
                    tile.reverseTile();
                }
            }
            board.placeTileToLine(tile, choice, board.line);
            players.get(getHuman()).removeTileByIndex(x);
        } else {
            int fits = board.getLine().fitsOnBeginning(players.get(getHuman()).getTileByIndex(x));
            int fits2 = board.getLine().fitsAtTheEnd(players.get(getHuman()).getTileByIndex(x));
            int choice = 1;
            if (fits > 0 && fits2 > 0) {
                // 8a ginei h 1 h 2
                choice = ((CardinalGui)(gui)).giveChoice();
            }
            board.placeTileToLine(players.get(getHuman()).getTileByIndex(x), choice, board.line);

            players.get(getHuman()).removeTileByIndex(x);
        }
        ((CardinalGui) (gui)).showHand(players.get(getHuman()).getHand());
    }

    private void askForTileFromSet() {
        int ask;
        do {
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
            }
            ask = ((CardinalGui) (gui)).wantToDraw();
        } while (ask == -1);//oso einai -1 perimene na se apanthsei 1 h 0

        while (ask == 1) {
            if (board.set.getSize() > 2) {
                players.get(getHuman()).getHand().addTile(board.set.getLastTile());
                board.set.removeLastTile();
               ((CardinalGui) (gui)).showCloseSet(board.set.getSize());
                ((CardinalGui) (gui)).selectFromSet(players.get(getHuman()).getName());
                ((CardinalGui) (gui)).showHand(players.get(getHuman()).getHand());
                // gui.createWhiteSpaces(1);
                do {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException ex) {
                    }
                    ask = ((CardinalGui) (gui)).wantToDraw();
                } while (ask == -1);//oso einai -1 perimene na se apanthsei 1 h 0
            } else {
                break;
            }

        }
    }

    private boolean isBalader(Tile tile) {
        return (tile.getSum() == 7 || tile.getSum() == 0);
    }

    private boolean botMakesMove(int i) {
        boolean done = false;
        if (board.getLine().isEmpty()) {
            done = true;
            //askForTileFromSet();

            int pos = botPickTile((Bot) players.get(i));
            board.placeTileToLine(players.get(i).getTileByIndex(pos), 1, board.line);
            players.get(i).removeTileByIndex(pos);

            showMoveOfBot(i);
        } else {
            boolean able = isPlayerAbleToPlay(players.get(i));
            while (!able && board.set.getSize() > 2) {
                ((CardinalGui) (gui)).selectFromSet(players.get(i).getName());

                players.get(i).getHand().addTile(board.set.getLastTile());
                board.set.removeLastTile();
                //((CardinalGui) (gui)).showHand(players.get(i).getHand());
                //gui.createWhiteSpaces(1);
                able = isPlayerAbleToPlay(players.get(i));
            }

            if (able) {
                //System.out.println("\n "+players.get(i).getName()+" is able to play\n");
                ((CardinalGui) (gui)).showAbleToPlay(players.get(i).getName());
                done = true;

                int pos = botPickTile((Bot) players.get(i));
                board.placeTileToLine(players.get(i).getTileByIndex(pos), 1, board.line);
                players.get(i).removeTileByIndex(pos);
                showMoveOfBot(i);

            }
        }

        return done;
    }

    private int botPickTile(Bot bot) {
        for (int i = 0; i < bot.getHand().getSize(); i++) {
            if (((BoardCardinal) board).checkIfFits(bot.getTileByIndex(i))) {
                return i;
            }
        }
        return -1;
    }

    private void showMoveOfBot(int pos) {
        //gui.createWhiteSpaces(1);
        ((CardinalGui) (gui)).playerIsPlaying(players.get(pos).getName());
        //((CardinalGui)(gui)).showHand(players.get(pos).getHand());   
        ((CardinalGui) (gui)).showLine(board.getLine().getSetOfTiles());
        //gui.createWhiteSpaces(1);
        ((CardinalGui) (gui)).showCloseSet(board.set.getSize());
        // gui.createWhiteSpaces(2);

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(HungarianGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //cui.showHand(players.get(i).getHand());
    }

    private void showMoveOfHuman() {
        int pos = getHuman();
        //gui.createWhiteSpaces(1);
        ((CardinalGui) (gui)).showLine(board.getLine().getSetOfTiles());
        //gui.createWhiteSpaces(1);
        ((CardinalGui) (gui)).showCloseSet(board.set.getSize());
        //gui.createWhiteSpaces(2);
        ((CardinalGui) (gui)).playerIsPlaying(players.get(pos).getName());
        ((CardinalGui) (gui)).showHand(players.get(pos).getHand());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(HungarianGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //cui.showHand(players.get(i).getHand());
    }

    private void showBoardNewRound() {
        //gui.createWhiteSpaces(3);
        ((CardinalGui) (gui)).newRound();
        //gui.createWhiteSpaces(3);
        for (int i = 0; i < players.size(); i++) {
            ((CardinalGui) (gui)).showPoints(players);
        }

    }

    @Override
    protected int getPosOfTileByPlayer() {
        int pos;
        //((CardinalGui) (gui)).setFalseButtonPressed();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        pos = ((CardinalGui) (gui)).readFromHand();
        while (pos == -1 || !((BoardCardinal) board).checkIfFits(players.get(getHuman()).getTileByIndex(pos))) {
            if (pos != -1) {
                ((CardinalGui) (gui)).showInvalidInput();
            }
            ((CardinalGui) (gui)).setFalseButtonPressed();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
            }
            pos = ((CardinalGui) (gui)).readFromHand();
        }
        return pos;
    }

    @Override
    protected boolean isPlayerAbleToPlay(Player p) {
        if (p.getHand().isEmpty()) {
            return false;
        }
        for (int i = 0; i < p.getHand().getSize(); i++) {
            if (((BoardCardinal) board).checkIfFits(p.getHand().getTileByIndex(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        game();
    }
}
