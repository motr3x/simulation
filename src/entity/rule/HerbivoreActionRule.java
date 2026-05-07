package entity.rule;

import static utility.MapUtility.VALUE_OF_GRASS;

import entity.Coordination;
import entity.Creature;
import entity.ability.ActionAble;
import main.GameMap;

public class HerbivoreActionRule implements ActionAble {

  @Override
  public void makeAction(GameMap map, Coordination creatureCoordinate,
      Coordination followCoordinate) {
    makeAttack(creatureCoordinate, followCoordinate, map);
  }

  @Override
  public void upHp(GameMap map, Coordination creatureCoordinate) {
    Creature herbivore = (Creature) map.getEntityByCoordinate(creatureCoordinate);
    herbivore.setHp(herbivore.getHp() + VALUE_OF_GRASS);
  }

  @Override
  public void makeAttack(Coordination creatureCoordinate, Coordination goalCreature,
      GameMap map) {
    upHp(map, creatureCoordinate);
    map.removeEntityByCoordinate(goalCreature);
  }

}
