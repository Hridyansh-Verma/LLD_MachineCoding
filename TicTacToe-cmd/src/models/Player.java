package models;

import java.util.Scanner;

public class Player {
    private String name;
    private PlayerType playerType;
    private String playerSymbol;

    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public Player(String name, PlayerType playerType, String playerSymbol) {
        this.name = name;
        this.playerType = playerType;
        this.playerSymbol = playerSymbol;
    }
    public Move makeMove(Board board) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter x and y for the move");
        int a =scanner.nextInt();
        int b=scanner.nextInt();
        return new Move(new Cell(a,b),this);
    }
}
