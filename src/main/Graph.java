package main;

import entity.Coordination;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Graph {

  private final Map<Coordination, List<Coordination>> graph = new HashMap<>();

  public Map<Coordination, List<Coordination>> get() {
    return new HashMap<>(graph);
  }

  public void set(Coordination coordination, List<Coordination> coordinates) {
    graph.put(coordination, coordinates);
  }
}
