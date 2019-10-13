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
public class BoardSolo1 extends Board {

    private PanelOfTiles panel;
    

    public BoardSolo1() {
        super.line=new BasicDominoLine();
        panel = new PanelOfTiles();
    }

    public void putFromPanelToEndOFLine(int x, boolean reverse) {
        Tile tile = panel.getLastOfRow(x);
        if (reverse) {
            tile.reverseTile();
        }
        panel.removeLastOfRow(x);
        line.addTileAtTheEnd(tile);
    }

    public void putFromPanelToStartOFLine(int x, boolean reverse) {
        Tile tile = panel.getLastOfRow(x);
        if (reverse) {
            tile.reverseTile();
        }
        panel.removeLastOfRow(x);
        line.addTileOnBeginning(tile);
    }

    

  

    public boolean continuePlaying() {
        boolean state = false;
        for (int i = 0; i < 4 && state == false; i++) {
            if (!panel.isRowEmpty(i)) {
                state = checkIfFits(panel.getLastOfRow(i));
            }
        }
        return state;
    }

    public boolean checkVictory() {
        return panel.panelIsEmpty();
    }

    public PanelOfTiles getPanel() {
        return panel;
    }

}
