// Reema Bedair (ryb200001) - CS 2336.003

/* ANALYSIS
    This class initializes, creates, and updates the boards for the game
 */

/* DESIGN
    Each board is initialized to a 3x3 board unless constructed otherwise
    Each board is initialized using the column x row size, to place the boxes in the correct positions
    If a box on the board is available it will make a mark the position on the board when prompted
    and then make that position not available on the board any more
    Can check if the board is full, meaning no available boxes on the board
 */

package UltimateTicTacToe;

public class Board implements Iboard {

    Box[] boxes;
    String name;
    int boardRowSize;
    int boardColSize;

    // Default Constructor
    Board(){
        this(3,3,"3*3 board");
    }

    // Constructor
    // sets the name and size of board
    Board(int rowSize, int colSize, String name){
        this.setName(name);
        this.setSize(rowSize, colSize);
    }

    // size of board (setter)
    public void setSize(int row, int col){
        if(row < 3 || col < 3) {
            System.out.println("min board size is 3*3");
        }
        else {
            this.boardRowSize = row;
            this.boardColSize = col;
            initialize();
        }
    }

    // initializes the board
    private void initialize(){
        boxes = new Box[boardRowSize * boardColSize];
        for(int i = 0; i < boxes.length; i++){
            Box b = new Box(i/boardColSize, i%boardColSize);
            boxes[i] = b;
        }
        print();
    }

    //prints board (no longer needed for Ultimate Tic Tac Toe)
    public void print(){
        /*System.out.println("printing the " + this.name + "-" + this.boardRowSize + "*" + this.boardColSize +
                "board info....");
        for(int i = 0; i < boxes.length; i++){
            if(i != 0 && i%boardColSize == 0) System.out.println();
            boxes[i].print();
        }
        System.out.println("");*/
    }

    // sets name of Board (setter)
    public void setName(String name){
        this.name = name;
    }

    // if box is available it will make a mark on the board and then make that position not available anymore
    public boolean makeMove(String mark, int row, int col){
        if (getMark(row, col) == "X" || getMark(row, col) == "O") {
            // System.out.println("Position not available, Try again");
            return false;
        }
        else{
            boxes[row * this.boardRowSize + col].setPlaceHolder(mark);
        }

        return true;
    }

    // checks if the board is full, no boxes available to place mark in board
    public boolean isFull(){
        for(Box b: boxes)
            if(b.isAvailable()) return false;
        return true;
    }

    // tells us the value in a particular location
    public String getMark(int row, int col){
        return boxes[row * this.boardRowSize + col].getPlaceHolder();
    }

    @Override
    public void reset(){
    }

    // gets column size of board(getter)
    @Override
    public int getColSize(){
        return this.boardColSize;
    }

    // gets row size of board(getter)
    @Override
    public int getRowSize(){
        return this.boardRowSize;
    }

    // gets name of board(getter)
    @Override
    public String getName() {
        return this.name;
    }


}

