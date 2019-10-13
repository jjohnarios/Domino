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
public class Tile {

    private int t1;
    private int t2;

    public Tile(int t1, int t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public int getT1() {
        return t1;
    }

    public int getT2() {
        return t2;
    }

    public void reverseTile() {
        int temp = t1;
        t1 = t2;
        t2 = temp;
    }

    public short matchSideOfTile(int tile_num) {
        if (t1 == tile_num) {
            return 1;
        } else if (t2 == tile_num) {
            return 2;
        } else {
            return 0;
        }
    }

    public boolean equalTile(Tile tile) {
        int tile1 = tile.getT1();
        int tile2 = tile.getT2();
        return (tile1 == t1 && tile2 == t2) || (tile1 == t2 && tile2 == t1);
    }

    public boolean isDouble() {
        return (t1 == t2);
    }

    public int getDouble() {
        if (this.isDouble()) {
            return t1;
        } else {
            return -1;
        }
    }

    public int getSum() {
        return (t1 + t2);
    }

    public String toString() {
        StringBuilder sb=new StringBuilder();
        return sb.append("[ ").append(t1).append("|").append(t2).append(" ]").toString();
    }

}
