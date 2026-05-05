package actions;

import static entity.Creature.followTrack;
import static main.Simulation.MAX_X_COORDINATE;
import static main.Simulation.MAX_Y_COORDINATE;
import static main.Simulation.MIN_X_COORDINATE;
import static main.Simulation.MIN_Y_COORDINATE;
import static utility.MapUtility.HERBIVORE_PLACE;
import static utility.MapUtility.PREDATORS_PLACE;
import static utility.MapUtility.getCreaturesCoordinates;
import static utility.MapUtility.getInfoByGrass;

import entity.Coordination;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import entity.staticObject.Grass;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import main.GameMap;
import main.Graph;

import java.util.ArrayDeque;
import java.util.Map;


// действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы или травоядных, если их осталось слишком мало
public class TurnAction {

//  private static int count = 0;
//  public static Coordination current;
//  public static Coordination follow;
//  public Graph copyOfGraph = new Graph();
//  Creature predator = new Predator(10, 10, 1);


  //  public void makeHerbivoresMove(GameMap gameMap, Graph graph, Queue<Coordination> herbivoresCoordinates) {
//    while (!herbivoresCoordinates.isEmpty()) {
//      Coordination findGrassCoordinate = null;
//
//      // поиск травы
//      for (Map.Entry<Coordination, Entity> element : gameMap.getMap().entrySet()) {
//        if (element.getValue() instanceof Grass) {
//          findGrassCoordinate = element.getKey();
//        }
//      }
//
//      // получение травоядного
//      Creature herbivore = (Creature) gameMap.getMap().get(herbivoresCoordinates.poll());
//
//      // трак по травояднйы -> трава
//      Deque<Coordination> track = herbivore.makeTrack(graph.getGraph(),
//          getEntityCoordinate(gameMap, herbivore), findGrassCoordinate, gameMap);
//
//      Coordination current = track.poll();
//      Coordination follow = track.poll();
//      herbivore.moving(gameMap, current, follow);
//
//    }
//  }
  //передвижение всех существ
  public static void makeMoveForEverybody(GameMap gameMap, Graph graph) {
    List<Queue<Coordination>> array = getCreaturesCoordinates(gameMap);
    followTrack(gameMap, graph, array.get(PREDATORS_PLACE));
    followTrack(gameMap, graph, array.get(HERBIVORE_PLACE));
    getInfoByGrass(gameMap);
  }
//    Deque<Coordination> track = getTrack(gameMap, graph);
//
//    if (count == 0) {
//      current = track.poll();
//      follow = track.poll();
//      herbivore.moving(gameMap, current, follow);
//      ++count;
//    } else {
//      current = follow;
//      follow = track.poll();
//      herbivore.moving(gameMap, current, follow);
//    }

//}

  //  private void makePredatorMove(GameMap gameMap, Graph graph, Queue<Coordination> predatorCoordinates) {
//    while (!predatorCoordinates.isEmpty()) {
//      Coordination findGrassCoordinate = null;
//
//      // поиск травы
//      for (Map.Entry<Coordination, Entity> element : gameMap.getMap().entrySet()) {
//        if (element.getValue() instanceof Herbivore) {
//          findGrassCoordinate = element.getKey();
//        }
//      }
//
//      // получение травоядного
//      Creature predator = (Creature) gameMap.getMap().get(predatorCoordinates.poll());
//
//      // трак по травояднйы -> трава
//      Deque<Coordination> track = predator.makeTrack(graph.getGraph(),
//          getEntityCoordinate(gameMap, predator), findGrassCoordinate, gameMap);
//
//      Coordination current = track.poll();
//      Coordination follow = track.poll();
//      predator.moving(gameMap, current, follow);
//
//    }


  //добавить траву
//  public static void addGrass(GameMap map) {
//    Coordination coordination = getRandomCoordinate();
//    if (fieldIsEmpty(coordination, map)) {
//      Grass.create(coordination, map);
//    }
//  }
  //добавить травоядных
//  public static void addHerbivore(GameMap map) {
//    Coordination coordination = getRandomCoordinate();
//    if (fieldIsEmpty(coordination, map)) {
//      Herbivore.setToMap(coordination, map);
//    }
//  }
//  public static void addPredator(GameMap map) {
//    Coordination coordination = getRandomCoordinate();
//    if (fieldIsEmpty(coordination, map)) {
//      Predator.create(coordination, map);
//    }
//  }

//  public static Coordination getRandomCoordinate(){
//    Random random = new Random();
//    int xCoordinate = random.nextInt(MIN_X_COORDINATE, MAX_X_COORDINATE);
//    int yCoordinate = random.nextInt(MIN_Y_COORDINATE, MAX_Y_COORDINATE);
//    return new Coordination(xCoordinate, yCoordinate);
//  }
//
//  public static boolean fieldIsEmpty(Coordination coordination, GameMap map){
//    return (map.getMap().get(coordination) == null);
//  }
//  private static void createGrass(Coordination coordination, GameMap map){
//    map.setMap(coordination, new Grass());
//  }
//  private static void createHerbivore(Coordination coordination, GameMap map){
//    map.setMap(coordination, new Herbivore(10,10));
//  }
//  private static void createPredator(Coordination coordination, GameMap map){
//    map.setMap(coordination, new Predator(10,10,5));
//  }

}
//  public Map<Coordination, List<Coordination>> removePredatorFromGraphForHerbivore(Graph graph) {
//    copyOfGraph.setAllGraph(graph.getGraph());
//    //Track without Predator
//    copyOfGraph.getGraph().remove(new Coordination(1, 2));
//    for (Map.Entry<Coordination, List<Coordination>> pair : copyOfGraph.getGraph().entrySet()) {
//      if (pair.getValue().equals(new Coordination(1, 2))) {
//        copyOfGraph.getGraph().remove(pair.getKey(), new Coordination(1, 2));
//      }
//
//    }
//    return copyOfGraph.getGraph();
//  }



