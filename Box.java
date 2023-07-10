// Reema Bedair (ryb200001) - CS 2336.003

/* ANALYSIS
    creates and initializes boxes for each smaller board
 */

/* DESIGN
    finds the position of each box using the row and column number and initializes it with a dash('-')
    checks if a box is available to be marked
    prints the placeholder(dash('-')) in each box
 */

package UltimateTicTacToe;

class Box {

    private int row;
    private int col;

    private final static String DASH = "-";
    private String placeHolder = Box.DASH;

    // constructor
    Box(int row, int col){
        this.row = row;
        this.col = col;
    }

    // getter
    String getPlaceHolder(){
        return placeHolder;
    }

    // setter
    boolean setPlaceHolder(String placeHolder) {
        if(isAvailable()) {
            this.placeHolder = placeHolder;
            return true;
        }
        return false;
    }

    // checks if the box is available with no marks placed
    boolean isAvailable(){
        return this.placeHolder.equals(Box.DASH);
    }

    // prints placeholder of the box
    void print(){
        //System.out.println("row:" + row + " col:" + col + " placeholder:" + placeHolder);
        System.out.print(placeHolder + " ");
    }

}
