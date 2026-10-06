package actions;

import entity.Coordinates;
import entity.Creature;
import entity.EntityType;
import entity.Herbivore;
import entity.Predator;
import entity.environmentalentities.Grass;
import exception.EntityNotExistException;
import java.util.Queue;
import main.GameMap;
import main.Graph;


public final class TurnAction {

  private TurnAction() {
  }

  public static final int MIN_COUNT_OF_GRASS = 6;

  public static void makeMoveForEverybody(GameMap gameMap, Graph graph) {
    Queue<Coordinates> predatorCoordinates = gameMap.getCoordinates(gameMap, Predator.class);
    Queue<Coordinates> herbivoresCoordinates = gameMap.getCoordinates(gameMap, Herbivore.class);

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

    createMissingGrass(gameMap, gameMap.getCoordinates(gameMap, Grass.class));
  }

  private static void createMissingGrass(GameMap gameMap, Queue<Coordinates> grassCoordinates) {
    if (!isGrassCountEnough(grassCoordinates)) {
      actions.InitActions.SPAWNER.spawnToMap(gameMap, EntityType.GRASS);
    }
  }

  private static boolean isGrassCountEnough(Queue<Coordinates> grassCoordinates) {
    return (grassCoordinates.size() >= MIN_COUNT_OF_GRASS);
  }
}


