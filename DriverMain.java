// Reema Bedair (ryb200001) - CS 2336.003

/* ANALYSIS
    Contains the driver for the program (main)
    This class allows the program to run
 */

/* DESIGN
    creates the game
    sets the type of players to play the game
    starts the game
 */

package UltimateTicTacToe;

public class DriverMain {
    public static void main(String[] args){
        // creates game to be played
        UltimateTTTGame game = new UltimateTTTGame();
        // sets the 2 players for game
        game.setPlayers(new HumanPlayer("Player1", Mark.X.getMark()), new ComputerPlayer("Player2", Mark.O.getMark()));
        // starts the game
        game.start();
    }
}