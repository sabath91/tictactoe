package com.epam.game.round;

import com.epam.utils.Board;
import com.epam.utils.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

class MoveManager {


    private Board board;
    private Player player1;
    private Player player2;
    private final MoveValidator moveValidator;
    private Player currentPlayer;
    private Player lastPlayer;
    private int lastMove = -1 ;

    MoveManager(Board board, Player player1, Player player2) {

        this.board = board;
        moveValidator = new MoveValidator(board);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; //TODO change to startPlayer
    }

    void handleMove() {


        Scanner scanner = new Scanner(System.in);
        System.out.println(currentPlayer.getName().toUpperCase() + " - Gdzie chcesz postawić znak?:\n");
        try {
            int move = scanner.nextInt();

            if (moveValidator.validate(move)) {
                board.mark(move, currentPlayer.getSign());
                lastMove = move;
                changePlayer();
            } else {
                //retry
                System.out.println(currentPlayer.getName().toUpperCase() + " - NIEWŁAŚCIWY RUCH\n Spróbuj jeszcze raz! Gdzie chcesz postawić znak?:\n");
                handleMove();
            }
        }catch (InputMismatchException e) {
            System.out.println("Zły znak. Proszę o jakąś wartość liczbową");
            scanner.nextLine();
            handleMove();
        }


    }

    private void changePlayer() {
        Player nextPlayer;
        if (currentPlayer.equals(player1)) {
            nextPlayer= player2;
        } else {
            nextPlayer = player1;
        }
        lastPlayer = currentPlayer;
        currentPlayer = nextPlayer;
    }

    Player getLastPlayer() {
        return lastPlayer;
    }

    int getLastMove() {
        return lastMove;
    }
}
