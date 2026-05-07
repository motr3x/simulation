package entity.ability;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import java.util.Map;
import main.GameMap;

public interface MoveAble {

  boolean checkBarrier(GameMap map, Coordination node);

  boolean isCorrectCreatureAndGoal(Creature creature,
      Map.Entry<Coordination, Entity> element);

  boolean isGoal(GameMap map, Coordination followCoordinate);
}
