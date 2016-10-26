package battleship;

import java.util.Scanner;
import java.util.List;
import static java.util.Arrays.asList;
/**
 *
 * @author Radwane
 */
public class BattleShip {
        
    Scanner input;
    final int BOARDSIZE = 10;
    //String[] SHIP = new String[]{"Carrier : 5" , "Battleship : 4" , "Cruiser : 3", "Submarine : 3", "Destroyer : 2"};
    List SHIP = asList("Carrier :5:1","Battleship :4:2","Cruiser :3:3","Submarine :3:4","Destroyer :2:5");
    int[][] board = new int[10][10];
    
    public static void main(String[] args) {
        BattleShip method = new BattleShip() ;  
        System.out.println("Welcome to the game BattleShip (v0.1)"+"\n");
        method.input = new Scanner(System.in);
        method.initialiseBoard();
    }  
    
    public void displayBoard(){
        
        for (int[] board1 : board) {
            for (int column = 0; column < board.length; column++) {
                board1[column] = 0; 
            }   
        }
        
        System.out.println("\n \tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ ");   
        for(int row = 0 ; row < board.length ; row++ ){
            System.out.print((row+1)+"");
            for(int column = 0 ; column < board.length ; column++ ){
                if(board[row][column] == 0){
                    System.out.print("\t"+"[ ]");
                }
            }
            System.out.println();
        }
    }
    
    public void initialiseBoard(){
        BattleShip method = new BattleShip() ; 
        System.out.println("You will begin with 5 Ships: \n\n1. Carrier, Size: 5"+
            " \n2. Battleship, Size: 4 \n3. Cruiser, Size: 3\n4. Submarine, Size 3 \n5. Destroyer, Size: 2\n");
        method.displayBoard();
        for (Object SHIP1 : SHIP) {
            String ship = SHIP1.toString();
            System.out.print("\nWhat co-ordinates do you want to place your "+ship.split(":",2)[0]+"(xy): ");
            String coordinates = input.next().toLowerCase();
            while (method.checkMoveIsLegal(coordinates) == false){
                System.out.print("\nYou have entered an invalid co-ordinate to place the "+ship.split(":",2)[0]+" (xy): ");
                coordinates = input.next().toLowerCase();
                method.checkMoveIsLegal(coordinates);
            }
            System.out.print("Do you want to place it (u)p, (d)own, (l)eft or (r)ight: ");
            String position = input.next().toLowerCase();
            while (!position.contains("u")&&!position.contains("d")&&!position.contains("l")&&!position.contains("r")){
                System.out.print("Please type 'u' for up, 'd' for down, 'l' for left, 'r' for right: ");
                position = input.next().toLowerCase();
            }
            while ((method.wouldItFit(coordinates, Integer.parseInt(ship.split(":",3)[1]), position) == false)){
                System.out.print("Your ship will not fit, do you want to place it (u)p, (d)own, (l)eft or (r)ight: ");
                position = input.next().toLowerCase();
                while (!position.contains("u")&&!position.contains("d")&&!position.contains("l")&&!position.contains("r")){
                    System.out.print("Please type 'u' for up, 'd' for down, 'l' for left, 'r' for right: ");
                    position = input.next().toLowerCase();  
                }
            }
            System.out.println(SHIP1);
            method.displayBoard();
        }
            
    }
    
    public boolean wouldItFit(String coordinates, int size, String position){
        BattleShip method = new BattleShip() ;
        int x = (int)(coordinates.charAt(0))-96;
        int y = Integer.parseInt(coordinates.substring(1));
        int end = 5;
        boolean taken = false;
        switch (position){
            case "u":
               end = y - (size-1);
                break;
            case "d":
                end = y + (size-1);    
               break;
            case "l":
                end = x - (size-1);
                break;
            case "r":
                end = x + size-1;
                for (int i = 0; i<11; i++){
                     System.out.println();
                }
                break;
            default:
                break; 
        } 
        return (end > 0 && end <= 10 && method.isItTaken(x,y));
    }
    
    
    public boolean isItTaken(int x, int y){
        boolean empty = false;
        if (board[x-1][y-1] == 0){
            empty = true;
        } 
        return empty;
    }
       
    public boolean checkMoveIsLegal(String coordinates){
       
        String x = String.valueOf(coordinates.charAt(0));
        String y = coordinates.substring(1);
        return ((coordinates.length() == 2 || coordinates.length() == 3 ) 
                && y.matches("[1-9]")|| y.contains("10") && x.matches ("[a-j]"));
    }
}

