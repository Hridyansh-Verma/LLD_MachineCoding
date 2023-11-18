package Controller;

import models.Game;
import models.GameStatus;
import models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players)
    {
        return Game.getBuilder()
                .setDimensions(dimension)
                .setPlayers(players)
                .build();
    }

    public GameStatus getGameStatus(Game game)
    {
        return game.getGameStatus();
    }
    public String getWinningPlayerName(Game game)
    {
        return game.getWinningPlayer().getName();
    }
    public void displayGameStatus(Game game)
    {
        game.getBoard().displayBoard();
    }
    public void executeNextMove(Game game)
    {
        game.makeNextMove();
    }
}
