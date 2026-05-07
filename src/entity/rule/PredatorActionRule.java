package entity.rule;

import static utility.MapUtility.VALUE_OF_HERBIVORE;

import entity.Coordination;
import entity.Creature;
import entity.predator.Predator;
import entity.ability.ActionAble;
import main.GameMap;

public class PredatorActionRule implements ActionAble {

  @Override
  public void upHp(GameMap map, Coordination creatureCoordinate) {
    Creature predator = (Creature) map.getEntityByCoordinate(creatureCoordinate);
    predator.setHp(predator.getHp() + VALUE_OF_HERBIVORE);
  }

  //TODO REFACTOR COORDINATE PARAM
  @Override
  public void makeAction(GameMap map, Coordination creatureCoordinate,
      Coordination goalCoordinate) {
    Creature goalCreature = (Creature) map.getEntityByCoordinate(goalCoordinate);
    if (isDead(creatureCoordinate, goalCreature, map)) {
      upHp(map, creatureCoordinate);
      map.removeEntityByCoordinate(goalCoordinate);
      return;
    }
    makeAttack(creatureCoordinate, goalCoordinate, map);
  }

  @Override
  public void makeAttack(Coordination creatureCoordinate, Coordination goalCreature,
      GameMap map) {
    Creature herbivore = (Creature) map.getEntityByCoordinate(goalCreature);
    int herbivoreHp = herbivore.getHp();
    Predator predator = (Predator) map.getEntityByCoordinate(creatureCoordinate);
    herbivore.setHp(herbivoreHp - predator.getPower());
  }

  private static boolean isDead(Coordination creatureCoordinate, Creature goalCreature,
      GameMap map) {
    int herbivoreHp = goalCreature.getHp();
    Predator predator = (Predator) map.getEntityByCoordinate(creatureCoordinate);
    return herbivoreHp <= predator.getPower();
  }
}
