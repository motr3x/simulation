package actions;

import static actions.InitActions.entitySpawner;
import static utility.MapUtility.getInfoByCreaturesCoordinates;

import entity.Coordination;
import entity.EntityType;
import entity.Herbivore;
import entity.Predator;
import entity.ability.Moveable;
import entity.factory.EntityFactory;
import entity.factory.GrassFactory;
import exception.EntityNotExistException;
import java.util.Queue;
import main.GameMap;
import main.Graph;


// действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы или травоядных, если их осталось слишком мало
public final class TurnAction {

  public static final int PREDATORS_PLACE = 1;
  public static final int HERBIVORE_PLACE = 0;
  private static final int GRASS_PLACE = 2;
  private static final int MIN_COUNT_OF_GRASS = 20;

  //передвижение всех существ
  public static void makeMoveForEverybody(GameMap gameMap, Graph graph) {
    Queue<Coordination> predators = getInfoByCreaturesCoordinates(gameMap).get(PREDATORS_PLACE);
    Queue<Coordination> herbivors = getInfoByCreaturesCoordinates(gameMap).get(HERBIVORE_PLACE);

    while (!herbivors.isEmpty()) {
      Moveable herbivore = gameMap.getEntityByCoordinate(herbivors.poll(), Herbivore.class).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
      herbivore.makeMove(gameMap, graph);
    }

    while (!predators.isEmpty()) {
      Moveable predator = gameMap.getEntityByCoordinate(predators.poll(), Predator.class).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));;
      predator.makeMove(gameMap, graph);
    }
    createMissingGrass(getInfoByCreaturesCoordinates(gameMap).get(GRASS_PLACE), gameMap);
  }

  private static void createMissingGrass(Queue<Coordination> grassCoordinates, GameMap map) {
    if (!isGrassCountEnough(grassCoordinates)) {
      entitySpawner.spawn(EntityType.GRASS, map);
    }
  }

  private static boolean isGrassCountEnough(Queue<Coordination> grassCoordinates) {
    return (grassCoordinates.size() == MIN_COUNT_OF_GRASS);
  }
}


