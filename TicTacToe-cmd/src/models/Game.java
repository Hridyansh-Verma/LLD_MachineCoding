package models;

import Exceptions.InvalidGameDimensionException;
import Strategy.GameWinningStrategy;
import Strategy.OrderOneGameWinningStrategy;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private Player winningPlayer;

    private GameWinningStrategy gameWinningStrategy;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public static Builder getBuilder()
    {
        return new Builder();
    }

    public void makeNextMove() {
        Player playingPlayer = players.get(nextPlayerIndex);
        System.out.println("Its "+ playingPlayer.getName()+"'s turn.");
        Move move = playingPlayer.makeMove(board);
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if(board.getCells().get(row).get(col).getCellState()==CellState.EMPTY)
        {
            board.applyMove(move);
            moves.add(move);

            //Checking Winner
            if (gameWinningStrategy.checkWinner(board,move))
            {
                gameStatus=GameStatus.ENDED;
                winningPlayer=playingPlayer;
            }

            //Check Draw
            if(moves.size()==board.getDimensions()*board.getDimensions())
            {
                gameStatus=GameStatus.DRAW;
            }
            nextPlayerIndex=(nextPlayerIndex+1)%players.size();
        }

    }

    public static class Builder
    {
        private int dimensions;
        private List<Player> players;

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build()
        {
            try {
                if(isValid())
                {

                }
            } catch (InvalidGameDimensionException e) {
                return null;
            }
            Game game = new Game();
            game.setBoard(new Board(dimensions));
            game.setPlayers(players);
            game.setMoves(new LinkedList<>());
            game.setNextPlayerIndex(0);
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimensions));
            return game;
        }
        boolean isValid() throws InvalidGameDimensionException
        {
            if(dimensions<3)
            {
                throw new InvalidGameDimensionException("Dimension less than 3");
            }
            return true;
        }
    }
}
