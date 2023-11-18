package Strategy;

import models.Board;
import models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy{
    List<HashMap<Character,Integer>> rowMap= new ArrayList<>();
    List<HashMap<Character,Integer>> colMap=new ArrayList<>();
    HashMap<Character,Integer> firstDiag=new HashMap<>();
    HashMap<Character,Integer> secondDiag=new HashMap<>();
    public OrderOneGameWinningStrategy(int dimensions) {

        for(int i=0;i<dimensions;i++)
        {
            rowMap.add(new HashMap<>());
            colMap.add(new HashMap<>());
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        char c = move.getPlayer().getPlayerSymbol().charAt(0);
        int row= move.getCell().getRow();
        int col= move.getCell().getCol();
        if(!rowMap.get(row).containsKey(c))
            rowMap.get(row).put(c,0);
        rowMap.get(row).put(c,rowMap.get(row).get(c)+1);
        if(!colMap.get(col).containsKey(c))
            colMap.get(col).put(c,0);
        colMap.get(col).put(c,colMap.get(col).get(c)+1);
        if(row==col)
        {
            if(!firstDiag.containsKey(c))
                firstDiag.put(c,0);
            firstDiag.put(c,firstDiag.get(c)+1);
        }
        if(row+col==board.getDimensions()-1)
        {
            if(!secondDiag.containsKey(c))
                secondDiag.put(c,0);
            secondDiag.put(c,secondDiag.get(c)+1);
        }
        if(rowMap.get(row).get(c)==board.getDimensions()
                ||colMap.get(col).get(c)== board.getDimensions())
        {
            return true;
        }
        if((row==col&& firstDiag.get(c)== board.getDimensions())
                ||(row+col== board.getDimensions()-1 && secondDiag.get(c)== board.getDimensions()))
            return true;
        return false;
    }
}
