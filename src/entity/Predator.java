package entity;

import config.CreatureConfig;
import config.SimulationConfig;
import entity.environmentalentities.Grass;
import entity.environmentalentities.Rock;
import entity.environmentalentities.Tree;
import exception.EntityNotExistException;
import main.GameMap;

public class Predator extends Creature {

  private final static Class<? extends Entity> GOAL = Predator.class;

  public Predator(int hp, int speed) {
    super(hp, speed, GOAL);
  }

  public int getPower() {
    return CreatureConfig.DEFAULT_CREATURE_POWER;
  }

  @Override
  public boolean checkBarrier(GameMap gameMap, Coordinates followCoordinate) {
    return (gameMap.checkClassType(gameMap, followCoordinate, Rock.class)
        || gameMap.checkClassType(gameMap, followCoordinate, Tree.class)
        || gameMap.checkClassType(gameMap, followCoordinate, Grass.class)
        || gameMap.checkClassType(gameMap, followCoordinate, Predator.class));
  }

  @Override
  public boolean isGoal(GameMap gameMap, Coordinates followCoordinate) {
    return (gameMap.checkClassType(gameMap, followCoordinate, GOAL));
  }

  @Override
  protected void reproduce(GameMap gameMap) {
    if (getHp() > CreatureConfig.MIN_HP_FOR_REPRODUCTION) {
      setHp(getHp() - CreatureConfig.CHILD_COST);
      actions.InitActions.SPAWNER.spawnToMap(gameMap, EntityType.PREDATOR);
    }
  }

  @Override
  protected void upHp() {
    setHp(getHp() + SimulationConfig.COST_OF_HERBIVORE);
  }

  @Override
  protected void makeAttack(GameMap gameMap, Coordinates goalCoordinate) {
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
