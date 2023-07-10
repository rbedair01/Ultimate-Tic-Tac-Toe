// Reema Bedair (ryb200001) - CS 2336.003

/* ANALYSIS
    Allows a player(computer-AI) to interact and play in the game
 */

/* DESIGN
    Class inherits from 'APlayer'
    gives the computer player a name and mark type ('X' or 'O')
    using 'random' to generate a number and select a random box in the smaller board
    or select a random board on the main board
 */

package UltimateTicTacToe;

public class ComputerPlayer extends APlayer {

    // constructor - gives player name and mark
    public ComputerPlayer(String name, String mark){
        super(name, mark);
    }

    // computer player uses 'random' to place a mark randomly within the range
    private int randomNumber(int range){
        return (int) (Math.random() * range);
    }

    // player can choose a box to place their mark
    @Override
    public int selectBox(int range) {
        return randomNumber(range);
    }

    // player can choose a board to place their mark
    @Override
    public int selectBoard(int range) {
        return randomNumber(range);
    }

}
