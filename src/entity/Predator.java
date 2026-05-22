package entity;

import static utility.MapUtility.VALUE_OF_HERBIVORE;
import static utility.MapUtility.checkClassType;

import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import exception.EntityNotCreateException;
import exception.EntityNotExistException;
import main.GameMap;

public class Predator extends Creature {

  public static final int DEFAULT_PREDATOR_POWER = 10;
  private final int power;

  public Predator(int hp, int speed, int power) {
    super(hp, speed);
    this.power = power;
  }

  public int getPower() {
    return power;
  }

  @Override
  public void upHp() {
    setHp(getHp() + VALUE_OF_HERBIVORE);
  }

  @Override
  public void makeAttack(GameMap map, Coordination goalCoordinate) {
    Creature goalCreature = map.getEntityByCoordinate(goalCoordinate, Creature.class).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
    if (isDead(goalCreature)) {
      upHp();
      map.removeEntityByCoordinate(goalCoordinate);
      return;
    }
    int herbivoreHp = goalCreature.getHp();
    goalCreature.setHp(herbivoreHp - getPower());
  }

  private boolean isDead(Creature goalCreature) {
    int herbivoreHp = goalCreature.getHp();
    return herbivoreHp <= getPower();
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
}
