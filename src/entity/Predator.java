package entity;

import static actions.InitActions.SPAWNER;
import static config.CreatureConfig.CHILD_COST;
import static config.CreatureConfig.DEFAULT_CREATURE_POWER;
import static config.CreatureConfig.DEFAULT_PREDATOR_HP;
import static config.CreatureConfig.DEFAULT_PREDATOR_SPEED;
import static config.CreatureConfig.MIN_HP_FOR_REPRODUCTION;
import static config.SimulationConfig.COST_OF_HERBIVORE;
import static entity.EntityType.PREDATOR;
import static utility.Helper.checkClassType;

import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import exception.EntityNotExistException;
import main.GameMap;

public class Predator extends Creature {

  public Predator() {
    super(DEFAULT_PREDATOR_HP, DEFAULT_PREDATOR_SPEED);
  }

  public Predator(int hp, int speed) {
    super(hp, speed);
  }

  public int getPower() {
    return DEFAULT_CREATURE_POWER;
  }

  @Override
  public void reproduce(GameMap gameMap) {
    if (getHp() > MIN_HP_FOR_REPRODUCTION) {
      setHp(getHp() - CHILD_COST);
      SPAWNER.spawnToMap(gameMap, PREDATOR);
    }
  }

  @Override
  public void upHp() {
    setHp(getHp() + COST_OF_HERBIVORE);
  }

  @Override
  public boolean checkBarrier(GameMap gameMap, Coordination followCoordinate) {
    return (checkClassType(gameMap, followCoordinate, Rock.class)
        || checkClassType(gameMap, followCoordinate, Tree.class)
        || checkClassType(gameMap, followCoordinate, Grass.class)
        || checkClassType(gameMap, followCoordinate, Predator.class));
  }

  @Override
  public boolean isGoal(GameMap gameMap, Coordination followCoordinate) {
    return (checkClassType(gameMap, followCoordinate, Herbivore.class));
  }

  @Override
  public void makeAttack(GameMap gameMap, Coordination goalCoordinate) {
    Creature goalCreature = gameMap.get(goalCoordinate, Creature.class)
        .orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
    if (isDead(goalCreature)) {
      upHp();
      gameMap.remove(goalCoordinate);
      return;
    }
    int herbivoreHp = goalCreature.getHp();
    goalCreature.setHp(herbivoreHp - getPower());
  }

  private boolean isDead(Creature goalCreature) {
    int herbivoreHp = goalCreature.getHp();
    return herbivoreHp <= getPower();
  }
}
