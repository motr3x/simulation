package entity.rule;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import entity.herbivore.Herbivore;
import entity.predator.Predator;
import entity.ability.MoveAble;
import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import java.util.Map;
import main.GameMap;

public class PredatorMoveRule implements MoveAble {

  public boolean checkBarrier(GameMap map, Coordination node) {
    return ((map.getEntityByCoordinate(node) instanceof Rock)
        || (map.getEntityByCoordinate(node) instanceof Tree)
        || (map.getEntityByCoordinate(node) instanceof Grass)
        || (map.getEntityByCoordinate(node) instanceof Predator));
  }

  @Override
  public boolean isCorrectCreatureAndGoal(Creature creature,
      Map.Entry<Coordination, Entity> element) {
    return (creature instanceof Predator && element.getValue() instanceof Herbivore);
  }

  @Override
  public boolean isGoal(GameMap map, Coordination followCoordinate) {
    return (map.getEntityByCoordinate(followCoordinate) instanceof Herbivore);
  }
}
