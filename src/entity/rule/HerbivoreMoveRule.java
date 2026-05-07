package entity.rule;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import entity.herbivore.Herbivore;
import entity.ability.MoveAble;
import entity.predator.Predator;
import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import java.util.Map;
import main.GameMap;

public class HerbivoreMoveRule implements MoveAble {

  @Override
  public boolean checkBarrier(GameMap map, Coordination node) {
    return ((map.getEntityByCoordinate(node) instanceof Rock)
        || (map.getEntityByCoordinate(node) instanceof Tree)
        || (map.getEntityByCoordinate(node) instanceof Predator)
        || (map.getEntityByCoordinate(node) instanceof Herbivore));
  }

  @Override
  public boolean isCorrectCreatureAndGoal(Creature creature,
      Map.Entry<Coordination, Entity> element) {
    return (creature instanceof Herbivore && element.getValue() instanceof Grass);
  }

  @Override
  public boolean isGoal(GameMap map, Coordination followCoordinate) {
    return (map.getEntityByCoordinate(followCoordinate) instanceof Grass);
  }
}
