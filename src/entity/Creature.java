package entity;

import entity.ability.ActionAble;
import entity.ability.MoveAble;
import entity.herbivore.Herbivore;
import entity.predator.Predator;
import main.GameMap;


public abstract class Creature extends Entity {

  private static final int HUNGRY_DAMAGE = 1;
  protected static final int DEFAULT_CREATURE_HP = 20;
  protected static final int DEFAULT_CREATURE_SPEED = 20;

  private final int speed;
  private int hp;
  private final MoveAble moveable;
  private final ActionAble actionAble;

  public Creature(int speed, int hp, MoveAble moveable, ActionAble actionAble) {
    this.hp = hp;
    this.speed = speed;
    this.moveable = moveable;
    this.actionAble = actionAble;
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

  public static void moving(GameMap map, Coordination creatureCoordinate,
      Coordination followCoordinate){
    map.setMap(followCoordinate, map.getEntityByCoordinate(creatureCoordinate));
    map.removeEntityByCoordinate(creatureCoordinate);
  }


  //TODO THROW EXCEPTION IF DON'T MAKE
  public static void makeDescendant(GameMap map, Coordination creatureCoordinate){
    Creature creature = (Creature) map.getEntityByCoordinate(creatureCoordinate);
    if(creature.getHp() > 20){
      if(creature instanceof Predator) {
        creature.setHp(creature.getHp()-10);
        Predator.create(map);
        return;
      }
      if(creature instanceof Herbivore) {
        creature.setHp(creature.getHp()-10);
        Herbivore.create(map);
        return;
      }
    }
  }

  public static void makeHungry(GameMap map, Coordination creatureCoordinate){
    Creature creature = (Creature) map.getEntityByCoordinate(creatureCoordinate);
    if(creature.getHp() == 1) {
      makeDead(map, creatureCoordinate);
      return;
    }
    creature.setHp(creature.getHp() - HUNGRY_DAMAGE);
  }

  private static void makeDead(GameMap map, Coordination creatureCoordinate){
    map.removeEntityByCoordinate(creatureCoordinate);
  }
}
