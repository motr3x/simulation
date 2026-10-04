package entity;


import config.CreatureConfig;
import exception.EntityNotExistException;
import java.util.Deque;
import java.util.Optional;
import main.GameMap;
import main.Graph;
import utility.PathFinder;


public abstract class Creature extends Entity {

  private final int speed;
  private final static Class<? extends Entity> GOAL = Herbivore.class;
  private int hp;
  private final Class<? extends Entity> goal;

  public Creature(int hp, int speed, Class<? extends Entity> goal) {
    this.hp = hp;
    this.speed = speed;
    this.goal = goal;
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

  public abstract boolean checkBarrier(GameMap gameMap, Coordinates followCoordinate);

  public abstract boolean isGoal(GameMap gameMap, Coordinates followCoordinate);

  public void makeMove(GameMap gameMap, Graph graph) {
    Coordinates creatureCoordinate = gameMap.getCoordinate(this).orElseThrow(
        () -> new EntityNotExistException("Entity doesn't exist"));
    for (int i = 0; i < getSpeed(); i++) {
      Optional<Deque<Coordinates>> track = PathFinder.useBfsAlgorithm(gameMap, graph.get(), creatureCoordinate);
      if (track.isPresent()) {
        Coordinates followCoordinate = track.get().poll();
        if (isGoal(gameMap, followCoordinate)) {
          makeAttack(gameMap, followCoordinate);
        } else {
          gameMap.shift(creatureCoordinate, followCoordinate, this);
        }
      }
      creatureCoordinate = gameMap.getCoordinate(this).orElseThrow(
          () -> new EntityNotExistException("Entity doesn't exist"));
    }
    reproduce(gameMap);
    starve(gameMap);
  }

  protected abstract void reproduce(GameMap gameMap);

  protected abstract void upHp();

  protected abstract void makeAttack(GameMap gameMap, Coordinates goalCreature);

  private void starve(GameMap gameMap) {
    if (getHp() == CreatureConfig.MIN_CREATURE_HP) {
      gameMap.remove(this);
      return;
    }
    setHp(getHp() - CreatureConfig.HUNGRY_DAMAGE);
  }
}
