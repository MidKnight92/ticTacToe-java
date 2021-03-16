package com.viveros.steph;

import java.util.Arrays;

public class TicTacToe {

    protected char[] board;
    protected char userMarker;
    protected char aiMarker;
    protected char winner;
    protected char currentMarker;

    public TicTacToe(char playerToken, char aiMarker){
        this.userMarker = playerToken;
        this.aiMarker = aiMarker;
        this.winner = '-';
        this.board = setBoard();
    }
    public static char[] setBoard(){
        char[] board = new char[9];
        Arrays.fill(board, '-');
        return board;
    }

    public boolean playTurn(int spot){
        boolean isValid = withinRange(spot) && !isSpotTaken(spot);
        if (!isValid) return false;

        // Valid
        board[spot - 1] = currentMarker;
        currentMarker = ( currentMarker == userMarker ) ? aiMarker : userMarker;
        return true;
    }

    public boolean withinRange(int spot){
        // Valid Range is 1-9
        return spot > 0 && spot < board.length + 1;
    }

    public boolean isSpotTaken(int spot){
        return board[spot - 1] != '-';
    }
    public void printBoard(){
        System.out.println();
        for (int i = 0; i <board.length ; i++) {
            if (i % 3 == 0 && i != 0){
                System.out.println("\n--------------");
            }
            System.out.print(" | " + board[i]);
        }
        System.out.println();
    }

    public static void printIndexBoard(){
        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0){
                System.out.println("\n--------------");
            }
            System.out.print(" | " + (i + 1));
        }
        System.out.println();
    }

    public boolean checkForWinner(){
        boolean foundWinner = false;
        for (int i = 0; i < board.length ; i++) {
            foundWinner = checkColumnValues(i);
            if (foundWinner) {
                this.winner = board[i];
                break;
            }
        }
        return foundWinner;
    }

    public boolean checkColumnValues(int idx){
        boolean foundWinner = false;

        switch (idx){
            case 0:
                foundWinner = checkDown(idx) || checkRight(idx) || checkDiag(idx);
                if (foundWinner) return true;
                break;
            case 1:
                foundWinner = checkDown(idx);
                if (foundWinner) return true;
                break;
            case 2:
                foundWinner = checkDown(idx) || checkDiag(idx);
                if (foundWinner) return true;
                break;
            case 3:
            case 6:
                foundWinner = checkRight(idx);
                if (foundWinner) return true;
                break;
        }

        return foundWinner;
    }

    public boolean checkDown(int idx){
        // Check value is valid i.e. not a '-'
        if (!validValue(idx)) return false;
        return  board[idx] == board[(idx + 3)] &&  board[(idx + 3)] == board[(idx + 6)];
    }

    public boolean checkRight(int idx){
        // Check value is valid i.e. not a '-'
        if (!validValue(idx)) return false;
        return board[idx] == board[idx + 1] && board[idx + 1] == board[idx + 2];
    }

    public boolean checkDiag(int idx){
        // Check value is valid i.e. not a '-'
        if (!validValue(idx)) return false;

        // Check indices (idx = 0 :: 0,4,8) or (idx = 2 :: 2,4,6)
        return idx == 0 ? board[idx] == board[4] && board[4]== board[8] : board[idx] == board[4] && board[4]== board[6];
    }
    private boolean validValue(int idx){
        // Check value is valid i.e. not a '-'
        return board[idx] != '-';
    }
    private boolean checkDraw(){
        String boardString = String.copyValueOf(board);
        return boardString.indexOf('-') == -1;
    }

    public String gameOver(){
        String msg = "continue";
        if (checkForWinner()) msg = "We have a winner! The winner is " + this.winner + "!";
        if (checkDraw()) msg = "Draw: Game Over";
        return msg;
    }

}

