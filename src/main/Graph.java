package main;

import entity.Coordinates;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Graph {

  private final Map<Coordinates, List<Coordinates>> graph = new HashMap<>();

  public Map<Coordinates, List<Coordinates>> get() {
    return new HashMap<>(graph);
  }

  public void set(Coordinates parentCoordinates, List<Coordinates> childCoordinates) {
    graph.put(parentCoordinates, childCoordinates);
  }
}
