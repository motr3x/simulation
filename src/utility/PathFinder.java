package utility;

import entity.Coordination;
import entity.ability.MoveAble;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import main.GameMap;

public class PathFinder {

  public static Optional<Deque<Coordination>> useBfsAlgorithm(
      Map<Coordination, List<Coordination>> graph,
      Coordination start, Coordination goal, GameMap map, MoveAble moveable) {

    Set<Coordination> visited = new LinkedHashSet<>();
    Deque<List<Coordination>> queue = new ArrayDeque<>();

    queue.add(new ArrayList<>(List.of(start)));

    while (!queue.isEmpty()) {
      Deque<Coordination> path = new ArrayDeque<>(queue.getFirst());
      queue.removeFirst();// берем путь
      Coordination node = path.getLast();// последний элемент пути
      if (node != start) {
        if (moveable.checkBarrier(map, node)) {
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
}
