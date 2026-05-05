package entity;

import entity.staticObject.Grass;
import exception.HerbivoreInExtinct;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import main.GameMap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import main.Graph;


public abstract class Creature extends Entity {

  private final int speed;
  private int hp;

  abstract protected boolean checkBarrier(GameMap map, Coordination node);

  abstract protected boolean isGoal(GameMap map, Coordination followCoordinate);

  abstract protected void upHp(GameMap map, Coordination creatureCoordinate);
//  protected abstract void setCoordinateSpecificCreature(Coordination outdatedCoordinate, Coordination newCoordinate, GameMap map);

  abstract protected void makeAction(GameMap map, Coordination creatureCoordinate,
      Coordination followCoordinate);

//  private void touchGoal(GameMap map, Coordination creatureCoordinate,
//      Coordination goalCoordinate){}

  private static boolean isPredatorAndGoalIsHerbivore(Creature creature, Map.Entry<Coordination, Entity> element){
    return (creature instanceof Predator && element.getValue() instanceof Herbivore);
  }

  public Optional<Deque<Coordination>> makeTrack(Map<Coordination, List<Coordination>> graph,
      Coordination start, Coordination goal, GameMap map) {

    Set<Coordination> visited = new LinkedHashSet<>();
    Deque<List<Coordination>> queue = new ArrayDeque<>();

    queue.add(new ArrayList<>(List.of(start)));

    while (!queue.isEmpty()) {
      Deque<Coordination> path = new ArrayDeque<>(queue.getFirst());
      queue.removeFirst();// берем путь
      Coordination node = path.getLast(); // последний элемент пути
      if(node != start){
        if (checkBarrier(map, node)) {
          continue;
        }
      }

      if (visited.contains(node)) {
        continue;
      }

      visited.add(node); // добавляем

      if (node.equals(goal)) {
        return Optional.of(path);
      }

      List<Coordination> neighbors = graph.get(node);
      if (neighbors == null) {
        continue;
      }
      for (Coordination neighbor : neighbors) {
        if (visited.contains(neighbor)) {
          continue;
        }
        List<Coordination> newPath = new ArrayList<>(path);
        newPath.add(neighbor);
        queue.add(newPath);
      }
    }
    return Optional.empty();
  }

  public static void followTrack(GameMap gameMap, Graph graph, Queue<Coordination> creaturesCoordinates){
    while (!creaturesCoordinates.isEmpty()) {
      // init goal
      Coordination goal = null;

      // получение одного существа из очереди существ (очередь - все существа на карте, ходят по очереди)
      Creature creature = (Creature) gameMap.getMap().get(creaturesCoordinates.poll());
      if(creature == null){
        throw new HerbivoreInExtinct("Травоядные вымерли");
      }
      int a = 123;
      // поиск подходящей цели
      for (Map.Entry<Coordination, Entity> element : gameMap.getMap().entrySet()) {
        if (isPredatorAndGoalIsHerbivore(creature, element)) {
          goal = element.getKey();
        }
        if (creature instanceof Herbivore && element.getValue() instanceof Grass) {
          goal = element.getKey();
        }
      }

      // постройка пути от существо -> к цель ( herbivore -> 1,1 -> 1,2 -> grass)
      Deque<Coordination> track = null;
      if (creature.makeTrack(graph.getGraph(),
          getEntityCoordinate(gameMap, creature), goal, gameMap).isPresent()) {
        track = new ArrayDeque<>(creature.makeTrack(graph.getGraph(),
            getEntityCoordinate(gameMap, creature), goal, gameMap).get());
      }
      if(track == null) return;

      // herbivore coordinate 1,2
      Coordination current = track.poll();
      // 1,1 coordinate
      Coordination follow = track.poll();
      // передвинутся herbivore на 1,1
      creature.moving(gameMap, current, follow);

    }
  }

  public void moving(GameMap map, Coordination creatureCoordinate,
      Coordination followCoordinate) {
    // если цель то выполняется дествие
    if(isGoal(map, followCoordinate)){
      makeAction(map, creatureCoordinate, followCoordinate);
      return;
    }
//    Entity entity = fullingEmptyEntity(map, followCoordinate);
    // иначе передвинутся herbivore на 1,1
    map.setMap(followCoordinate, map.getMap().get(creatureCoordinate));
    map.getMap().remove(creatureCoordinate);
//    setCoordinateSpecificCreature(creatureCoordinate, followCoordinate, map);
  }


//  public static Entity fullingEmptyEntity(GameMap map, Coordination coordinate) {
//    Entity entity = map.getMap().get(coordinate);
//    if (entity == null) {
//      map.setMap(coordinate, null);
//    }
//    return entity;
//  }


  @Override
  public String toString() {
    return sprite;
  }

  public Creature(int speed, int hp) {
    this.speed = speed;
    this.hp = hp;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }
}
