package Strategy;

import models.Board;
import models.Move;
import models.Player;

public interface BotPlayingStrategy {
    public Move decideMove(Player player, Board board);
}
