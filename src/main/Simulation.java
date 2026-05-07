package main;

import static actions.TurnAction.HERBIVORE_PLACE;
import static actions.TurnAction.PREDATORS_PLACE;
import static actions.TurnAction.makeMoveForEverybody;
import static utility.MapUtility.getCreaturesCoordinates;
import static utility.OtherUtility.clearScreen;

import entity.Coordination;
import entity.Creature;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;


public class Simulation {

  private final GameMap gameMap;
  private final Graph graph;
  public static final int MAX_X_COORDINATE = 10;
  public static final int MIN_X_COORDINATE = 1;
  public static final int MAX_Y_COORDINATE = 10;
  public static final int MIN_Y_COORDINATE = 1;
  private static final String EMPTY_SPACE = "\uD83D\uDDFE";

  // Вывод актуального состояния карты
  public static void renderField(GameMap map) {
    for (int yCoordinate = MAX_Y_COORDINATE; yCoordinate >= MIN_Y_COORDINATE; yCoordinate--) {
      for (int xCoordinate = MIN_X_COORDINATE; xCoordinate <= MAX_X_COORDINATE; xCoordinate++) {
//                for(Map.Entry<Coordination, Entity> entity : map.getMap().entrySet()){
//                    if(entity.getKey().getX() == x && entity.getKey().getY() == y) System.out.print(entity.getValue().toString());
//
//                }

        if (map.getEntityByCoordinate(new Coordination(xCoordinate, yCoordinate)) != null) {
          System.out.print(map.getEntityByCoordinate(new Coordination(xCoordinate, yCoordinate)));
          continue;
        }
        System.out.print(EMPTY_SPACE);
        // TODO REFACTORING
        printInfoBar(map, xCoordinate, yCoordinate);
      }
      System.out.println();
    }
    System.out.println();
    try {
      Thread.sleep(500L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // TODO REFACTORING
  public static void printInfoBar(GameMap map, int xCoordinate, int yCoordinate) {
    List<Queue<Coordination>> array = getCreaturesCoordinates(map);
    Queue<Coordination> herbivoreCoordinates = new ArrayDeque<>(array.get(HERBIVORE_PLACE));
    Queue<Coordination> predatorCoordinates = new ArrayDeque<>(array.get(PREDATORS_PLACE));
    if (yCoordinate == MAX_Y_COORDINATE && xCoordinate == MAX_X_COORDINATE) {
      while (!herbivoreCoordinates.isEmpty()) {
        Creature creature = (Creature) map.getEntityByCoordinate(herbivoreCoordinates.poll());
        System.out.print("[ " + creature.toString() + " : " + creature.getHp() + " hp ]");
      }
    }
    if (yCoordinate == MAX_Y_COORDINATE - 1 && xCoordinate == MAX_X_COORDINATE) {
      while (!predatorCoordinates.isEmpty()) {
        Creature creature = (Creature) map.getEntityByCoordinate(predatorCoordinates.poll());
        System.out.print("[ " + creature.toString() + " : " + creature.getHp() + " hp ]");
      }
    }
  }

  public Simulation(GameMap gameMap, Graph graph) {
    this.gameMap = gameMap;
    this.graph = graph;
  }

  //Просимулировать и отрендерить один ход
  void nextTurn() {
    makeMoveForEverybody(gameMap, graph);
    renderField(gameMap);
    clearScreen();
  }

  // Запустить бесконечный цикл симуляции и рендеринга
  void startSimulation() {
    while (true) {
      makeMoveForEverybody(gameMap, graph);
      renderField(gameMap);
      clearScreen();
    }
  }

  // Приостановить бесконечный цикл симуляции и рендеринга
  void pauseSimulation() {

  }
}
