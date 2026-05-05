package utility;


import static main.Simulation.MAX_X_COORDINATE;
import static main.Simulation.MAX_Y_COORDINATE;
import static main.Simulation.MIN_X_COORDINATE;
import static main.Simulation.MIN_Y_COORDINATE;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import entity.staticObject.Grass;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import main.GameMap;

public class MapUtility {

  public static final int MIN_COUNT_OF_GRASS = 3;
  public static final int MIN_COUNT_OF_HERBIVORE = 2;
  public static final int MIN_COUNT_OF_PREDATOR = 2;
  public static final int PREDATORS_PLACE = 1;
  public static final int HERBIVORE_PLACE = 0;

//  public static Queue<Coordination> getInfoByHerbivores(GameMap map) {
//    Queue<Coordination> herbivoreCoordinates = new ArrayDeque<>();
//    for (Map.Entry<Coordination, Entity> element : map.getMap().entrySet()) {
//      if (element.getValue() instanceof Herbivore) {
//        herbivoreCoordinates.add(element.getKey());
//      }
//    }
//    if (herbivoreCoordinates.size() < MIN_COUNT_OF_HERBIVORE) {
//      Herbivore.create(map);
//    }
//    return herbivoreCoordinates;
//  }
public static void clearScreen() {
  // \033[H moves the cursor to the top-left corner
  // \033[2J clears the entire screen
  System.out.print("\033[H\033[2J");
  System.out.flush();
}


  public static List<Queue<Coordination>> getCreaturesCoordinates(GameMap map) {

    Queue<Coordination> herbivoreCoordinates = new ArrayDeque<>();
    Queue<Coordination> predatorCoordinates = new ArrayDeque<>();
//    Queue<Coordination> grassCoordinates = new ArrayDeque<>();

    for (Map.Entry<Coordination, Entity> element : map.getMap().entrySet()) {
      if (element.getValue() instanceof Herbivore) {
        herbivoreCoordinates.add(element.getKey());
        continue;
      }
      if (element.getValue() instanceof Predator) {
        predatorCoordinates.add(element.getKey());
        continue;
      }
//      if (element.getValue() instanceof Grass) {
//        grassCoordinates.add(element.getKey());
//      }
    }
    creatMissingCreatures(herbivoreCoordinates, predatorCoordinates, map);
//    if (herbivoreCoordinates.size() < MIN_COUNT_OF_HERBIVORE) {
//      Herbivore.create(map);
//    }
//    if (predatorCoordinates.size() < MIN_COUNT_OF_PREDATOR) {
//      Predator.create(map);
//    }
//    if (grassCoordinates.size() < MIN_COUNT_OF_GRASS) {
//      Grass.create(map);
//    }

    return List.of(herbivoreCoordinates, predatorCoordinates);
  }
  private static void creatMissingCreatures(Queue<Coordination> herbivoreCoordinates, Queue<Coordination> predatorCoordinates, GameMap map){
    if (herbivoreCoordinates.size() < MIN_COUNT_OF_HERBIVORE) {
      Herbivore.create(map);
    }
    if (predatorCoordinates.size() < MIN_COUNT_OF_PREDATOR) {
      Predator.create(map);
    }
  }
  // TODO REFACTORING
  public static void printInfoBar(GameMap map, int xCoordinate, int yCoordinate){
    List<Queue<Coordination>> array = getCreaturesCoordinates(map);
    Queue<Coordination> herbivoreCoordinates = new ArrayDeque<>(array.get(HERBIVORE_PLACE));
    Queue<Coordination> predatorCoordinates = new ArrayDeque<>(array.get(PREDATORS_PLACE));
    if(yCoordinate == MAX_Y_COORDINATE && xCoordinate == MAX_X_COORDINATE) {
      while (!herbivoreCoordinates.isEmpty()) {
        Creature creature = (Creature) map.getMap().get(herbivoreCoordinates.poll());
        System.out.print("[ " + creature.getSprite() + " : " + creature.getHp() + " hp ]");
      }
    }
    if(yCoordinate == MAX_Y_COORDINATE - 1 && xCoordinate == MAX_X_COORDINATE) {
      while (!predatorCoordinates.isEmpty()) {
        Creature creature = (Creature) map.getMap().get(predatorCoordinates.poll());
        System.out.print("[ " + creature.getSprite() + " : " + creature.getHp() + " hp ]");
      }
    }
  }
//  public static Queue<Coordination> getInfoByPredator(GameMap map) {
//    Queue<Coordination> predatorCoordinates = new ArrayDeque<>();
//    for (Map.Entry<Coordination, Entity> element : map.getMap().entrySet()) {
//      if (element.getValue() instanceof Predator) {
//        predatorCoordinates.add(element.getKey());
//      }
//    }
//    if (predatorCoordinates.size() < MIN_COUNT_OF_PREDATOR) {
//      Predator.create(map);
//    }
//    return predatorCoordinates;
//  }
//
  public static void getInfoByGrass(GameMap map) {
    Queue<Coordination> grassCoordinates = new ArrayDeque<>();
    for (Map.Entry<Coordination, Entity> element : map.getMap().entrySet()) {
      if (element.getValue() instanceof Grass) {
        grassCoordinates.add(element.getKey());
      }
    }
    if (grassCoordinates.size() < MIN_COUNT_OF_GRASS) {
      Grass.create(map);
    }
  }

  public static Coordination getRandomCoordinate(){
    Random random = new Random();
    int xCoordinate = random.nextInt(MIN_X_COORDINATE, MAX_X_COORDINATE);
    int yCoordinate = random.nextInt(MIN_Y_COORDINATE, MAX_Y_COORDINATE);
    return new Coordination(xCoordinate, yCoordinate);
  }

  public static boolean fieldIsEmpty(Coordination coordination, GameMap map){
    return (map.getMap().get(coordination) == null);
  }

}
