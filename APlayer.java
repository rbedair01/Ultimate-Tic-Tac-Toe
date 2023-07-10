// Reema Bedair (ryb200001) - CS 2336.003

/* ANALYSIS
    Is used to create the main aspects to either the human or computer player
 */

/* DESIGN
    An abstract class that is inherited by 'HumanPlayer' class and 'ComputerPlayer' class
    will determine the mark and name of the players
    2 abstract methods that will allow a player to select a box and board
 */
package UltimateTicTacToe;

public abstract class APlayer {

    private String name;
    private String mark;

    // Constructor
    public APlayer(String name, String mark){
        setName(name);
        setMark(mark);
    }

    // getters and setters (accessor methods)
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getMark(){
        return mark;
    }

    public void setMark(String mark){
        this.mark = mark;
    }


    // creates method for Override(abstract)
    public abstract int selectBox(int range);
    public abstract int selectBoard(int range);
}
