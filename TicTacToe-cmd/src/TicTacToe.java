import Controller.GameController;
import models.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe");
        Scanner scanner= new Scanner(System.in);
        System.out.println("Whats the board dimensions you want? Enter a number : ");
        int dimension = scanner.nextInt();
        System.out.println("What are the no of players playing? Enter a number : ");
        int numPlayers =  scanner.nextInt();

        List<Player> players= new LinkedList<>();
        System.out.println("Do you want a bot? Press y/n");
        boolean isBot= scanner.next().equals("y");
        if(isBot)
        {
            numPlayers--;
            System.out.println("Enter the name of bot : ");
            String botName = scanner.next();
            System.out.println("Enter a symbol for the bot : ");
            String botSymbol = scanner.next();
            System.out.println("Enter Difficulty Level for bot? Easy:1 Medium:2 Hard:3 ");
            int botDifficultyLevel = scanner.nextInt();

            // how to convert value to ENUM
            players.add(new Bot(botName,botSymbol, BotDifficultyLevel.EASY));
        }

        for (int i=0;i<numPlayers;i++)
        {
            System.out.println("Enter the name for Player " + (i+1) + " : ");
            String playerName = scanner.next();
            System.out.println("Enter a symbol for the player " + (i+1) + " : ");
            String playerSymbol = scanner.next();
            players.add(new Player(playerName + (i+1), PlayerType.NORMAL,playerSymbol + (i+1)));
        }

        GameController gameController = new GameController();

        Game game =gameController.createGame(dimension,players);
        while (gameController.getGameStatus(game)==GameStatus.IN_PROGRESS)
        {
            System.out.println("Current Board: ");
            gameController.displayGameStatus(game);
            gameController.executeNextMove(game);
        }
        if(gameController.getGameStatus(game) == GameStatus.DRAW) {
            System.out.println("Game has drawn");
        } else {
            System.out.println("Game won by: "
                    + gameController.getWinningPlayerName(game));
        }
    }
}