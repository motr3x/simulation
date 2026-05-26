package entity;

import static entity.CreatureConfig.DEFAULT_CREATURE_HP;
import static entity.CreatureConfig.DEFAULT_CREATURE_SPEED;
import static entity.CreatureConfig.HUNGRY_DAMAGE;
import static entity.CreatureConfig.MIN_CREATURE_HP;
import static utility.MoveUtility.chooseNextCell;

import exception.EntityNotExistException;
import main.GameMap;
import main.Graph;


public abstract class Creature extends Entity {

  private final int speed;
  private int hp;

  // create default creature
  public Creature() {
    this(DEFAULT_CREATURE_HP, DEFAULT_CREATURE_SPEED);
  }

  // create custom creature
  public Creature(int hp, int speed) {
    this.hp = hp;
    this.speed = speed;
  }

  public int getSpeed() {
    return speed;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public void makeMove(GameMap map, Graph graph) {
    Coordination creatureCoordinate = map.getCoordinateByEntity(this)
        .orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
    chooseNextCell(map, graph, creatureCoordinate);
  }

  public void starve(GameMap map) {
    if (getHp() == MIN_CREATURE_HP) {
      Coordination entityCoordinate = map.getCoordinateByEntity(this)
          .orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
      map.removeEntityByCoordinate(entityCoordinate);
      return;
    }
    setHp(getHp() - HUNGRY_DAMAGE);
  }

  //TODO THROW EXCEPTION IF DON'T MAKE
  public abstract void reproduce(GameMap map);

  public abstract void upHp();

  public abstract void makeAttack(GameMap map, Coordination goalCreature);

  public abstract boolean checkBarrier(GameMap map, Coordination node);

  public abstract boolean isGoal(GameMap map, Coordination followCoordinate);
}
