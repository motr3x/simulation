package main;



import entity.Coordinates;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import entity.SpriteType;
import entity.environmentalentities.Grass;
import entity.environmentalentities.Rock;
import entity.environmentalentities.Tree;
import java.util.Optional;


public final class Simulation {

  private static final String ROUND_OUTPUT = "Round:";
  private static final int SLEEP_TIME = 1200;
  private static final String STOP_INSTRUCTION = "PRESS S TO STOP";
  private static final String CLEAR = "\033[H\033[2J";
  private final GameMap gameMap;
  private final Graph graph;
  private int roundCount = 0;
  private Boolean stopFlag;

  public static final int MAX_X_COORDINATE = 10;
  public static final int MIN_X_COORDINATE = 1;
  public static final int MAX_Y_COORDINATE = 10;
  public static final int MIN_Y_COORDINATE = 1;


  public Simulation(GameMap gameMap, Graph graph, Boolean stopFlag) {
    this.gameMap = gameMap;
    this.graph = graph;
    this.stopFlag = stopFlag;

    actions.InitActions.initMap(gameMap);
    actions.InitActions.initGraph(graph);
  }

  public void startSimulation() {
    while (!Boolean.TRUE.equals(stopFlag)) {
      nextTurn();
      sleep();
    }
  }

  public void sleep() {
    try {
      Thread.sleep(SLEEP_TIME);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }


  public void pauseSimulation() {
    stopFlag = Boolean.TRUE;
  }

  public void continueSimulation() {
    stopFlag = Boolean.FALSE;
  }

  public void nextTurn() {
    clearScreen();
    System.out.println(ROUND_OUTPUT + roundCount++);
    renderField(gameMap);
    actions.TurnAction.makeMoveForEverybody(gameMap, graph);
  }

  private void clearScreen() {
    System.out.print(CLEAR);
    System.out.flush();
  }

  private void renderField(GameMap gameMap) {
    for (int yCoordinate = MAX_Y_COORDINATE; yCoordinate >= MIN_Y_COORDINATE; yCoordinate--) {
      for (int xCoordinate = MIN_X_COORDINATE; xCoordinate <= MAX_X_COORDINATE; xCoordinate++) {
        Optional<String> sprite = Optional.empty();
        Optional<Entity> entity = gameMap.get(
            new Coordinates(xCoordinate, yCoordinate), Entity.class);
        if (entity.isPresent()) {
          sprite = getEntitySprite(entity.get());
        }
        System.out.print(sprite.orElse(SpriteType.EMPTY.getCode()));
        if (xCoordinate == MAX_X_COORDINATE && yCoordinate == MIN_Y_COORDINATE) {
          System.out.print(STOP_INSTRUCTION);
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  private Optional<String> getEntitySprite(Entity entity) {
    return switch (entity) {
      case Predator predator -> Optional.of(SpriteType.PREDATOR.getCode());
      case Herbivore herbivore -> Optional.of(SpriteType.HERBIVORE.getCode());
      case Grass grass -> Optional.of(SpriteType.GRASS.getCode());
      case Rock rock -> Optional.of(SpriteType.ROCK.getCode());
      case Tree tree -> Optional.of(SpriteType.TREE.getCode());
      case null, default -> Optional.empty();
    };
  }
}
