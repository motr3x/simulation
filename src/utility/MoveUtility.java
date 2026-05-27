package utility;

import static utility.PathFinder.useBfsAlgorithm;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import exception.EntityNotExistException;
import exception.GoalNotExistException;
import java.util.Deque;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import main.GameMap;
import main.Graph;

public final class MoveUtility {

  private MoveUtility() {
  }

  public static void chooseNextCell(GameMap gameMap, Graph graph,
      Coordination creatureCoordinate) {
    // пока очередь со всеми существами не пуста
    // получение одного существа из очереди существ (очередь - все существа на карте, ходят по очереди)
    Creature creature = gameMap.getEntityByCoordinate(creatureCoordinate, Creature.class).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));;

    // Проход по всем сущностей:
    // если ход травоядного(т.е creature instanceOf Herbivore)  - берет случайную траву(goal instanceOf Grass)
    // если ход хищника(т.е creature instanceOf Predator) - берет случайного травоядного(goal instanceOf Herbivore)
    // Присваивание переменной goal значения
    Coordination goal = getGoalCoordinate(gameMap, creature).orElseThrow(() -> new GoalNotExistException("Goal doesn't exist"));

    //TODO need to refactor
    //постройка пути от существо -> к цель ( herbivore -> 1,1 -> 1,2 -> grass)
    Optional<Deque<Coordination>> track = useBfsAlgorithm(graph.getGraph(),
        gameMap.getCoordinateByEntity(creature).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist")), goal, gameMap);

    //Если нет никакого пути (мешают препятствие и т.д), то return
    if (track.isEmpty()) {
      return;
    }

    // herbivore coordinate 1,2
    Coordination current = track.get().poll();
    // 1,1 coordinate
    Coordination follow = track.get().poll();

    // передвинутся herbivore на 1,1
    makeMove(gameMap, current, follow);

  }

  private static void makeMove(GameMap map, Coordination creatureCoordinate,
      Coordination followCoordinate) {
    // если цель то, выполняется действие makeAction
    // а после все второстепенные действий makeDescendant and makeHungry
//    Entity entity = map.getEntityByCoordinate(creatureCoordinate);
    Creature creature = map.getEntityByCoordinate(creatureCoordinate, Creature.class).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));;
//    if(entity instanceof Predator || entity instanceof Herbivore) {
//      Creature creature = (Creature) map.getEntityByCoordinate(creatureCoordinate);
    if (creature.isGoal(map, followCoordinate)) {
      creature.makeAttack(map, followCoordinate);
      //makeRoutine(map, creatureCoordinate);
      return;
//      }
    }
//    if (moveable.isGoal(map, followCoordinate)) {
//      actionAble.makeAction(map, creatureCoordinate, followCoordinate);
//      makeRoutine(map, creatureCoordinate);
//      return;
//    }
    changeCell(map, creatureCoordinate, followCoordinate);
    //makeRoutine(map, followCoordinate);
  }

  private static void changeCell(GameMap map, Coordination creatureCoordinate,
      Coordination followCoordinate) {
    map.setMap(followCoordinate, map.getEntityByCoordinate(creatureCoordinate, Creature.class).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist")));
    map.removeEntityByCoordinate(creatureCoordinate);
  }

  public static void makeRoutine(GameMap map, Coordination creatureCoordinate) {
    Creature creature = map.getEntityByCoordinate(creatureCoordinate, Creature.class).orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));;
    creature.reproduce(map);
    creature.starve(map);
  }

  private static Optional<Coordination> getGoalCoordinate(GameMap map, Creature creature) {
    Optional<Coordination> goal = Optional.empty();
    for (Entry<Coordination, Entity> element : map.getEntrySet()) {
      if (creature.isGoal(map, element.getKey())) {
        goal = Optional.of(element.getKey());
      }
    }
    return goal;
  }
}
