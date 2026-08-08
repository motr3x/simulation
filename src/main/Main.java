package main;

public class Main {

  public static void main(String[] args) {
    GameMap gameMap = new GameMap();
    Graph graph = new Graph();
    Boolean stopFlag = Boolean.FALSE;
    Simulation simulation = new Simulation(gameMap, graph, stopFlag);
    Menu menu = new Menu(simulation, stopFlag);
    menu.printMenu();
  }
}