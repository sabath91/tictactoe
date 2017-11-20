package com.epam.game;

import com.epam.utils.Player;

class GameReferee {

    private Player player1;
    private Player player2;

    private int player1Score;
    private int player2Score;

    public GameReferee(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1Score = 0;
        player2Score = 0;
    }

     void givePointTo(Player player) {
        if(player.equals(player1)){
            player1Score += 3;
        }else {
            player2Score += 3;
        }

    }

    void giveEverybodyOnePoint() {
       player1Score++;
       player2Score++;
    }

    String getWinner() {
        if(player1Score>player2Score){
            return player1.getName() + " wygrał grę";
        }else if(player2Score>player1Score){
            return player2.getName() + " wygrał grę";
        }else {
            return "Gra zakończona remisem";
        }
    }
}
