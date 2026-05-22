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
import entity.EntityType;
import exception.EntityNotExistException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import utility.MapUtility;


public class Simulation {

  private final GameMap gameMap;
  private final Graph graph;
  public static final int MAX_X_COORDINATE = 10;
  public static final int MIN_X_COORDINATE = 1;
  public static final int MAX_Y_COORDINATE = 10;
  public static final int MIN_Y_COORDINATE = 1;
  public static final int PENULTIMATE_Y_COORDINATE = MAX_Y_COORDINATE - 1;
  public static final String EMPTY_SPACE = "\uD83D\uDDFE";

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
        Optional<Entity> entity = map.getEntityByCoordinate(new Coordination(xCoordinate, yCoordinate), Entity.class);
        Optional<String> sprite = entity.map(MapUtility::getEntitySprite);
        sprite.ifPresentOrElse(
            System.out::print,
            () -> System.out.print(EMPTY_SPACE)
        );
//        if (entity != null) {
//          System.out.print(getEntitySprite(entity));
//          continue;
//        }
//        else {
//          System.out.print(EMPTY_SPACE);
//        }
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

  // TODO REFACTORING
  private void printInfoBar(GameMap map, int xCoordinate, int yCoordinate) {
    // GET INFO BY CREATURES COORDINATES
    List<Queue<Coordination>> array = getInfoByCreaturesCoordinates(map);
    Queue<Coordination> herbivoreCoordinates = new ArrayDeque<>(array.get(HERBIVORE_PLACE));
    Queue<Coordination> predatorCoordinates = new ArrayDeque<>(array.get(PREDATORS_PLACE));

    // Iterate herbivores and prints info
    if (isTopCoordinate(xCoordinate, yCoordinate)) {
      while (!herbivoreCoordinates.isEmpty()) {
        Creature creature = map.getEntityByCoordinate(herbivoreCoordinates.poll(), Creature.class).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));;
        System.out.print("[ " + getEntitySprite(creature) + " : " + creature.getHp() + " hp ]");
      }
    }
    // Iterate herbivores and prints info
    if (isAfterTopCoordinate(xCoordinate, yCoordinate)) {
      while (!predatorCoordinates.isEmpty()) {
        Creature creature = map.getEntityByCoordinate(predatorCoordinates.poll(), Creature.class).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));;
        System.out.print("[ " + getEntitySprite(creature) + " : " + creature.getHp() + " hp ]");
      }
    }
  }
  private boolean isTopCoordinate(int xCoordinate, int yCoordinate){
    return yCoordinate == MAX_Y_COORDINATE && xCoordinate == MAX_X_COORDINATE;
  }
  private boolean isAfterTopCoordinate(int xCoordinate, int yCoordinate){
    return yCoordinate == PENULTIMATE_Y_COORDINATE && xCoordinate == MAX_X_COORDINATE;
  }

}
