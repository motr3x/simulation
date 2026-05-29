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

  public void makeMove(GameMap gameMap, Graph graph) {
    Coordination creatureCoordinate = gameMap.getPosition(this).orElseThrow(
        () -> new EntityNotExistException("Entity doesn't exist"));
    for (int i = 0; i < getSpeed(); i++) {
      Optional<Deque<Coordination>> track = useBfsAlgorithm(gameMap, graph.get(), creatureCoordinate);
      if (track.isPresent()) {
        Coordination followCoordinate = track.get().poll();
        if (isGoal(gameMap, followCoordinate)) {
          makeAttack(gameMap, followCoordinate);
        } else {
          gameMap.shift(creatureCoordinate, followCoordinate, this);
        }
      }
      creatureCoordinate = gameMap.getPosition(this).orElseThrow(
          () -> new EntityNotExistException("Entity doesn't exist"));
    }
    reproduce(gameMap);
    starve(gameMap);
  }

  private void starve(GameMap gameMap) {
    if (getHp() == MIN_CREATURE_HP) {
      gameMap.remove(this);
      return;
    }
    setHp(getHp() - HUNGRY_DAMAGE);
  }

  public abstract void reproduce(GameMap gameMap);

  public abstract void upHp();

  public abstract boolean checkBarrier(GameMap gameMap, Coordination followCoordinate);

  public abstract boolean isGoal(GameMap gameMap, Coordination followCoordinate);

  public abstract void makeAttack(GameMap gameMap, Coordination goalCreature);

}
