package main;

import static actions.InitActions.initGraph;
import static actions.InitActions.initMap;

public class Main {

  public static void main(String[] args) {

    GameMap map = new GameMap();
    Graph graph = new Graph();
    Simulation simulation = new Simulation(map, graph);
    simulation.startSimulation();
  }
}