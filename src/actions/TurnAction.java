package actions;

import static actions.InitActions.SPAWNER;
import static config.SimulationConfig.MIN_COUNT_OF_GRASS;
import static entity.EntityType.GRASS;

import entity.Coordination;
import entity.Creature;
import entity.Herbivore;
import entity.Predator;
import entity.staticObject.Grass;
import exception.EntityNotExistException;
import java.util.Queue;
import main.GameMap;
import main.Graph;


// действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы или травоядных, если их осталось слишком мало
public final class TurnAction {

  private TurnAction() {
  }

  //передвижение всех существ
  public static void makeMoveForEverybody(GameMap gameMap, Graph graph) {
    Queue<Coordination> predatorCoordinates = gameMap.getPositions(gameMap, Predator.class);
    Queue<Coordination> herbivoresCoordinates = gameMap.getPositions(gameMap, Herbivore.class);

    while (!herbivoresCoordinates.isEmpty()) {
      Creature herbivore = gameMap.get(herbivoresCoordinates.poll(), Herbivore.class)
          .orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
      herbivore.makeMove(gameMap, graph);
    }

    while (!predatorCoordinates.isEmpty()) {
      Creature predator = gameMap.get(predatorCoordinates.poll(), Predator.class)
          .orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
      predator.makeMove(gameMap, graph);
    }

    createMissingGrass(gameMap.getPositions(gameMap, Grass.class), gameMap);
  }

  private static void createMissingGrass(Queue<Coordination> grassCoordinates, GameMap map) {
    if (!isGrassCountEnough(grassCoordinates)) {
      SPAWNER.spawnToMap(map, GRASS);
    }
  }

  private static boolean isGrassCountEnough(Queue<Coordination> grassCoordinates) {
    return (grassCoordinates.size() >= MIN_COUNT_OF_GRASS);
  }
}


