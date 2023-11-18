package models;

import Strategy.BotPlayingStrategy;
import Strategy.EasyGamePlay;

import java.util.Scanner;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, String playerSymbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, PlayerType.BOT, playerSymbol);
        this.botDifficultyLevel=botDifficultyLevel;
        botPlayingStrategy= new EasyGamePlay();
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
        botPlayingStrategy= new EasyGamePlay();
    }

    public Move makeMove(Board board) {
        return botPlayingStrategy.decideMove(this,board);
    }
}
