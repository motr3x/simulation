package actions;


import static entity.EntityType.GRASS;
import static entity.EntityType.HERBIVORE;
import static entity.EntityType.PREDATOR;
import static entity.EntityType.ROCK;
import static entity.EntityType.TREE;
import static config.SimulationConfig.MAX_X_COORDINATE;
import static config.SimulationConfig.MAX_Y_COORDINATE;
import static config.SimulationConfig.MIN_INIT_COUNT_OF_GRASS;
import static config.SimulationConfig.MIN_INIT_COUNT_OF_HERBIVORE;
import static config.SimulationConfig.MIN_INIT_COUNT_OF_PREDATOR;
import static config.SimulationConfig.MIN_INIT_COUNT_OF_ROCK;
import static config.SimulationConfig.MIN_INIT_COUNT_OF_TREE;
import static config.SimulationConfig.MIN_X_COORDINATE;
import static config.SimulationConfig.MIN_Y_COORDINATE;

import entity.Coordination;
import java.util.List;
import main.GameMap;
import main.Graph;
import utility.EntitySpawner;


// Действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существ на карте
public final class InitActions {

  public static final EntitySpawner SPAWNER = new EntitySpawner();

  public static void initMap(GameMap map) {
    initDefaultRock(map);
    initDefaultTree(map);
    initStartGrass(map);
    initStartPredator(map);
    initStartHerbivore(map);
  }

  public static void initGraph(Graph graph) {
    for (int yCoordinate = MAX_Y_COORDINATE; yCoordinate >= MIN_Y_COORDINATE; yCoordinate--) {
      for (int xCoordinate = MIN_X_COORDINATE; xCoordinate <= MAX_X_COORDINATE; xCoordinate++) {
        // right
        if (isLowerLeftCorner(xCoordinate, yCoordinate)) {
          graph.setGraph(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate, yCoordinate + 1),
                  new Coordination(xCoordinate + 1, yCoordinate)));
          continue;
        }
        // right
        if (isUpperLeftCorner(xCoordinate, yCoordinate)) {
          graph.setGraph(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate, yCoordinate - 1),
                  new Coordination(xCoordinate + 1, yCoordinate)));
          continue;
        }
        // right
        if (isUpperRightCorner(xCoordinate, yCoordinate)) {
          graph.setGraph(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate - 1, yCoordinate),
                  new Coordination(xCoordinate, yCoordinate - 1)));
          continue;
        }
        // right
        if (isLowerRightCorner(xCoordinate, yCoordinate)) {
          graph.setGraph(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate - 1, yCoordinate),
                  new Coordination(xCoordinate, yCoordinate + 1)));
          continue;
        }
        // right
        if (isLeftSide(xCoordinate, yCoordinate)) {
          graph.setGraph(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate, yCoordinate - 1),
                  new Coordination(xCoordinate + 1, yCoordinate),
                  new Coordination(xCoordinate, yCoordinate + 1)));
          continue;
        }
        // right
        if (isAbove(xCoordinate, yCoordinate)) {
          graph.setGraph(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate - 1, yCoordinate),
                  new Coordination(xCoordinate, yCoordinate - 1),
                  new Coordination(xCoordinate + 1, yCoordinate)));
          continue;
        }
        // right
        if (isBottom(xCoordinate, yCoordinate)) {
          graph.setGraph(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate - 1, yCoordinate),
                  new Coordination(xCoordinate, yCoordinate + 1),
                  new Coordination(xCoordinate + 1, yCoordinate)));
          continue;
        }
        // right
        if (isRightSide(xCoordinate, yCoordinate)) {
          graph.setGraph(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate, yCoordinate - 1),
                  new Coordination(xCoordinate, yCoordinate + 1),
                  new Coordination(xCoordinate - 1, yCoordinate)));
          continue;
        }
        // right
        graph.setGraph(new Coordination(xCoordinate, yCoordinate),
            List.of(new Coordination(xCoordinate, yCoordinate + 1),
                new Coordination(xCoordinate + 1, yCoordinate),
                new Coordination(xCoordinate, yCoordinate - 1),
                new Coordination(xCoordinate - 1, yCoordinate)));
      }
    }
  }

  private static void initDefaultTree(GameMap map) {
    int countOfTree = 0;
    while (countOfTree < MIN_INIT_COUNT_OF_TREE) {
      SPAWNER.spawnToMap(map, TREE);
      countOfTree++;
    }
  }

  private static void initDefaultRock(GameMap map) {
    int countOfRock = 0;
    while (countOfRock < MIN_INIT_COUNT_OF_ROCK) {
      SPAWNER.spawnToMap(map, ROCK);
      countOfRock++;
    }
  }

  private static void initStartGrass(GameMap map) {
    int countOfGrass = 0;
    while (countOfGrass < MIN_INIT_COUNT_OF_GRASS) {
      SPAWNER.spawnToMap(map, GRASS);
      countOfGrass++;
    }
  }

  private static void initStartPredator(GameMap map) {
    int countOfPredator = 0;
    while (countOfPredator < MIN_INIT_COUNT_OF_PREDATOR) {
      SPAWNER.spawnToMap(map, PREDATOR);
      countOfPredator++;
    }
  }

  private static void initStartHerbivore(GameMap map) {
    int countOfHerbivore = 0;
    while (countOfHerbivore < MIN_INIT_COUNT_OF_HERBIVORE) {
      SPAWNER.spawnToMap(map, HERBIVORE);
      countOfHerbivore++;
    }
  }

  private static boolean isRightSide(int xCoordinate, int yCoordinate) {
    return (((yCoordinate > MIN_Y_COORDINATE) && (yCoordinate < MAX_Y_COORDINATE)) && (xCoordinate
        == MAX_X_COORDINATE));
  }

  private static boolean isBottom(int xCoordinate, int yCoordinate) {
    return (((xCoordinate > MIN_X_COORDINATE) && (xCoordinate < MAX_X_COORDINATE)) && (yCoordinate
        == MIN_Y_COORDINATE));
  }

  private static boolean isAbove(int xCoordinate, int yCoordinate) {
    return (((xCoordinate > MIN_X_COORDINATE) && (xCoordinate < MAX_X_COORDINATE)) && (yCoordinate
        == MAX_Y_COORDINATE));
  }

  private static boolean isLeftSide(int xCoordinate, int yCoordinate) {
    return (((yCoordinate > MIN_Y_COORDINATE) && (yCoordinate < MAX_Y_COORDINATE)) && (xCoordinate
        == MIN_X_COORDINATE));
  }

  private static boolean isLowerRightCorner(int xCoordinate, int yCoordinate) {
    return ((yCoordinate == MIN_Y_COORDINATE) && (xCoordinate == MAX_X_COORDINATE));
  }

  private static boolean isUpperRightCorner(int xCoordinate, int yCoordinate) {
    return ((yCoordinate == MAX_Y_COORDINATE) && (xCoordinate == MAX_X_COORDINATE));
  }

  private static boolean isUpperLeftCorner(int xCoordinate, int yCoordinate) {
    return ((yCoordinate == MAX_Y_COORDINATE) && (xCoordinate == MIN_X_COORDINATE));
  }

  private static boolean isLowerLeftCorner(int xCoordinate, int yCoordinate) {
    return ((yCoordinate == MIN_Y_COORDINATE) && (xCoordinate == MIN_X_COORDINATE));
  }
}