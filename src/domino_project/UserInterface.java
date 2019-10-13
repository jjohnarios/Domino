/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino_project;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class UserInterface {

    static Scanner input = new Scanner(System.in);

    static public boolean askForReverse() {
        System.out.println(" Reversed? (YES=1/NO=0)");
        
        int choice = input.nextInt();
        while (choice != 1 && choice != 0) {
            System.out.println("Wrong input");
            System.out.println(" Reversed? (YES=1/NO=0)");
            choice = input.nextInt();
        }
        return choice==1;
        
    }

    public UserInterface() {
        //input=new Scanner(System.in);
    }

    public void showTile(Tile tile) {
        System.out.print("[" + tile.getT1() + "|" + tile.getT2() + "]");
    }

    public void showSetOfTiles(SetOfTiles set) {
        for (int i = 0; i < set.getSize(); i++) {
            showTile(set.getTileByIndex(i));
        }
    }

    public void showPanel(PanelOfTiles panel) {
        for (int i = 0; i < panel.getNumberOfRows(); i++) {
            System.out.print((i + 1) + ". ");
            showSetOfTiles(panel.getSetOfRow(i));
            System.out.println();
        }
    }

    public void createWhiteSpaces(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    public int readTileFromPanel() {
        System.out.println("You can pick a Tile.Give the number of its row (1-4)");

        int choice = input.nextInt();
        while (!(choice > 0 && choice < 5)) {

            showInvalidInput();
            System.out.println("You can pick a Tile.Give the number of its row (1-4)");
            choice = input.nextInt();
        }
        return choice - 1;
    }

    public void showInvalidInput() {
        System.out.println("Invalid input , please try again ");
    }

    public static int giveChoice() {

        System.out.println("Pick the side you want to place your Tile(1 or 2).");
        System.out.println("1.On the beggining ");
        System.out.println("2.On the end ");
        int choice = input.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Pick the side you want to place your Tile(1 or 2).");
            System.out.println("1.On the beggining ");
            System.out.println("2.On the end ");
            choice = input.nextInt();
        }
        return choice;

    }

    public int readFromHand() {
        System.out.println();
        System.out.println("Pick the Tile you want to play");
        int pos = input.nextInt();
        return pos;
    }

    public void showHand(SetOfTiles hand) {
        for (int i = 0; i < hand.getSize(); i++) {
            System.out.print(" " + i + ")");
            this.showTile(hand.getTileByIndex(i));
        }
    }

    public void playerIsPlaying(String p) {
        System.out.println("Player " + p + " is playing:");
    }

    public void showLine(DominoLine line) {
        createWhiteSpaces(1);
        System.out.println("Line:");
        this.showSetOfTiles(line.getSetOfTiles());
    }
    
    public void showCloseSet(int ammount){
        createWhiteSpaces(1);
        System.out.println("Stack : |"+ammount+"|");
    }
     public void selectFromSet(String name){
        System.out.println(name+" : took tile from set");
    }
    
    public void newRound() {
        System.out.println("NEW ROUND");
    }

    public void showPoints(String player, int points) {
        System.out.println("Player " + player + " : " + points + " points");
    }

    public void welcome(MultiplayerGame game){
        if(game instanceof HungarianGame){
            welcomeToHungarianGame();
        }
        if(game instanceof CardinalGame){
            welcomeToCardinalGame();
        }
    }
    public void welcomeToSolo1() {
        System.out.println("Welcome to SOLO1 type of DominoGame.");
        System.out.println("Enjoy the game and have fun!!");
        this.createWhiteSpaces(2);
    }

    private void welcomeToHungarianGame() {
        System.out.println("Welcome to HUNGARIAN type of DominoGame.");
        System.out.println("Enjoy the game and have fun!!");
        this.createWhiteSpaces(1);
    }
    
    private void welcomeToCardinalGame(){
        System.out.println("Welcome to CARDINAL type of DominoGame.");
        System.out.println("Enjoy the game and have fun!!");
        this.createWhiteSpaces(1);
    }

    public static int pickDominoTypeToPlay() {
        System.out.println("Choose a type of DominoGame to play.");
        System.out.println("1.Solo1");
        System.out.println("2.Hungarian");
        System.out.println("3.Cardinal");
        int choice = input.nextInt();

        return choice;
    }

    public String askName() {
        createWhiteSpaces(1);
        System.out.println("Name : ");
        input.nextLine();
        String name = input.nextLine();
        return name;
    }
    
    public boolean wantToDraw(){
        createWhiteSpaces(1);
        System.out.println("Draw Tile? (YES:1/NO:0)");
        int choice=input.nextInt();
        while (choice != 1 && choice != 0) {
            System.out.println("Wrong input");
            System.out.println(" Draw Tile? (YES=1/NO=0)");
            choice = input.nextInt();
        }
        return choice==1;
    }
    
    public int chooseNumberOfPlayers(){
        createWhiteSpaces(1);
        System.out.println("How many Players?(2,3,4)");
        int choice=input.nextInt();
        while(choice<2 || choice>4){
            showInvalidInput();                                                                            
            System.out.println("Choose number of players in [2,4]");
            choice=input.nextInt();
        }
        return choice;
    }
    

}
