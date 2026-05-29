package utility;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import entity.Herbivore;
import entity.staticObject.Grass;
import exception.EntityNotExistException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import main.GameMap;

public final class PathFinder {

  private PathFinder() {

  }

  public static Optional<Deque<Coordination>> useBfsAlgorithm(
      Map<Coordination, List<Coordination>> graph,
      Coordination start, GameMap gameMap) {

    Set<Coordination> visited = new LinkedHashSet<>();
    Deque<List<Coordination>> queue = new ArrayDeque<>();

    queue.add(new ArrayList<>(List.of(start)));

    while (!queue.isEmpty()) {
      Deque<Coordination> path = new ArrayDeque<>(queue.getFirst());
      queue.removeFirst();
      Coordination node = path.getLast();
      if (node != start) {
        Creature creature = gameMap.get(start, Creature.class)
            .orElseThrow(() -> new EntityNotExistException("Entity doesn't exist"));
        ;
        if (creature.checkBarrier(gameMap, node)) {
          continue;
        }
      }

      if (visited.contains(node)) {
        continue;
      }

      visited.add(node);

      if (!path.getLast().equals(start)) {
        Optional<Entity> entity = gameMap.get(node);
        if (entity.isPresent()) {
          if (entity.get() instanceof Grass || entity.get() instanceof Herbivore) {
            path.removeFirst();
            return Optional.of(path);
          }
        }
      }

      List<Coordination> neighbors = graph.get(node);
      if (neighbors.isEmpty()) {
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
