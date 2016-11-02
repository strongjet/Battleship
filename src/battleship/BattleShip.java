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
    List SHIP = asList("Carrier :5:1","Battleship :4:2","Cruiser :3:3","Submarine :3:4","Destroyer :2:5");
    static int[][] board = new int[10][10];
    
    public static void main(String[] args) {
        BattleShip method = new BattleShip() ;  
        System.out.println("Welcome to the game BattleShip (v0.1)"+"\n");
        method.input = new Scanner(System.in);
        method.createBoard();
        method.initialiseBoard();
    }  
    
    public void createBoard(){
            for (int[] board1 : board) {
                for (int column = 0; column < board.length; column++) {
                    board1[column] = 0; 
                }   
        }
    }

    public void displayBoard(){
        
        System.out.println("\n \tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ ");   
        for(int row = 0 ; row < board.length ; row++ ){
            System.out.print((row+1)+"");
            for(int column = 0 ; column < board.length ; column++ ){
                switch (board[row][column]) {
                    case 0:
                        System.out.print("\t"+"[  ]");
                        break;
                    case 1:
                        System.out.print("\t"+"[Ca]");
                        break;
                    case 2:
                        System.out.print("\t"+"[Bs]");
                        break;
                    case 3:
                        System.out.print("\t"+"[Cr]");
                        break;
                    case 4:
                        System.out.print("\t"+"[Sm]");
                        break;
                    case 5:
                        System.out.print("\t"+"[Ds]");
                        break;
                    default:
                        break;
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
        int x;
        int y;
        for (Object SHIP1 : SHIP) {
            String ship = SHIP1.toString();
            System.out.print("\nWhat co-ordinates do you want to place your "+ship.split(":",2)[0]+"of size "+ship.split(":")[1]+" (xy): ");
            String coordinates = input.next().toLowerCase();
            x = (int)(coordinates.charAt(0))-96;
            y = Integer.parseInt(coordinates.substring(1));         
            while (method.checkMoveIsLegal(coordinates) == false || isItTaken(x,y) == true){
                System.out.print("\nYou have entered an invalid co-ordinate to place the "+ship.split(":",2)[0]+"of size "+ship.split(":")[1]+" (xy): ");
                coordinates = input.next().toLowerCase();
                x = (int)(coordinates.charAt(0))-96;
                y = Integer.parseInt(coordinates.substring(1));
            }
            System.out.print("Do you want to place it (u)p, (d)own, (l)eft or (r)ight: ");
            String position = input.next().toLowerCase().substring(0, 1);
            while (!position.equals("u")&&!position.equals("d")&&!position.equals("l")&&!position.equals("r")){
                System.out.print("Please type 'u' for up, 'd' for down, 'l' for left, 'r' for right: ");
                position = input.next().toLowerCase();
            }
            while ((method.wouldItFit(x ,y , ship, position) == false)){
                System.out.print("Your ship will not fit, do you want to place it (u)p, (d)own, (l)eft or (r)ight: ");
                position = input.next().toLowerCase();
                while (!position.equals("u")&&!position.equals("d")&&!position.equals("l")&&!position.equals("r")){
                    System.out.print("Please type 'u' for up, 'd' for down, 'l' for left, 'r' for right: ");
                    position = input.next().toLowerCase();  
                }
            }         
            method.displayBoard();
        }
            
    }
    
    public boolean wouldItFit(int x ,int y, String ship, String position){

        int size = Integer.parseInt(ship.split(":")[1]);
        int ShipNo = Integer.parseInt(ship.split(":")[2]);
        BattleShip method = new BattleShip() ;
        int end = 5;
        boolean taken = true;
        switch (position){
            case "u":
               end = y - (size-1);
                if (method.isItTaken(x,y) == false && end > 0 && end <= 10){
                    for (int i = y; i >= end; i--){
                        BattleShip.board[i-1][x-1] = ShipNo;
                    }         
                }else{
                    taken = false;
                }
               break;
            case "d":
                end = y + (size-1);
                if (method.isItTaken(x,y) == false && end > 0 && end <= 10){
                    for (int i = y; i <= end; i++){
                        BattleShip.board[i-1][x-1] = ShipNo;
                    }         
                }else{
                    taken = false;
                }
               break;
            case "l":
                end = x - (size-1);
                if (method.isItTaken(x,y) == false && end > 0 && end <= 10){
                    for (int i = x; i >= end; i--){
                        BattleShip.board[y-1][i-1] = ShipNo;
                    }         
                }else{
                    taken = false;
                } 
                break;
            case "r":
                end = x + size-1;
                if (method.isItTaken(x,y) == false && end > 0 && end <= 10){
                    for (int i = x; i <= end; i++){
                        BattleShip.board[y-1][i-1] = ShipNo;
                    }         
                }else{
                    taken = false;
                } 
                break;
            default:
                break; 
        } 
        return (end > 0 && end <= 10 && taken);
    }
    
    
    public boolean isItTaken(int x, int y){
        boolean taken = true;
        if (BattleShip.board[y-1][x-1] == 0){
            taken = false;
        } 
        return taken;
    }
       
    public boolean checkMoveIsLegal(String coordinates){
       
        String x = String.valueOf(coordinates.charAt(0));
        String y = coordinates.substring(1);
        return (((coordinates.length() == 2 || coordinates.length() == 3 )) 
                && (y.matches("[1-9]")|| y.contains("10")) && x.contains ("[a-j]"));
    }
}

