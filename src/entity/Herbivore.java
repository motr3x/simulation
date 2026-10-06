package entity;


import config.CreatureConfig;
import entity.environmentalentities.Rock;
import entity.environmentalentities.Tree;
import main.GameMap;

public class Herbivore extends Creature {

  private final static Class<? extends Entity> GOAL = Herbivore.class;

  public Herbivore(int hp, int speed) {
    super(hp, speed, GOAL);
  }

  public static final int COST_OF_GRASS = 5;

  @Override
  protected void reproduce(GameMap gameMap) {
    if (getHp() > CreatureConfig.MIN_HP_FOR_REPRODUCTION) {
      setHp(getHp() - CreatureConfig.CHILD_COST);
      actions.InitActions.SPAWNER.spawnToMap(gameMap, EntityType.HERBIVORE);
    }
  }

  @Override
  protected void upHp() {
    setHp(getHp() + COST_OF_GRASS);
  }

  @Override
  public boolean checkBarrier(GameMap gameMap, Coordinates followCoordinate) {
    return (gameMap.checkClassType(gameMap, followCoordinate, Rock.class)
        || gameMap.checkClassType(gameMap, followCoordinate, Tree.class)
        || gameMap.checkClassType(gameMap, followCoordinate, Predator.class)
        || gameMap.checkClassType(gameMap, followCoordinate, Herbivore.class));
  }

  @Override
  public boolean isGoal(GameMap gameMap, Coordinates followCoordinate) {
    return (gameMap.checkClassType(gameMap, followCoordinate, GOAL));
  }

  @Override
  protected void makeAttack(GameMap gameMap, Coordinates goalCreature) {
    upHp();
    gameMap.remove(goalCreature);
  }
}
