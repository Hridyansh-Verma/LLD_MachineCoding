package Strategy;

import models.*;

public class EasyGamePlay implements BotPlayingStrategy{
    @Override
    public Move decideMove(Player player, Board board) {
        for(int i=0;i< board.getDimensions();i++)
        {
            for(int j=0;j< board.getDimensions();j++)
            {
                if(board.getCells().get(i).get(j).getCellState()== CellState.EMPTY)
                {
                    return new Move(new Cell(i,j),player);
                }
            }
        }
        return null;
    }
}
