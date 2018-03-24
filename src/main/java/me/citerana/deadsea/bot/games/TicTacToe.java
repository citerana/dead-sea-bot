package me.citerana.deadsea.bot.games;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;

import java.util.ArrayList;

public class TicTacToe {
    private int BOARD_SIZE = 3;
    private char[][] board;
    boolean gameOver;
    User p1;
    User p2;
    MessageChannel channel;

    public TicTacToe(User p1, User p2, MessageChannel channel) {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        this.p1 = p1;
        this.p2 = p2;
        this.channel = channel;
        this.gameOver = false;

        for (int i=0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    public void play() {
        while (!this.gameOver) {

        }
    }

    public void sendRules() {
        CharSequence description = "Use the command `d!move x y` when it is your turn";
        MessageEmbed content = new EmbedBuilder()
                .setTitle("Tic Tac Toe")
                .appendDescription(description)
                .addField("Board Layout", "(1,1) | (1,2) | (1,3)\n(2,1) | (2,2) | (2,3)\n(3,1) | (3,2) | (3,3)", false)
                .build();

        System.out.print(content);
        System.out.print("Private channels opened.");
        p1.openPrivateChannel().queue( (channel) -> channel.sendMessage(content).queue() );
        p2.openPrivateChannel().queue( (channel) -> channel.sendMessage(content).queue() );
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
