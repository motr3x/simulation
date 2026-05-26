package entity;

import static actions.InitActions.SPAWNER;
import static entity.CreatureConfig.CHILD_COST;
import static entity.CreatureConfig.DEFAULT_CREATURE_HP;
import static entity.CreatureConfig.DEFAULT_CREATURE_SPEED;
import static entity.CreatureConfig.HUNGRY_DAMAGE;
import static entity.CreatureConfig.MIN_CREATURE_HP;
import static entity.CreatureConfig.MIN_HP_FOR_REPRODUCTION;
import static entity.EntityType.HERBIVORE;
import static entity.EntityType.PREDATOR;
import static utility.MoveUtility.chooseNextCell;

import entity.ability.ActionAble;
import entity.ability.Moveable;
import entity.factory.EntityFactory;
import entity.rule.MoveRule;
import entity.rule.RoutineRule;
import exception.EntityNotExistException;
import main.GameMap;
import main.Graph;


public abstract class Creature extends Entity implements Moveable, MoveRule, RoutineRule,
    ActionAble {

  private final int speed;
  private int hp;

  // create custom creature
  public Creature(int hp, int speed) {
    this.hp = hp;
    this.speed = speed;
  }

  // create default creature
  public Creature() {
    this(DEFAULT_CREATURE_HP, DEFAULT_CREATURE_SPEED);
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
}
