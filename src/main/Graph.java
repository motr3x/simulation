package main;

import entity.Coordination;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

  private final Map<Coordination, List<Coordination>> graph = new HashMap<>();

  public void setGraph(Coordination coordination, List<Coordination> coordinations) {
    graph.put(coordination, coordinations);
  }

  public Map<Coordination, List<Coordination>> getGraph() {
    return new HashMap<>(graph);
  }

}
