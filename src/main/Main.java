package main;

import static actions.InitActions.initGraph;
import static actions.InitActions.initMap;
import static main.Simulation.renderField;

public class Main {
    public static void main(String[] args) {
        GameMap map = new GameMap();
        Graph graph = new Graph();
        initMap(map);
        initGraph(graph);
//
//        TurnAction turnAction = new TurnAction();
//        Creature herbivore;
//        Coordination herbivoreCoordinate = null;
//        Creature predator = new Predator(10, 10, 1);
        Simulation simulation = new Simulation(map, graph);
        simulation.startSimulation();
//        Coordination findGrassCoordinate = null;
//        for(Map.Entry<Coordination, Entity> element : map.getMap().entrySet()){
//            if (element.getValue() instanceof Herbivore) {
//                herbivoreCoordinate = element.getKey();
//            }
//        }
//        for(Map.Entry<Coordination, Entity> element : map.getMap().entrySet()){
//            if (element.getValue() instanceof Grass) {
//                findGrassCoordinate = element.getKey();
//            }
//        }
//        herbivore = (Creature) map.getMap().get(herbivoreCoordinate);
//        renderField(map);
//        Deque<Coordination> track = herbivore.makeTrack(graph.getGraph(), getEntityCoordinate(map, herbivore), findGrassCoordinate, map);
//
//
//
//        turnAction.makeMoveForEverybody(map, graph);
//        renderField(map);
//        turnAction.makeMoveForEverybody(map, graph);
//        renderField(map);
//        turnAction.makeMoveForEverybody(map, graph);
//        renderField(map);
//        turnAction.makeMoveForEverybody(map, graph);
//        renderField(map);
//        turnAction.makeMoveForEverybody(map, graph);
//        renderField(map);
//        turnAction.makeMoveForEverybody(map, graph);
//        renderField(map);
//        turnAction.makeMoveForEverybody(map, graph);
//        renderField(map);
//
//        int a = 12;
    }
}