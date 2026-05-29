package entity;


import static actions.InitActions.SPAWNER;
import static config.CreatureConfig.CHILD_COST;
import static config.CreatureConfig.DEFAULT_HERBIVORE_HP;
import static config.CreatureConfig.DEFAULT_HERBIVORE_SPEED;
import static config.CreatureConfig.MIN_HP_FOR_REPRODUCTION;
import static config.SimulationConfig.COST_OF_GRASS;
import static entity.EntityType.HERBIVORE;
import static utility.Helper.checkClassType;

import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import main.GameMap;

public class Herbivore extends Creature {

  public Herbivore() {
    super(DEFAULT_HERBIVORE_HP, DEFAULT_HERBIVORE_SPEED);
  }

  public Herbivore(int hp, int speed) {
    super(hp, speed);
  }

  @Override
  public void reproduce(GameMap gameMap) {
    if (getHp() > MIN_HP_FOR_REPRODUCTION) {
      setHp(getHp() - CHILD_COST);
      SPAWNER.spawnToMap(gameMap, HERBIVORE);
    }
  }

  @Override
  public void upHp() {
    setHp(getHp() + COST_OF_GRASS);
  }

  @Override
  public boolean checkBarrier(GameMap gameMap, Coordination followCoordinate) {
    return (checkClassType(gameMap, followCoordinate, Rock.class)
        || checkClassType(gameMap, followCoordinate, Tree.class)
        || checkClassType(gameMap, followCoordinate, Predator.class)
        || checkClassType(gameMap, followCoordinate, Herbivore.class));
  }

  @Override
  public boolean isGoal(GameMap gameMap, Coordination followCoordinate) {
    return (checkClassType(gameMap, followCoordinate, Grass.class));
  }

  @Override
  public void makeAttack(GameMap gameMap, Coordination goalCreature) {
    upHp();
    gameMap.remove(goalCreature);
  }
}
