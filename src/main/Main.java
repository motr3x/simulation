package main;

public class Main {

  public static void main(String[] args) {
    GameMap gameMap = new GameMap();
    Graph graph = new Graph();
    Simulation simulation = new Simulation(gameMap, graph);
    simulation.startSimulation();
  }
}