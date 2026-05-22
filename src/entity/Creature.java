package entity;

import static utility.MoveUtility.chooseNextCell;

import entity.ability.ActionAble;
import entity.ability.Moveable;
import entity.factory.EntityFactory;
import entity.factory.HerbivoreFactory;
import entity.factory.PredatorFactory;
import entity.rule.MoveRule;
import entity.rule.RoutineRule;
import exception.EntityNotExistException;
import main.GameMap;
import main.Graph;


public abstract class Creature extends Entity implements Moveable, MoveRule, RoutineRule,
    ActionAble {

  private static final int HUNGRY_DAMAGE = 1;
  public static final int DEFAULT_CREATURE_HP = 20;
  public static final int DEFAULT_CREATURE_SPEED = 1;
  public static final int MIN_CREATURE_HP = 1;
  public static final int CHILD_COST = 10;
  public static final int MIN_HP_FOR_REPRODUCTION = 20;

  private final int speed;
  private int hp;

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

  @Override
  public void makeMove(GameMap map, Graph graph) {
    Coordination creatureCoordinate = map.getCoordinateByEntity(this).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
    chooseNextCell(map, graph, creatureCoordinate);
  }

  @Override
  public void starve(GameMap map) {
    if (getHp() == MIN_CREATURE_HP) {
      Coordination entityCoordinate = map.getCoordinateByEntity(this).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
      map.removeEntityByCoordinate(entityCoordinate);
      return;
    }
    setHp(getHp() - HUNGRY_DAMAGE);
  }

  //TODO THROW EXCEPTION IF DON'T MAKE
  @Override
  public void reproduce(GameMap map) {
    EntityFactory entityFactory;
    if (getHp() > MIN_HP_FOR_REPRODUCTION) {
      if (this instanceof Predator) {
        entityFactory = new PredatorFactory();
        setHp(getHp() - CHILD_COST);
        entityFactory.create(map);
        return;
      }
      if (this instanceof Herbivore) {
        entityFactory = new HerbivoreFactory();
        setHp(getHp() - CHILD_COST);
        entityFactory.create(map);
        return;
      }
    }
  }

}
