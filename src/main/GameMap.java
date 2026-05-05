package main;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import entity.staticObject.Grass;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GameMap {
    private Map<Coordination, Entity> map = new LinkedHashMap<>();
//    private Queue<Coordination> herbivoresCoordinates = new ArrayDeque<>();
//    private Queue<Coordination> predatorsCoordinates = new ArrayDeque<>();

    public void setMap(Coordination coordination, Entity entity){
        map.put(coordination, entity);
    }

    public Map<Coordination, Entity> getMap() {
        return map;
    }

//    public Queue<Coordination> getHerbivoresCoordinates() {
//        return herbivoresCoordinates;
//    }
//
//    public void setHerbivoresCoordinates(Coordination coordinates) {
//        herbivoresCoordinates.add(coordinates);
//    }
//
//    public Queue<Coordination> getPredatorsCoordinates() {
//        return predatorsCoordinates;
//    }
//
//    public void setPredatorsCoordinates(Coordination coordinates) {
//        predatorsCoordinates.add(coordinates);
//    }
}
