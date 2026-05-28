package entity;

import static config.CreatureConfig.DEFAULT_CREATURE_HP;
import static config.CreatureConfig.DEFAULT_CREATURE_SPEED;
import static config.CreatureConfig.HUNGRY_DAMAGE;
import static config.CreatureConfig.MIN_CREATURE_HP;
import static utility.PathFinder.useBfsAlgorithm;

import exception.EntityNotExistException;
import java.util.Deque;
import java.util.Optional;
import main.GameMap;
import main.Graph;


public abstract class Creature extends Entity {

  private final int speed;
  private int hp;

  // create default creature
  public Creature() {
    this(DEFAULT_CREATURE_HP, DEFAULT_CREATURE_SPEED);
  }

  // create default creature
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
    Coordination creatureCoordinate = map.getPosition(this).orElseThrow(
        () -> new EntityNotExistException("Entity doesn't exist"));
    for (int i = 0; i < getSpeed(); i++) {
      Optional<Deque<Coordination>> track = useBfsAlgorithm(graph.get(), creatureCoordinate,
          map);
      if (track.isPresent()) {
        Coordination followCoordinate = track.get().poll();
        if (isGoal(map, followCoordinate)) {
          makeAttack(map, followCoordinate);
        } else {
          map.shift(creatureCoordinate, followCoordinate, this);
        }
      }
      creatureCoordinate = map.getPosition(this).orElseThrow(
          () -> new EntityNotExistException("Entity doesn't exist"));
    }
    reproduce(map);
    starve(map);
  }

  public abstract void reproduce(GameMap map);

  public abstract void upHp();

  public abstract void makeAttack(GameMap map, Coordination goalCreature);

  public abstract boolean checkBarrier(GameMap map, Coordination node);

  public abstract boolean isGoal(GameMap map, Coordination followCoordinate);

  private void starve(GameMap map) {
    if (getHp() == MIN_CREATURE_HP) {
      map.remove(this);
      return;
    }
    setHp(getHp() - HUNGRY_DAMAGE);
  }
}
