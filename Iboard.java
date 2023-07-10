// Reema Bedair (ryb200001) - CS 2336.003

/* ANALYSIS
    An interface that contains methods used for the creation of a Board
 */

/* DESIGN
    The method calls are placed
    The 'Board' class will implement this interface and all of its methods
 */

package UltimateTicTacToe;

public interface Iboard {

    void print();
    void reset();
    String getMark(int row, int col);
    boolean makeMove(String player,int row, int col);
    void setSize(int row, int col);
    int getColSize();
    int getRowSize();
    String getName();
    boolean isFull();

}
