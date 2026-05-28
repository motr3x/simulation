package main;

import static actions.InitActions.initGraph;
import static actions.InitActions.initMap;
import static actions.TurnAction.makeMoveForEverybody;
import static config.SimulationConfig.MAX_X_COORDINATE;
import static config.SimulationConfig.MAX_Y_COORDINATE;
import static config.SimulationConfig.MIN_X_COORDINATE;
import static config.SimulationConfig.MIN_Y_COORDINATE;
import static config.SimulationConfig.PENULTIMATE_Y_COORDINATE;
import static utility.OtherUtility.clearScreen;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import entity.SpriteType;
import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import exception.EntityNotExistException;
import java.util.Optional;
import java.util.Queue;


public final class Simulation {


  private final GameMap gameMap;
  private final Graph graph;
  private int roundCount = 0;

  public Simulation(GameMap gameMap, Graph graph) {
    this.gameMap = gameMap;
    this.graph = graph;
    initMap(gameMap);
    initGraph(graph);
  }

  // Запустить бесконечный цикл симуляции и рендеринга
  public void startSimulation() {
    renderField(gameMap);
    while (true) {
      nextTurn();
      ++roundCount;
    }
  }

  // Приостановить бесконечный цикл симуляции и рендеринга
  public void pauseSimulation() {

  }

  //Просимулировать и отрендерить один ход
  public void nextTurn() {
    renderField(gameMap);
    makeMoveForEverybody(gameMap, graph);
    try {
      Thread.sleep(500L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    renderField(gameMap);
    clearScreen();
  }


  private void renderField(GameMap map) {
    for (int yCoordinate = MAX_Y_COORDINATE; yCoordinate >= MIN_Y_COORDINATE; yCoordinate--) {
      for (int xCoordinate = MIN_X_COORDINATE; xCoordinate <= MAX_X_COORDINATE; xCoordinate++) {
        Optional<String> sprite = Optional.empty();
        // Получаем сущность, может быть нулем
        Optional<Entity> entity = map.get(
            new Coordination(xCoordinate, yCoordinate), Entity.class);
        if (entity.isPresent()) {
          sprite = getEntitySprite(entity.get());
        }
        System.out.print(sprite.orElse(SpriteType.EMPTY.getCode()));

        printInfoBar(map, xCoordinate, yCoordinate);
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

  private void printInfoBar(GameMap map, int xCoordinate, int yCoordinate) {
    Queue<Coordination> predatorCoordinates = gameMap.getPositions(gameMap, Predator.class);
    Queue<Coordination> herbivoreCoordinates = gameMap.getPositions(gameMap, Herbivore.class);

    printInfoByCreatures(map, herbivoreCoordinates, isTopCoordinate(xCoordinate, yCoordinate));

    printInfoByCreatures(map, predatorCoordinates, isAfterTopCoordinate(xCoordinate, yCoordinate));

  }

  private void printInfoByCreatures(GameMap map, Queue<Coordination> creaturesCoordinates,
      boolean positionFlag) {
    if (positionFlag) {
      while (!creaturesCoordinates.isEmpty()) {
        Creature creature = map.get(creaturesCoordinates.poll(), Creature.class)
            .orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
        Optional<String> sprite = getEntitySprite(creature);
        sprite.ifPresent(s -> System.out.print("[ " + s + " : " + creature.getHp() + " hp ]"));
      }
    }
  }

  private boolean isTopCoordinate(int xCoordinate, int yCoordinate) {
    return yCoordinate == MAX_Y_COORDINATE && xCoordinate == MAX_X_COORDINATE;
  }

  private boolean isAfterTopCoordinate(int xCoordinate, int yCoordinate) {
    return yCoordinate == PENULTIMATE_Y_COORDINATE && xCoordinate == MAX_X_COORDINATE;
  }

}
