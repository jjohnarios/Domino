/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino_project;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author User
 */
public class SetOfTiles {

    private ArrayList<Tile> tileSet;

    public SetOfTiles() {
        tileSet = new ArrayList<>();
    }

    public boolean addTile(Tile tile) {
        return tileSet.add(tile);

    }

    public void addTileByIndex(Tile tile, int i) {
        tileSet.add(i, tile);
    }

    public boolean isEmpty() {
        return tileSet.isEmpty();
    }

    public boolean removeLastTile() {
        if (tileSet.size() >= 1) {

            tileSet.remove(tileSet.size() - 1);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeByIndex(int i) {
        if (tileSet.size() >= i) {

            tileSet.remove(i);
            return true;
        } else {
            return false;
        }
    }

    public Tile getLastTile() {

        return tileSet.get(tileSet.size() - 1);

    }

    public Tile getFirstTile() {

        return tileSet.get(0);

    }

    public Tile getTileByIndex(int i) {
        return tileSet.get(i);
    }

    private void shuffleAll() {
        Collections.shuffle(tileSet);
    }

    public void fillSet28() {

        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                tileSet.add(new Tile(i, j));
            }
        }
        this.shuffleAll();
    }

    public int getSize() {
        return tileSet.size();
    }

}
