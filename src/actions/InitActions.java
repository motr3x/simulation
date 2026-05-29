package actions;


import static config.SimulationConfig.INIT_COUNT_OF_GRASS;
import static config.SimulationConfig.INIT_COUNT_OF_HERBIVORE;
import static config.SimulationConfig.INIT_COUNT_OF_PREDATOR;
import static config.SimulationConfig.INIT_COUNT_OF_ROCK;
import static config.SimulationConfig.INIT_COUNT_OF_TREE;
import static config.SimulationConfig.MAX_X_COORDINATE;
import static config.SimulationConfig.MAX_Y_COORDINATE;
import static config.SimulationConfig.MIN_X_COORDINATE;
import static config.SimulationConfig.MIN_Y_COORDINATE;
import static entity.EntityType.GRASS;
import static entity.EntityType.HERBIVORE;
import static entity.EntityType.PREDATOR;
import static entity.EntityType.ROCK;
import static entity.EntityType.TREE;

import entity.Coordination;
import java.util.List;
import main.GameMap;
import main.Graph;
import utility.EntitySpawner;


public final class InitActions {

  private InitActions() {
  }

  public static final EntitySpawner SPAWNER = new EntitySpawner();

  public static void initMap(GameMap gameMap) {
    initDefaultRock(gameMap);
    initDefaultTree(gameMap);
    initStartGrass(gameMap);
    initStartPredator(gameMap);
    initStartHerbivore(gameMap);
  }

  public static void initGraph(Graph graph) {
    for (int yCoordinate = MAX_Y_COORDINATE; yCoordinate >= MIN_Y_COORDINATE; yCoordinate--) {
      for (int xCoordinate = MIN_X_COORDINATE; xCoordinate <= MAX_X_COORDINATE; xCoordinate++) {
        
        if (isLowerLeftCorner(xCoordinate, yCoordinate)) {
          graph.set(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate, yCoordinate + 1),
                  new Coordination(xCoordinate + 1, yCoordinate)));
          continue;
        }
       
        if (isUpperLeftCorner(xCoordinate, yCoordinate)) {
          graph.set(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate, yCoordinate - 1),
                  new Coordination(xCoordinate + 1, yCoordinate)));
          continue;
        }
     
        if (isUpperRightCorner(xCoordinate, yCoordinate)) {
          graph.set(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate - 1, yCoordinate),
                  new Coordination(xCoordinate, yCoordinate - 1)));
          continue;
        }
     
        if (isLowerRightCorner(xCoordinate, yCoordinate)) {
          graph.set(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate - 1, yCoordinate),
                  new Coordination(xCoordinate, yCoordinate + 1)));
          continue;
        }
        
        if (isLeftSide(xCoordinate, yCoordinate)) {
          graph.set(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate, yCoordinate - 1),
                  new Coordination(xCoordinate + 1, yCoordinate),
                  new Coordination(xCoordinate, yCoordinate + 1)));
          continue;
        }
        
        if (isAboveSide(xCoordinate, yCoordinate)) {
          graph.set(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate - 1, yCoordinate),
                  new Coordination(xCoordinate, yCoordinate - 1),
                  new Coordination(xCoordinate + 1, yCoordinate)));
          continue;
        }
        
        if (isBottomSide(xCoordinate, yCoordinate)) {
          graph.set(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate - 1, yCoordinate),
                  new Coordination(xCoordinate, yCoordinate + 1),
                  new Coordination(xCoordinate + 1, yCoordinate)));
          continue;
        }
        
        if (isRightSide(xCoordinate, yCoordinate)) {
          graph.set(new Coordination(xCoordinate, yCoordinate),
              List.of(new Coordination(xCoordinate, yCoordinate - 1),
                  new Coordination(xCoordinate, yCoordinate + 1),
                  new Coordination(xCoordinate - 1, yCoordinate)));
          continue;
        }
        
        graph.set(new Coordination(xCoordinate, yCoordinate),
            List.of(new Coordination(xCoordinate, yCoordinate + 1),
                new Coordination(xCoordinate + 1, yCoordinate),
                new Coordination(xCoordinate, yCoordinate - 1),
                new Coordination(xCoordinate - 1, yCoordinate)));
      }
    }
  }

  private static void initDefaultTree(GameMap gameMap) {
    int countOfTree = 0;
    while (countOfTree < INIT_COUNT_OF_TREE) {
      SPAWNER.spawnToMap(gameMap, TREE);
      countOfTree++;
    }
  }

  private static void initDefaultRock(GameMap gameMap) {
    int countOfRock = 0;
    while (countOfRock < INIT_COUNT_OF_ROCK) {
      SPAWNER.spawnToMap(gameMap, ROCK);
      countOfRock++;
    }
  }

  private static void initStartGrass(GameMap gameMap) {
    int countOfGrass = 0;
    while (countOfGrass < INIT_COUNT_OF_GRASS) {
      SPAWNER.spawnToMap(gameMap, GRASS);
      countOfGrass++;
    }
  }

  private static void initStartPredator(GameMap gameMap) {
    int countOfPredator = 0;
    while (countOfPredator < INIT_COUNT_OF_PREDATOR) {
      SPAWNER.spawnToMap(gameMap, PREDATOR);
      countOfPredator++;
    }
  }

  private static void initStartHerbivore(GameMap gameMap) {
    int countOfHerbivore = 0;
    while (countOfHerbivore < INIT_COUNT_OF_HERBIVORE) {
      SPAWNER.spawnToMap(gameMap, HERBIVORE);
      countOfHerbivore++;
    }
  }

  private static boolean isRightSide(int xCoordinate, int yCoordinate) {
    return (((yCoordinate > MIN_Y_COORDINATE) && (yCoordinate < MAX_Y_COORDINATE)) && (xCoordinate
        == MAX_X_COORDINATE));
  }

  private static boolean isBottomSide(int xCoordinate, int yCoordinate) {
    return (((xCoordinate > MIN_X_COORDINATE) && (xCoordinate < MAX_X_COORDINATE)) && (yCoordinate
        == MIN_Y_COORDINATE));
  }

  private static boolean isAboveSide(int xCoordinate, int yCoordinate) {
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