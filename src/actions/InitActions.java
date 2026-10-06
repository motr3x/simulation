package actions;

import entity.Coordinates;
import entity.EntityType;
import java.util.List;
import main.GameMap;
import main.Graph;
import utility.EntitySpawner;


public final class InitActions {

  private InitActions() {
  }

  public static final int MAX_X_COORDINATE = 10;
  public static final int MIN_X_COORDINATE = 1;
  public static final int MAX_Y_COORDINATE = 10;
  public static final int MIN_Y_COORDINATE = 1;

  public static final int INIT_COUNT_OF_HERBIVORE = 3;
  public static final int INIT_COUNT_OF_PREDATOR = 3;
  public static final int INIT_COUNT_OF_GRASS = 7;
  public static final int INIT_COUNT_OF_TREE = 2;
  public static final int INIT_COUNT_OF_ROCK = 1;

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
          graph.set(new Coordinates(xCoordinate, yCoordinate),
              List.of(new Coordinates(xCoordinate, yCoordinate + 1),
                  new Coordinates(xCoordinate + 1, yCoordinate)));
          continue;
        }
       
        if (isUpperLeftCorner(xCoordinate, yCoordinate)) {
          graph.set(new Coordinates(xCoordinate, yCoordinate),
              List.of(new Coordinates(xCoordinate, yCoordinate - 1),
                  new Coordinates(xCoordinate + 1, yCoordinate)));
          continue;
        }
     
        if (isUpperRightCorner(xCoordinate, yCoordinate)) {
          graph.set(new Coordinates(xCoordinate, yCoordinate),
              List.of(new Coordinates(xCoordinate - 1, yCoordinate),
                  new Coordinates(xCoordinate, yCoordinate - 1)));
          continue;
        }
     
        if (isLowerRightCorner(xCoordinate, yCoordinate)) {
          graph.set(new Coordinates(xCoordinate, yCoordinate),
              List.of(new Coordinates(xCoordinate - 1, yCoordinate),
                  new Coordinates(xCoordinate, yCoordinate + 1)));
          continue;
        }
        
        if (isLeftSide(xCoordinate, yCoordinate)) {
          graph.set(new Coordinates(xCoordinate, yCoordinate),
              List.of(new Coordinates(xCoordinate, yCoordinate - 1),
                  new Coordinates(xCoordinate + 1, yCoordinate),
                  new Coordinates(xCoordinate, yCoordinate + 1)));
          continue;
        }
        
        if (isAboveSide(xCoordinate, yCoordinate)) {
          graph.set(new Coordinates(xCoordinate, yCoordinate),
              List.of(new Coordinates(xCoordinate - 1, yCoordinate),
                  new Coordinates(xCoordinate, yCoordinate - 1),
                  new Coordinates(xCoordinate + 1, yCoordinate)));
          continue;
        }
        
        if (isBottomSide(xCoordinate, yCoordinate)) {
          graph.set(new Coordinates(xCoordinate, yCoordinate),
              List.of(new Coordinates(xCoordinate - 1, yCoordinate),
                  new Coordinates(xCoordinate, yCoordinate + 1),
                  new Coordinates(xCoordinate + 1, yCoordinate)));
          continue;
        }
        
        if (isRightSide(xCoordinate, yCoordinate)) {
          graph.set(new Coordinates(xCoordinate, yCoordinate),
              List.of(new Coordinates(xCoordinate, yCoordinate - 1),
                  new Coordinates(xCoordinate, yCoordinate + 1),
                  new Coordinates(xCoordinate - 1, yCoordinate)));
          continue;
        }
        
        graph.set(new Coordinates(xCoordinate, yCoordinate),
            List.of(new Coordinates(xCoordinate, yCoordinate + 1),
                new Coordinates(xCoordinate + 1, yCoordinate),
                new Coordinates(xCoordinate, yCoordinate - 1),
                new Coordinates(xCoordinate - 1, yCoordinate)));
      }
    }
  }

  private static void initDefaultTree(GameMap gameMap) {
    int countOfTree = 0;
    while (countOfTree < INIT_COUNT_OF_TREE) {
      SPAWNER.spawnToMap(gameMap, EntityType.TREE);
      countOfTree++;
    }
  }

  private static void initDefaultRock(GameMap gameMap) {
    int countOfRock = 0;
    while (countOfRock < INIT_COUNT_OF_ROCK) {
      SPAWNER.spawnToMap(gameMap, EntityType.ROCK);
      countOfRock++;
    }
  }

  private static void initStartGrass(GameMap gameMap) {
    int countOfGrass = 0;
    while (countOfGrass < INIT_COUNT_OF_GRASS) {
      SPAWNER.spawnToMap(gameMap, EntityType.GRASS);
      countOfGrass++;
    }
  }

  private static void initStartPredator(GameMap gameMap) {
    int countOfPredator = 0;
    while (countOfPredator < INIT_COUNT_OF_PREDATOR) {
      SPAWNER.spawnToMap(gameMap, EntityType.PREDATOR);
      countOfPredator++;
    }
  }

  private static void initStartHerbivore(GameMap gameMap) {
    int countOfHerbivore = 0;
    while (countOfHerbivore < INIT_COUNT_OF_HERBIVORE) {
      SPAWNER.spawnToMap(gameMap, EntityType.HERBIVORE);
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