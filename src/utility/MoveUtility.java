package utility;

import static entity.Creature.makeDescendant;
import static entity.Creature.makeHungry;
import static entity.Creature.moving;
import static utility.MapUtility.getEntityCoordinate;
import static utility.PathFinder.useBfsAlgorithm;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import entity.ability.ActionAble;
import entity.ability.MoveAble;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import main.GameMap;
import main.Graph;

public class MoveUtility {

  public static void followTrack(GameMap gameMap, Graph graph,
      Queue<Coordination> creaturesCoordinates, MoveAble moveable, ActionAble actionAble) {
    // пока очередь со всеми существами не пуста
    while (!creaturesCoordinates.isEmpty()) {

      // Переменная для цели
      Coordination goal = null;

      // получение одного существа из очереди существ (очередь - все существа на карте, ходят по очереди)
      Creature creature = (Creature) gameMap.getEntityByCoordinate(creaturesCoordinates.poll());

      // Проход по всем сущностей:
      // если ход травоядного(т.е creature instanceOf Herbivore)  - берет случайную траву(goal instanceOf Grass)
      // если ход хищника(т.е creature instanceOf Predator) - берет случайного травоядного(goal instanceOf Herbivore)
      // Присваивание переменной goal значения
      goal = getGoalCoordinate(gameMap, creature, moveable);

      //TODO need to refactor
      //постройка пути от существо -> к цель ( herbivore -> 1,1 -> 1,2 -> grass)
      Optional<Deque<Coordination>> track = useBfsAlgorithm(graph.getGraph(),
          getEntityCoordinate(gameMap, creature), goal, gameMap, moveable);

      //Если нет никакого пути (мешают препятствие и т.д), то return
      if (track.isEmpty()) {
        return;
      }

      // herbivore coordinate 1,2
      Coordination current = track.get().poll();
      // 1,1 coordinate
      Coordination follow = track.get().poll();

      // передвинутся herbivore на 1,1
      makeMove(gameMap, current, follow, moveable, actionAble);

    }
  }

  private static void makeMove(GameMap map, Coordination creatureCoordinate,
      Coordination followCoordinate, MoveAble moveable, ActionAble actionAble) {

    // если цель то, выполняется действие makeAction
    // а после все второстепенные действий makeDescendant and makeHungry
    if (moveable.isGoal(map, followCoordinate)) {
      actionAble.makeAction(map, creatureCoordinate, followCoordinate);
      makeRoutine(map, creatureCoordinate);
      return;
    }
    moving(map, creatureCoordinate, followCoordinate);
    makeRoutine(map, followCoordinate);
  }

  public static void makeRoutine(GameMap map, Coordination creatureCoordinate) {
    makeDescendant(map, creatureCoordinate);
    makeHungry(map, creatureCoordinate);
  }

  public static Coordination getGoalCoordinate(GameMap map, Creature creature, MoveAble moveable) {
    Coordination goal = null;
    for (Map.Entry<Coordination, Entity> element : map.getEntrySet()) {
      if (moveable.isCorrectCreatureAndGoal(creature, element)) {
        goal = element.getKey();
      }
    }
    return goal;
  }
}
