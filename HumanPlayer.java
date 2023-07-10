// Reema Bedair (ryb200001) - CS 2336.003

/* ANALYSIS
    Allows a Player (human) to play in the game
 */

/* DESIGN
    Class inherits from 'APlayer'
    gives the human player a name and mark type ('X' or 'O')
    allows the player to select a box and a miniature board for the ultimate (main) board
 */

package UltimateTicTacToe;

import java.util.Scanner;

public class HumanPlayer extends APlayer {
    Scanner input = new Scanner(System.in);

    // Constructor - gives player name and mark
    public HumanPlayer(String name, String mark){
        super(name, mark);
    }

    // player can choose a box to place their mark
    @Override
    public int selectBox(int range) {
        System.out.print("Please enter a valid box number : ");
        int box;

        do {
            box = input.nextInt();
        }while(box < 0 || box >= range);

        return box;
    }

    // player can choose a board to place their mark
    @Override
    public int selectBoard(int range) {
        System.out.print("Please enter a valid board number : ");
        int board;

        do {
            board = input.nextInt();
        }while(board < 0 || board >= range);

        return board;
    }

}