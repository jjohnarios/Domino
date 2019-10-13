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
public class Human implements Player{
    private SetOfTiles hand;
    private int points;
    private String name;
    
   public Human(String name){
        hand=new SetOfTiles();
        points=0;
        this.name=name;
    }
    
    
    @Override
    public void fillhand(SetOfTiles set){
        hand=set;
    }
    
    public String getName(){
        return name;
    }
    public void ClearHand(){
        hand=new SetOfTiles();
    }
    
    public int  getHandpoints(){
        int sum=0;
        for(int i=0;i<hand.getSize();i++){
//            sum+=hand.getTileByIndex(i).getT1();
//            sum+=hand.getTileByIndex(i).getT2();
                sum += hand.getTileByIndex(i).getSum();
        }
        return sum;
    }
    
    public int getHighiestDoubleTile(){
        int maxDouble=-1;
//        for(int i=0;i<hand.getSize();i++){
//            if(hand.getTileByIndex(i).getT1()==hand.getTileByIndex(i).getT2()){
//                if(max<hand.getTileByIndex(i).getT1()){
//                    max=hand.getTileByIndex(i).getT1();
//                }
//            }
//        }
//        return max;
        for(int i=0;i<hand.getSize();i++){
            if(hand.getTileByIndex(i).isDouble()){
                if(maxDouble<hand.getTileByIndex(i).getDouble()){
                    maxDouble=hand.getTileByIndex(i).getDouble();
                }
            }
        }
        return maxDouble;
    }
     public void addPoints(int x){
       this.points+=x;
    }
    
    
    public int GetPoints(){
        return points;
    }
    
    public boolean removeTileByIndex(int x){
        return hand.removeByIndex(x);
    }
    
    public Tile getTileByIndex(int x){
        return hand.getTileByIndex(x);
    }
    
    
    
    public boolean handIsEmpty(){
        return hand.isEmpty();
    }
    public SetOfTiles getHand(){
        return hand;
    }
    
    
    
    
}
