package main;

import entity.Coordination;
import entity.Entity;

import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<Coordination, List<Coordination>> graph = new HashMap<>();

    public void setGraph(Coordination coordination, List<Coordination> coordinations){
        graph.put(coordination, coordinations);
    }
    public void setAllGraph(Map<Coordination, List<Coordination>> copyOfGraph){
        graph.putAll(copyOfGraph);
    }

    public Map<Coordination, List<Coordination>> getGraph() {
        return graph;
    }
}
