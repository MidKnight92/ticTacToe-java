package com.viveros.steph;

import java.util.ArrayList;

public class Ai {
    public int pickSpot(TicTacToe game){
        ArrayList<Integer> choices = new ArrayList<>();
        for (int i = 0; i < game.board.length ; i++) {
//            System.out.println(game.board[i]);
            if (game.board[i] == '-'){
                // Increment position to account for the playTurn method accepting indices (1-9)
                choices.add(i + 1);
            }
        }
//        System.out.println(choices.toString());
//        System.out.println((int) (Math.random() * choices.size()));
        return choices.get((int) (Math.random() * choices.size()));
    }
}
