package me.citerana.deadsea.bot.games;

import net.dv8tion.jda.core.entities.*;

import java.util.ArrayList;

public class TicTacToe {
    private int BOARD_SIZE = 3;
    private int[][] board;
    boolean gameOver;
    User p1;
    User p2;
    MessageChannel channel;

    public TicTacToe(User p1, User p2, MessageChannel channel) {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        this.p1 = p1;
        this.p2 = p2;
        this.channel = channel;
        this.gameOver = false;
    }

    public void play() {
        while (!this.gameOver) {

        }
    }

    public void sendRules() {
        p1.openPrivateChannel();
        p2.openPrivateChannel();
    }

    private void prettySendBoard() {
        String boardString = "";
        for (int i=0; i < BOARD_SIZE; i++) {
            boardString.concat("| ");
            for (int j=0; j < BOARD_SIZE; j++) {
                boardString.concat(Integer.toString(this.board[i][j]) + " |");
            }
            boardString.concat("\n");
        }


    }
}
