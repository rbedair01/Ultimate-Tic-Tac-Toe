// Reema Bedair (ryb200001) - CS 2336.003

/* ANALYSIS
    Enumaration to ensure the types of marks for the board
 */

/* DESIGN
    the enum conatins 'X', 'O', or a '-'(dash)
 */
package UltimateTicTacToe;

enum Mark {
    // types of marks placed in boxes
    X("X"),
    O("O"),
    DASH("-");
    private String mark;

    // constructor
    Mark(String mark){
        this.mark = mark;
    }
    // gets mark type (getter)
    public String getMark(){
        return mark;
    }
}