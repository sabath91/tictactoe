package org.czyz.game;

public class Score {

    //consider to replace numbers with words
    int player1Score;
    int player2Score;

    public Score() {
        player1Score = 0;
        player2Score = 0;
    }

    public void player1Win() {
        player1Score += 3;
    }

    public void player2Win() {
        player2Score += 3;
    }

    public void markDraw() {
        player1Score += 1;
        player2Score += 1;
    }

    public boolean isDraw() {
        return player1Score == player2Score;
    }
}
