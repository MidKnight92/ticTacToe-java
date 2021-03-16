package com.viveros.steph;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = true;
        while (flag){
            System.out.println("Welcome to Tic Tac Toe");

            // get tokens
            System.out.print("Please enter your character: ");
            char playerToken = scanner.next().charAt(0);
            System.out.print("Please enter your opponent's character: ");
            char opponentToken = scanner.next().charAt(0);

            // instainte classes
            TicTacToe game = new TicTacToe(playerToken, opponentToken);
            Ai opponent = new Ai();

            // Show instructions
            System.out.println("\nGame Starting");
            System.out.println("Instructions: Enter a number position to place your token ( positions: 1 - 9 )\n");
            TicTacToe.printIndexBoard();

            int val = (int) (Math.random() * 8);
            System.out.println(val);
            if (val % 2 == 0) {
                game.currentMarker = opponentToken;
            } else {
                game.currentMarker = playerToken;
            }
            while (true){

                int position;
                // Check if users turn
                if (game.currentMarker == game.userMarker){
                    // User
                    System.out.print("It's your turn. Enter a position number ( 1-9 ): ");
                    position = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    // AI
                    System.out.println(":: Opponent's turn ::");
                    position = opponent.pickSpot(game);
                }
                // Check if valid position
                if (!game.playTurn(position)){
                    System.out.println("Position is either invalid or taken, please try again");
                } else {
                    System.out.println("Spot selected " + position);
                    game.playTurn(position);
                    System.out.println();
                    game.printBoard();
                }

                if (!game.gameOver().equals("continue")){
                    break;
                }
            }

            // Game over
            System.out.println(game.gameOver() + "\n");
            game.printBoard();
            System.out.print("\nPlay Again (y/n): ");
            char response = scanner.next().charAt(0);
            flag = response == 'Y' || response == 'y';
        }

    }
}
