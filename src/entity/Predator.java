package entity;

import static actions.InitActions.SPAWNER;
import static entity.CreatureConfig.CHILD_COST;
import static entity.CreatureConfig.DEFAULT_CREATURE_POWER;
import static entity.CreatureConfig.MIN_HP_FOR_REPRODUCTION;
import static entity.EntityType.PREDATOR;
import static utility.MapUtility.VALUE_OF_HERBIVORE;
import static utility.MapUtility.checkClassType;

import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import exception.EntityNotExistException;
import main.GameMap;

public class Predator extends Creature {

  // create default predator
  public Predator() {
    super();
  }

  // create custom predator
  public Predator(int hp, int speed) {
    super(hp, speed);
  }

  public int getPower() {
    return DEFAULT_CREATURE_POWER;
  }

  @Override
  public void reproduce(GameMap map) {
    if (getHp() > MIN_HP_FOR_REPRODUCTION) {
      setHp(getHp() - CHILD_COST);
      SPAWNER.spawnToMap(map, PREDATOR);
    }
  }

  @Override
  public void upHp() {
    setHp(getHp() + VALUE_OF_HERBIVORE);
  }

  @Override
  public void makeAttack(GameMap map, Coordination goalCoordinate) {
    Creature goalCreature = map.getEntityByCoordinate(goalCoordinate, Creature.class)
        .orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
    if (isDead(goalCreature)) {
      upHp();
      map.removeEntityByCoordinate(goalCoordinate);
      return;
    }
    int herbivoreHp = goalCreature.getHp();
    goalCreature.setHp(herbivoreHp - getPower());
  }

  @Override
  public boolean checkBarrier(GameMap map, Coordination node) {
    return (checkClassType(map, node, Rock.class)
        || checkClassType(map, node, Tree.class)
        || checkClassType(map, node, Grass.class)
        || checkClassType(map, node, Predator.class));
  }

  @Override
  public boolean isGoal(GameMap map, Coordination followCoordinate) {
    return (checkClassType(map, followCoordinate, Herbivore.class));
  }

  private boolean isDead(Creature goalCreature) {
    int herbivoreHp = goalCreature.getHp();
    return herbivoreHp <= getPower();
  }
}
