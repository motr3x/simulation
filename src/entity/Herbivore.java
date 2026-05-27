package entity;


import static actions.InitActions.SPAWNER;
import static config.CreatureConfig.CHILD_COST;
import static config.CreatureConfig.DEFAULT_HERBIVORE_HP;
import static config.CreatureConfig.DEFAULT_HERBIVORE_SPEED;
import static config.CreatureConfig.MIN_HP_FOR_REPRODUCTION;
import static entity.EntityType.HERBIVORE;
import static utility.MapUtility.VALUE_OF_GRASS;
import static utility.MapUtility.checkClassType;

import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import main.GameMap;

public class Herbivore extends Creature {

  // create default herbivore
  public Herbivore() {
    super(DEFAULT_HERBIVORE_HP, DEFAULT_HERBIVORE_SPEED);
  }

  // create custom herbivore
  public Herbivore(int hp, int speed) {
    super(hp, speed);
  }

  @Override
  public void reproduce(GameMap map) {
    if (getHp() > MIN_HP_FOR_REPRODUCTION) {
      setHp(getHp() - CHILD_COST);
      SPAWNER.spawnToMap(map, HERBIVORE);
    }
  }

  @Override
  public boolean checkBarrier(GameMap map, Coordination node) {
    return (checkClassType(map, node, Rock.class)
        || checkClassType(map, node, Tree.class)
        || checkClassType(map, node, Predator.class)
        || checkClassType(map, node, Herbivore.class));
  }

  @Override
  public boolean isGoal(GameMap map, Coordination followCoordinate) {
    return (checkClassType(map, followCoordinate, Grass.class));
  }

  @Override
  public void upHp() {
    setHp(getHp() + VALUE_OF_GRASS);
  }

  @Override
  public void makeAttack(GameMap map, Coordination goalCreature) {
    upHp();
    map.removeEntityByCoordinate(goalCreature);
  }
}
