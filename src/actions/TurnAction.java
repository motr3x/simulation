package actions;

import static utility.MapUtility.getCreaturesCoordinates;

import entity.Coordination;
import entity.herbivore.Herbivore;
import entity.predator.Predator;
import entity.staticObject.Grass;
import java.util.Queue;
import main.GameMap;
import main.Graph;


// действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы или травоядных, если их осталось слишком мало
public class TurnAction {

  public static final int PREDATORS_PLACE = 1;
  public static final int HERBIVORE_PLACE = 0;
  private static final int GRASS_PLACE = 2;
  private static final int MIN_COUNT_OF_GRASS = 10;

  //передвижение всех существ
  public static void makeMoveForEverybody(GameMap gameMap, Graph graph) {
    // info by creatures coordinate should update every
    Predator.makeMove(gameMap, graph);
    Herbivore.makeMove(gameMap, graph);
    createMissingGrass(getCreaturesCoordinates(gameMap).get(GRASS_PLACE), gameMap);
  }

  private static void createMissingGrass(Queue<Coordination> grassCoordinates, GameMap map) {
    if (!isGrassCountEnough(grassCoordinates)) {
      Grass.create(map);
    }
  }

  private static boolean isGrassCountEnough(Queue<Coordination> grassCoordinates) {
    return (grassCoordinates.size() == MIN_COUNT_OF_GRASS);
  }
}


