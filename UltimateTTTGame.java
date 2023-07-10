// Reema Bedair (ryb200001) - CS 2336.003

/* ANALYSIS
    This class will allow two players to enjoy a game of Ultimate Tic Tac Toe
        The players can be 2 Humans, 2 computers, or a human and a computer
    Ultimate Tic Tac Toe is a regular tic-tac-toe board with miniature tic-tac-toe boards in each box
    The game starts with a player choosing the board and a box to be marked, after that none of the
    players can choose a board to be played on. Each board is chosen by the placement of the previous player's
    mark on a certain box in one of the boards.
    To win the game, a player needs to win 3 of the miniature tic-tac-toe boards in a row
        Either horizontally, vertically, or diagnolly
        If a player has won a board, the board will continue to be played on regardless
    Otherwise the game will end in a tie once all the boards are full
 */

/* DESIGN
    To create the main board, an array is used to hold 9 miniature boards
    The miniature boards are assigned a number (0-9) and each box inside those boards are assigned a number (0-9)
        This will allow us to keep track of the boards won, the board being played on, the boxes available or
        not available, etc.
    Once the players and all boards are set up the game is started
        To start the game, the first player will choose a board and box to mark the first position
        After that, a loop is used for the entirety of the game to go between players
            Inside the loop:
             - All nine boards are printed each turn to show user the available spaces and taken spaces
             - The players are switched with each iteration
             - If one of the boards become full, the current player is asked to choose a new board and place a mark
             in any of the available boxes in that chosen board
             - With each iteration the player is asked where they would like to place their next mark
             If the box is not available, the loop will end iteration and keep looping until user chooses an empty box
             - Everytime a mark is placed by player, the loop will check to see if there is a winner on the board being
             played on
               If there is a winner on the smaller board, the board and player who won the board is saved using 2 arrays
             - Each iteration will check if there is an ultimate winner of the game and if the game board is entirely full(tie)
             - At the end of each iteration, the box marked by player becomes the next board with a simple equal assignment
    This class will utilize many methods:
        - To check the smaller boards for a winner (checks each row, column, diagnol)
        - To check the main board for an ultimate winner (check the rows, columns, and both diagnols)
        - To check if there is a tie on the ultimate board (gameOver)
        - To create the print of the board
        - To switch between player
 */

/* TEST
    This class was mainly tested using 2 human players to ensure all objectives of the game are correct
    The game was also tested to ensure that a human and computer, as well as 2 computers, can play the game
    The objectives of the game were tested by running the program multiple times with valid inputs
 */

package UltimateTicTacToe;

import java.util.*;
import java.lang.*;
import java.io.*;


public class UltimateTTTGame {

    // calls two players for the game
    private APlayer[] players = new APlayer[2];

    // creating the 'board' data field with the interface 'Iboard'
    // will allow us to set each of the boards inside the main board
    private Iboard board;
    private Board[] regBoard = new Board[9];

    private String[] marks = {"X","O"};
    private String name = "TicTacToe";
    private int gameRowSize = 3;
    private int gameColSize = 3;
    private int gameScoreToWin = 3;
    private int currentPlayerIndex = -1;

    // used to keep track of the boards won and which player won them
    private int[] boardWins= new int[9];
    private String[] winPlayer= new String[9];



    // constructor to start game
    public UltimateTTTGame(){
        setPlayers();
        setBoard();
    }

    // sets the players up for game
    private void setPlayers(){
        for(int i = 0; i < players.length; i++){
            ComputerPlayer p = new ComputerPlayer("player" + i+1 , marks[i]);
            players[i] = p;
        }
    }

    // sets the players up for game
    public void setPlayers(APlayer player1, APlayer player2){
        players[0] = player1;
        players[1] = player2;
    }

    // sets board up for game
    // each board in the main board is initialized and casted with the 'Board' class
    private void setBoard(){
        int count = 0;
        while (count < 9) {
            this.board = new Board(gameRowSize, gameColSize, "UltimateTTTGame");
            regBoard[count] = (Board) this.board;
            count++;
        }
    }

    // prints the ultimate board to the console
    private void ultPrint(Board[] board) {
        System.out.println("Ultimate Tic Tac Toe!");

        // first line
        System.out.print(regBoard[0].boxes[0].getPlaceHolder() + " ");
        System.out.print(regBoard[0].boxes[1].getPlaceHolder() + " ");
        System.out.print(regBoard[0].boxes[2].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[1].boxes[0].getPlaceHolder() + " ");
        System.out.print(regBoard[1].boxes[1].getPlaceHolder() + " ");
        System.out.print(regBoard[1].boxes[2].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[2].boxes[0].getPlaceHolder() + " ");
        System.out.print(regBoard[2].boxes[1].getPlaceHolder() + " ");
        System.out.println(regBoard[2].boxes[2].getPlaceHolder());


        // second line
        System.out.print(regBoard[0].boxes[3].getPlaceHolder() + " ");
        System.out.print(regBoard[0].boxes[4].getPlaceHolder() + " ");
        System.out.print(regBoard[0].boxes[5].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[1].boxes[3].getPlaceHolder() + " ");
        System.out.print(regBoard[1].boxes[4].getPlaceHolder() + " ");
        System.out.print(regBoard[1].boxes[5].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[2].boxes[3].getPlaceHolder() + " ");
        System.out.print(regBoard[2].boxes[4].getPlaceHolder() + " ");
        System.out.println(regBoard[2].boxes[5].getPlaceHolder());


        // third line
        System.out.print(regBoard[0].boxes[6].getPlaceHolder() + " ");
        System.out.print(regBoard[0].boxes[7].getPlaceHolder() + " ");
        System.out.print(regBoard[0].boxes[8].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[1].boxes[6].getPlaceHolder() + " ");
        System.out.print(regBoard[1].boxes[7].getPlaceHolder() + " ");
        System.out.print(regBoard[1].boxes[8].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[2].boxes[6].getPlaceHolder() + " ");
        System.out.print(regBoard[2].boxes[7].getPlaceHolder() + " ");
        System.out.println(regBoard[2].boxes[8].getPlaceHolder());

        System.out.println("---------------------");

        // fourth line
        System.out.print(regBoard[3].boxes[0].getPlaceHolder() + " ");
        System.out.print(regBoard[3].boxes[1].getPlaceHolder() + " ");
        System.out.print(regBoard[3].boxes[2].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[4].boxes[0].getPlaceHolder() + " ");
        System.out.print(regBoard[4].boxes[1].getPlaceHolder() + " ");
        System.out.print(regBoard[4].boxes[2].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[5].boxes[0].getPlaceHolder() + " ");
        System.out.print(regBoard[5].boxes[1].getPlaceHolder() + " ");
        System.out.println(regBoard[5].boxes[2].getPlaceHolder());


        // fifth line
        System.out.print(regBoard[3].boxes[3].getPlaceHolder() + " ");
        System.out.print(regBoard[3].boxes[4].getPlaceHolder() + " ");
        System.out.print(regBoard[3].boxes[5].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[4].boxes[3].getPlaceHolder() + " ");
        System.out.print(regBoard[4].boxes[4].getPlaceHolder() + " ");
        System.out.print(regBoard[4].boxes[5].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[5].boxes[3].getPlaceHolder() + " ");
        System.out.print(regBoard[5].boxes[4].getPlaceHolder() + " ");
        System.out.println(regBoard[5].boxes[5].getPlaceHolder());


        // sixth line
        System.out.print(regBoard[3].boxes[6].getPlaceHolder() + " ");
        System.out.print(regBoard[3].boxes[7].getPlaceHolder() + " ");
        System.out.print(regBoard[3].boxes[8].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[4].boxes[6].getPlaceHolder() + " ");
        System.out.print(regBoard[4].boxes[7].getPlaceHolder() + " ");
        System.out.print(regBoard[4].boxes[8].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[5].boxes[6].getPlaceHolder() + " ");
        System.out.print(regBoard[5].boxes[7].getPlaceHolder() + " ");
        System.out.println(regBoard[5].boxes[8].getPlaceHolder());

        System.out.println("---------------------");


        // seventh line
        System.out.print(regBoard[6].boxes[0].getPlaceHolder() + " ");
        System.out.print(regBoard[6].boxes[1].getPlaceHolder() + " ");
        System.out.print(regBoard[6].boxes[2].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[7].boxes[0].getPlaceHolder() + " ");
        System.out.print(regBoard[7].boxes[1].getPlaceHolder() + " ");
        System.out.print(regBoard[7].boxes[2].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[8].boxes[0].getPlaceHolder() + " ");
        System.out.print(regBoard[8].boxes[1].getPlaceHolder() + " ");
        System.out.println(regBoard[8].boxes[2].getPlaceHolder());


        // eighth line
        System.out.print(regBoard[6].boxes[3].getPlaceHolder() + " ");
        System.out.print(regBoard[6].boxes[4].getPlaceHolder() + " ");
        System.out.print(regBoard[6].boxes[5].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[7].boxes[3].getPlaceHolder() + " ");
        System.out.print(regBoard[7].boxes[4].getPlaceHolder() + " ");
        System.out.print(regBoard[7].boxes[5].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[8].boxes[3].getPlaceHolder() + " ");
        System.out.print(regBoard[8].boxes[4].getPlaceHolder() + " ");
        System.out.println(regBoard[8].boxes[5].getPlaceHolder());


        // ninth line
        System.out.print(regBoard[6].boxes[6].getPlaceHolder() + " ");
        System.out.print(regBoard[6].boxes[7].getPlaceHolder() + " ");
        System.out.print(regBoard[6].boxes[8].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[7].boxes[6].getPlaceHolder() + " ");
        System.out.print(regBoard[7].boxes[7].getPlaceHolder() + " ");
        System.out.print(regBoard[7].boxes[8].getPlaceHolder() + " ");

        System.out.print("| " + regBoard[8].boxes[6].getPlaceHolder() + " ");
        System.out.print(regBoard[8].boxes[7].getPlaceHolder() + " ");
        System.out.println(regBoard[8].boxes[8].getPlaceHolder());

    }


    // main method for the game
    // method will start the game and selects the correct player with each iteration
    // ensure the correct board and box are played on and marked and print the board with every new change
    // method will run until a player has won or game has ended in a tie (Game Over
    public void start(){
        // the board is first prints when game starts
        ultPrint(regBoard);
        // once the game starts, 'switchPlayers()' is called to allow Player 1 to start playing
        switchPlayers();

        // asking first player which board to start with
        int startBoard = players[this.currentPlayerIndex].selectBoard(regBoard.length);

        // will hold the current box called on the place an 'X' or 'O'
        int boardBox;

        // will be used to count the wins of each board
        int winCount = 0;

        // initialize elements in boardWin array to -1
        // if the element is -1 a board hasn't been won otherwise the board will be filled
        // with number 0 - 9 of which boards have been won
        for(int i = 0; i < boardWins.length; i++){
            boardWins[i] = -1;
        }

        // do-while loop is used until game is over
        do {

            // if one of the boards is full, ask user to choose new board and end iteration with chosen board
            if(regBoard[startBoard].isFull()){
                startBoard = players[this.currentPlayerIndex].selectBoard(regBoard.length);
                continue;
            }

            // asks player which box would they like to place a mark
            // and saves the box number
            boardBox = players[this.currentPlayerIndex].selectBox(regBoard[0].boxes.length);

            // if box already has a mark, end current iteration
            if (!regBoard[startBoard].boxes[boardBox].isAvailable()){
                continue;
            }

            // If mark is available on board, place mark in correct board and box
            regBoard[startBoard].boxes[boardBox].setPlaceHolder(marks[currentPlayerIndex]);


            // Everytime mark is placed, check to see if there is a winner on the smaller boards
            // if statement will call on a method that will check if the board has a winner,
            // if the method returns true there is a winner on the board
            if (boardWinner(regBoard, startBoard)){
                // initialize boolean variable to true
                boolean isWon = true;
                // loop will check if the board already has been won
                // if board has been one, boolean 'isWon' is False and the game will continue
                for(int i = 0; i < boardWins.length; i++){
                    if (boardWins[i] == startBoard){
                        isWon = false;
                    }
                }
                // if the board hasn't been won, save the board that was won and the
                // player who won the board
                if (isWon == true){
                    // 'boardWins' will save the board that has been one, it will hold an integer (0-9)
                    boardWins[winCount] = startBoard;
                    // 'winPlayer' will hold the player who won the board
                    winPlayer[winCount] = marks[currentPlayerIndex];
                    //System.out.println("win count: " + winCount);
                    System.out.println("Board won: " + boardWins[winCount]);
                    System.out.println("Player that won board: " + winPlayer[winCount]);
                    // 'winCount' will ensure that both arrays are saving the won board and player at the same index
                    winCount++;
                }

            }


            // prints the updated board
            ultPrint(regBoard);

            // will check if there is a winner for the entire game by calling 'isWinner' method
            // if method returns true, break out of loop and game ends
            if (isWinner(boardWins, winPlayer)){
                break;
            }


            // switches players
            switchPlayers();

            // allows the chosen box the player made, to be the next board played on
            startBoard = boardBox;

            // 'gameOver()' method will check if the game has ended in a tie (if all boards are full)
        }while(!gameOver());

    }

    // switches the players
    private void switchPlayers(){
        // if 'currentPlayerIndex' is -1 or 1, player 1 will play next
        // if 'currentPlayerIndex' is 0, player 2 will play next
        if (currentPlayerIndex == -1) {
            currentPlayerIndex = 0;
        }
        else if (currentPlayerIndex == 0){
            currentPlayerIndex = 1;
        }
        else if (currentPlayerIndex == 1){
            currentPlayerIndex = 0;
        }
    }



    // method will check if board is full (tie)
    // if the board is full, a statement is outputted and 'true' is returned
    private boolean gameOver(){

        int countFull = 0;
        // Using 'isFull' method from 'Board' class, a loop is used to check if each board is full
        // if a board is full the counter variable is incremented by 1
        for (int i = 0; i < regBoard.length; i++) {
            if (regBoard[i].isFull())
                countFull++;
        }

        // since there are 9 boards, if the counter variable used to count the full boards equals 9
        // all boards are full and the game ends in a tie
        if (countFull == 9){
            System.out.println("Game Over: Tie");
            return true;
        }

        // if not all boards are full, 'false' is returned
        return false;
    }

    // method will declare if a player on the main board has won
    // if a player has won, a statement is outputted and 'true' is returned
    private boolean isWinner(int[] whichBoard, String[] playerWin){
        // if a row on the man board has been won
        if(mainRowWin(whichBoard, playerWin)){
            System.out.println("PLAYER " + player(currentPlayerIndex) + " WON!!");
            return true;
        } else if(mainColWin(whichBoard, playerWin)){ // if a column on the main board has been won
            System.out.println("PLAYER " + player(currentPlayerIndex) + " WON!!");
            return true;
        } else if(mainDiagWin(whichBoard, playerWin)){ // if a diagnol on the main board has been won
            System.out.println("PLAYER " + player(currentPlayerIndex) + " WON!!");
            return true;
        }

        // if nothing has been won, 'false' is returned
        return false;
    }

    // method will check all the rows of the main board to see if a player has been won
    // both arrays that have saved the winning boards and the players that won those boards are checked
    // to see if they both correspond to find a winner of the main board
    private boolean mainRowWin(int[] whichBoard, String[] playerWin){
        // a counter variable is used to find out if 3 boards have been won in a row by the same player,
        // if the counter equals 3, a player has won the game

        // checks 1st row
        int row1 = 0;
        for (int i = 0; i < whichBoard.length; i++){
            // if board 0, 1, 2 are won and the players of those winning boards are all the same
            if((whichBoard[i] == 0 || whichBoard[i] == 1 || whichBoard[i] == 2) && playerWin[i] == player(currentPlayerIndex)){
                row1++;
            }
        }
        if (row1 == 3){
            return true;
        }

        // checks 2nd row
        int row2 = 0;
        for (int i = 0; i < whichBoard.length; i++){
            // if board 3, 4, 4 are won and the players of those winning boards are all the same
            if((whichBoard[i] == 3 || whichBoard[i] == 4 || whichBoard[i] == 5) && playerWin[i] == player(currentPlayerIndex)){
                row2++;
            }
        }
        if (row2 == 3){
            return true;
        }

        // checks 3rd row
        int row3 = 0;
        for (int i = 0; i < whichBoard.length; i++){
            // if board 6, 7, 8 are won and the players of those winning boards are all the same
            if((whichBoard[i] == 6 || whichBoard[i] == 7 || whichBoard[i] == 8) && playerWin[i] == player(currentPlayerIndex)){
                row3++;
            }
        }
        if (row3 == 3){
            return true;
        }

        // no winners, 'false' is returned
        return false;
    }

    // method will check all the columns of the main board to see if a player has been won
    // both arrays that have saved the winning boards and the players that won those boards are checked
    // to see if they both correspond to find a winner of the main board
    private boolean mainColWin(int[] whichBoard, String[] playerWin){
        // a counter variable is used to find out if 3 boards have been won in a row by the same player,
        // if the counter equals 3, a player has won the game

        // check 1st column
        int col1 = 0;
        for (int i = 0; i < whichBoard.length; i++){
            // if board 0, 3, 6 are won and the players of those winning boards are all the same
            if((whichBoard[i] == 0 || whichBoard[i] == 3 || whichBoard[i] == 6) && playerWin[i] == player(currentPlayerIndex)){
                col1++;
            }
        }
        if (col1 == 3){
            return true;
        }

        // check 2nd column
        int col2 = 0;
        for (int i = 0; i < whichBoard.length; i++){
            // if board 1, 4, 7 are won and the players of those winning boards are all the same
            if((whichBoard[i] == 1 || whichBoard[i] == 4 || whichBoard[i] == 7) && playerWin[i] == player(currentPlayerIndex)){
                col2++;
            }
        }
        if (col2 == 3){
            return true;
        }

        // check 3rd column
        int col3 = 0;
        for (int i = 0; i < whichBoard.length; i++){
            // if board 2, 5, 8 are won and the players of those winning boards are all the same
            if((whichBoard[i] == 2 || whichBoard[i] == 5 || whichBoard[i] == 8) && playerWin[i] == player(currentPlayerIndex)){
                col3++;
            }
        }
        if (col3 == 3){
            return true;
        }

        // no winners, 'false' is returned
        return false;
    }

    // method will check both diagnols of the main board to see if a player has been won
    // both arrays that have saved the winning boards and the players that won those boards are checked
    // to see if they both correspond to find a winner of the main board
    private boolean mainDiagWin(int[] whichBoard, String[] playerWin){
        // a counter variable is used to find out if 3 boards have been won in a row by the same player,
        // if the counter equals 3, a player has won the game

        // check diagnol top left to bottom right
        int diagLR = 0;
        for (int i = 0; i < whichBoard.length; i++){
            // if board 0, 4, 8 are won and the players of those winning boards are all the same
            if((whichBoard[i] == 0 || whichBoard[i] == 4 || whichBoard[i] == 8) && playerWin[i] == player(currentPlayerIndex)){
                diagLR++;
            }
        }
        if (diagLR == 3){
            return true;
        }


        // check diagnol top right to bottom left
        int diagRL = 0;
        for (int i = 0; i < whichBoard.length; i++){
            // if board 2, 4, 6 are won and the players of those winning boards are all the same
            if((whichBoard[i] == 2 || whichBoard[i] == 4 || whichBoard[i] == 6) && playerWin[i] == player(currentPlayerIndex)){
                diagRL++;
            }
        }
        if (diagRL == 3){
            return true;
        }

        // no winners, 'false' is returned
        return false;
    }


    // method determines that if the currentPlayerIndex is '0' that the player is X,
    // Otherwise if the currentPlayerIndex is 1 the player is O,
    private String player(int currentPlayerIndex){
        String player = new String();
        if (currentPlayerIndex == 0){
            player = "X";
        }
        else if (currentPlayerIndex == 1){
            player = "O";
        }

        return player;
    }


    // this method is called on to check if the smaller boards in the main board has a winner
    // this method will call on other methods to check the rows, columns, and diagnols of the small board
    // if a 'true' is returned there is a winner on the small board, otherwise there is no winner ('false')
    private boolean boardWinner(Board[] regBoard, int boardNum){
        // calls on method to check the rows in small board
        // if won, 'true' is returned
        if(checkRow(regBoard, boardNum)){
            return true;
        }
        // calls on method to check the columns in small board
        // if won, 'true' is returned
        else if(checkCol(regBoard, boardNum)){
            return true;
        }
        // calls on method to check the diagnol(right to left) in small board
        // if won, 'true' is returned
        else if(checkDiagRL(regBoard,boardNum)){
            return true;
        }
        // calls on method to check the diagnol(left to right) in small board
        // if won, 'true' is returned
        else if(checkDiagLR(regBoard, boardNum)){
            return true;
        }

        // no winners
        return false;
    }

    // method checks each row in the small board sent through the method argument
    // to see if there is a winner of the small board
    // if there is a winner 'true' is returned
    private boolean checkRow(Board[] regBoard, int boardNum){
        // first row check
        int countRow1 = 0;
        for (int col = 0; col < gameColSize; col++) {
            if (regBoard[boardNum].getMark(0, col).equals(player(currentPlayerIndex))) {
                countRow1++;
            }
        }
        if (countRow1 == gameScoreToWin){ return true;}

        // second row check
        int countRow2 = 0;
        for (int col = 0; col < gameColSize; col++) {
            if (regBoard[boardNum].getMark(1, col).equals(player(currentPlayerIndex))) {
                countRow2++;
            }
        }
        if (countRow2 == gameScoreToWin){ return true;}

        // third row check
        int countRow3 = 0;
        for (int col = 0; col < gameColSize; col++) {
            if (regBoard[boardNum].getMark(2, col).equals(player(currentPlayerIndex))) {
                countRow3++;
            }
        }
        if (countRow3 == gameScoreToWin){ return true;}

        // no winner
        return false;
    }

    // method checks each column in the small board sent through the method argument
    // to see if there is a winner of the small board
    // if there is a winner 'true' is returned
    private boolean checkCol(Board[] regBoard, int boardNum){
        int countCol1 = 0;
        for (int row = 0; row < gameRowSize; row++) {
            if (regBoard[boardNum].getMark(row, 0).equals(player(currentPlayerIndex))) {
                countCol1++;
            }
        }
        if (countCol1 == gameScoreToWin){ return true;}

        int countCol2 = 0;
        for (int row = 0; row < gameRowSize; row++) {
            if (regBoard[boardNum].getMark(row, 1).equals(player(currentPlayerIndex))) {
                countCol2++;
            }
        }
        if (countCol2 == gameScoreToWin){ return true;}

        int countCol3 = 0;
        for (int row = 0; row < gameRowSize; row++) {
            if (regBoard[boardNum].getMark(row, 2).equals(player(currentPlayerIndex))) {
                countCol3++;
            }
        }
        if (countCol3 == gameScoreToWin){ return true;}

        // no winner
        return false;
    }

    // method checks the diagnol(left to right) in the small board sent through the method argument
    // to see if there is a winner of the small board
    // if there is a winner 'true' is returned
    private boolean checkDiagLR(Board[] regBoard, int boardNum){
        if((regBoard[boardNum].getMark(0,0).equals(players[currentPlayerIndex].getMark()) &&
                regBoard[boardNum].getMark(1,1).equals(players[currentPlayerIndex].getMark()) &&
                regBoard[boardNum].getMark(2,2).equals(players[currentPlayerIndex].getMark()))){
            return true;
        }

        // no winner
        return false;
    }

    // method checks the diagnol(right to left) in the small board sent through the method argument
    // to see if there is a winner of the small board
    // if there is a winner 'true' is returned
    private boolean checkDiagRL(Board[] regBoard, int boardNum){
        if((regBoard[boardNum].getMark(0,2).equals(players[currentPlayerIndex].getMark()) &&
                regBoard[boardNum].getMark(1,1).equals(players[currentPlayerIndex].getMark()) &&
                regBoard[boardNum].getMark(2,0).equals(players[currentPlayerIndex].getMark()))){
            return true;
        }

        // no winner
        return false;
    }

}
