package main;

import static actions.TurnAction.HERBIVORE_PLACE;
import static actions.TurnAction.PREDATORS_PLACE;
import static actions.TurnAction.makeMoveForEverybody;
import static utility.MapUtility.getEntitySprite;
import static utility.MapUtility.getInfoByCreaturesCoordinates;
import static utility.OtherUtility.clearScreen;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import exception.EntityNotExistException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;


public final class Simulation {

  private final GameMap gameMap;
  private final Graph graph;
  public static final int MAX_X_COORDINATE = 10;
  public static final int MIN_X_COORDINATE = 1;
  public static final int MAX_Y_COORDINATE = 10;
  public static final int MIN_Y_COORDINATE = 1;
  public static final int PENULTIMATE_Y_COORDINATE = MAX_Y_COORDINATE - 1;
  public static final String EMPTY_SPRITE = "\uD83D\uDDFE";

  public Simulation(GameMap gameMap, Graph graph) {
    this.gameMap = gameMap;
    this.graph = graph;
  }

  // Запустить бесконечный цикл симуляции и рендеринга
  public void startSimulation() {
    while (true) {
      nextTurn();
    }
  }

  // Приостановить бесконечный цикл симуляции и рендеринга
  public void pauseSimulation() {

  }

  private void renderField(GameMap map) {
    for (int yCoordinate = MAX_Y_COORDINATE; yCoordinate >= MIN_Y_COORDINATE; yCoordinate--) {
      for (int xCoordinate = MIN_X_COORDINATE; xCoordinate <= MAX_X_COORDINATE; xCoordinate++) {
        Optional<String> sprite = Optional.empty();
        // Получаем сущность, может быть нулем
        Optional<Entity> entity = map.getEntityByCoordinate(
            new Coordination(xCoordinate, yCoordinate), Entity.class);
        if (entity.isPresent()) {
          sprite = getEntitySprite(entity.get());
        }
        System.out.print(sprite.orElse(EMPTY_SPRITE));

        printInfoBar(map, xCoordinate, yCoordinate);
      }
      System.out.println();
    }
    System.out.println();
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


  private void printInfoBar(GameMap map, int xCoordinate, int yCoordinate) {
    // GET INFO BY CREATURES COORDINATES
    List<Queue<Coordination>> array = getInfoByCreaturesCoordinates(map);
    Queue<Coordination> herbivoreCoordinates = new ArrayDeque<>(array.get(HERBIVORE_PLACE));
    Queue<Coordination> predatorCoordinates = new ArrayDeque<>(array.get(PREDATORS_PLACE));

    // Iterate herbivores and prints info
    printInfoByCreatures(map, herbivoreCoordinates, isTopCoordinate(xCoordinate, yCoordinate));

    // Iterate predators and prints info
    printInfoByCreatures(map, predatorCoordinates, isAfterTopCoordinate(xCoordinate, yCoordinate));

  }

  private void printInfoByCreatures(GameMap map, Queue<Coordination> creaturesCoordinates,
      boolean positionFlag) {
    if (positionFlag) {
      while (!creaturesCoordinates.isEmpty()) {
        Creature creature = map.getEntityByCoordinate(creaturesCoordinates.poll(), Creature.class)
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
