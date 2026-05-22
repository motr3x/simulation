package entity.rule;

import entity.Coordination;
import main.GameMap;

public interface MoveRule {
  boolean checkBarrier(GameMap map, Coordination node);
  boolean isGoal(GameMap map, Coordination followCoordinate);
}
