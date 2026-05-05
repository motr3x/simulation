package actions;


import static main.Simulation.MAX_X_COORDINATE;
import static main.Simulation.MAX_Y_COORDINATE;
import static main.Simulation.MIN_X_COORDINATE;
import static main.Simulation.MIN_Y_COORDINATE;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import entity.staticObject.Grass;
import entity.staticObject.Rock;
import main.GameMap;
import main.Graph;

import java.util.List;


// действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существ на карте
public class InitActions {
    public static void initMap(GameMap map) {
        // Init Animals

//        Creature herbivore = new Herbivore(10, 1);
//        Creature herbivore2 = new Herbivore(10, 2);
//        Creature predator = new Predator(10, 10, 5);
        // Init staticObject
//        Entity empty = new Empty("e1");
//        Entity empty2 = new Empty("e2");
//        Entity empty3 = new Empty("e3");
//        Entity empty4 = new Empty("e4");
//        Entity empty5 = new Empty("e5");
//        Entity empty6 = new Empty("e6");
//        Entity grass = new Grass();
//        Entity grass2 = new Grass();
        Entity rock = new Rock();
//        Entity tree = new Tree();

//        map.getObjectLocation().put(herbivore,new Coordination(1,1));
//        map.getObjectLocation().put(predator, new Coordination(1,2));
//        map.getObjectLocation().put(empty, new Coordination(1,3));
//        map.getObjectLocation().put(empty2,new Coordination(2,2));
//        map.getObjectLocation().put(empty3, new Coordination(2,3));
//        map.getObjectLocation().put(empty4, new Coordination(3,1));
//        map.getObjectLocation().put(empty5, new Coordination(3,2));
//        map.getObjectLocation().put(empty6, new Coordination(2,1));
//        map.getObjectLocation().put(grass, new Coordination(3,3));

        // Fulling map
//        map.setMap(new Coordination(1,3), predator);
//        map.setMap(new Coordination(3, 1), herbivore);
//        map.setMap(new Coordination(5, 1), herbivore2);
//        map.setMap(new Coordination(5, 3), grass);
//        map.setMap(new Coordination(1, 1), grass2);
        map.setMap(new Coordination(3, 2), rock);
//        map.setMap(new Coordination(1, 3), rock);
//        map.setMap(new Coordination(2, 2), tree);
//        map.getObjectLocation().put(new Coordination(1, 3), empty);
//        map.getObjectLocation().put(new Coordination(2, 2), empty2);
//        map.getObjectLocation().put(new Coordination(2, 3), empty3);
//        map.getObjectLocation().put(new Coordination(3, 1), empty4);
//        map.getObjectLocation().put(new Coordination(3, 2), empty5);
//        map.getObjectLocation().put(new Coordination(2, 1), empty6);
    }
    public static void initGraph(Graph graph) {
        //Filling graph
        fillingGraph(graph);
//        graph.setGraph(new Coordination(1, 1),
//            List.of(new Coordination(1, 2), new Coordination(2, 1)));
//        graph.setGraph(new Coordination(1, 2),
//            List.of(new Coordination(1, 1), new Coordination(1, 3), new Coordination(2, 2)));
//        graph.setGraph(new Coordination(2, 1),
//            List.of(new Coordination(1, 1), new Coordination(2, 2), new Coordination(3, 1)));
//        graph.setGraph(new Coordination(1, 3),
//            List.of(new Coordination(1, 4), new Coordination(1, 2), new Coordination(2, 3)));
//        graph.setGraph(new Coordination(2, 2),
//            List.of(new Coordination(1, 2), new Coordination(2, 1), new Coordination(2, 3),
//                new Coordination(3, 2)));
//        graph.setGraph(new Coordination(3, 1),
//            List.of(new Coordination(2, 1), new Coordination(3, 2), new Coordination(4, 1)));
//        graph.setGraph(new Coordination(2, 3),
//            List.of(new Coordination(2, 4), new Coordination(1, 3), new Coordination(2, 2),
//                new Coordination(3, 3)));
//        graph.setGraph(new Coordination(3, 2),
//            List.of(new Coordination(2, 2), new Coordination(3, 1), new Coordination(3, 3),
//                new Coordination(4, 2)));
//        graph.setGraph(new Coordination(3, 3),
//            List.of(new Coordination(3, 4), new Coordination(2, 3), new Coordination(3, 2),
//                new Coordination(4, 3)));
//        graph.setGraph(new Coordination(4, 3),
//            List.of(new Coordination(4, 4), new Coordination(3, 3), new Coordination(4, 2),
//                new Coordination(5, 3)));
//        graph.setGraph(new Coordination(4, 2),
//            List.of(new Coordination(4, 3), new Coordination(3, 2), new Coordination(4, 1),
//                new Coordination(5, 2)));
//        graph.setGraph(new Coordination(4, 1),
//            List.of(new Coordination(4, 2), new Coordination(3, 1), new Coordination(5, 1)));
//        graph.setGraph(new Coordination(5, 3),
//            List.of(new Coordination(6, 3), new Coordination(5, 4), new Coordination(4, 3),
//                new Coordination(5, 2)));
//        graph.setGraph(new Coordination(5, 2),
//            List.of(new Coordination(6, 2), new Coordination(5, 3), new Coordination(4, 2),
//                new Coordination(5, 1)));
//        graph.setGraph(new Coordination(5, 1),
//            List.of(new Coordination(6, 1), new Coordination(5, 2), new Coordination(4, 1)));
//        graph.setGraph(new Coordination(1, 4),
//            List.of(new Coordination(1, 5), new Coordination(2, 4), new Coordination(1, 3)));
//        graph.setGraph(new Coordination(2, 4),
//            List.of(new Coordination(1, 4), new Coordination(2, 5), new Coordination(3, 4),
//                new Coordination(2, 3)));
//        graph.setGraph(new Coordination(3, 4),
//            List.of(new Coordination(3, 5), new Coordination(2, 4), new Coordination(4, 4),
//                new Coordination(3, 3)));
//        graph.setGraph(new Coordination(4, 4),
//            List.of(new Coordination(4, 5), new Coordination(3, 4), new Coordination(4, 3),
//                new Coordination(5, 4)));
//        graph.setGraph(new Coordination(5, 4),
//            List.of(new Coordination(4, 4), new Coordination(5, 5), new Coordination(6, 4),
//                new Coordination(5, 3)));
//        graph.setGraph(new Coordination(1, 5),
//            List.of(new Coordination(1, 4), new Coordination(2, 4)));
//        graph.setGraph(new Coordination(2, 5),
//            List.of(new Coordination(1, 5), new Coordination(2, 4), new Coordination(3, 5)));
//        graph.setGraph(new Coordination(3, 5),
//            List.of(new Coordination(2, 5), new Coordination(3, 4), new Coordination(4, 5)));
//        graph.setGraph(new Coordination(4, 5),
//            List.of(new Coordination(3, 5), new Coordination(4, 4), new Coordination(5, 5)));
//        graph.setGraph(new Coordination(5, 5),
//            List.of(new Coordination(4, 5), new Coordination(5, 4), new Coordination(6, 5)));
//        graph.setGraph(new Coordination(6, 5),
//            List.of(new Coordination(5, 5), new Coordination(6, 4), new Coordination(7, 5)));
//        graph.setGraph(new Coordination(6, 4),
//            List.of(new Coordination(6, 5), new Coordination(5, 4), new Coordination(7, 4),
//                new Coordination(6, 3)));
//        graph.setGraph(new Coordination(6, 3),
//            List.of(new Coordination(6, 4), new Coordination(5, 3), new Coordination(6, 2),
//                new Coordination(7, 3)));
//        graph.setGraph(new Coordination(6, 2),
//            List.of(new Coordination(6, 3), new Coordination(5, 2), new Coordination(6, 1),
//                new Coordination(7, 2)));
//        graph.setGraph(new Coordination(6, 1),
//            List.of(new Coordination(6, 2), new Coordination(5, 1), new Coordination(7, 1)));
//        graph.setGraph(new Coordination(7, 5),
//            List.of(new Coordination(6, 5), new Coordination(7, 4)));
//        graph.setGraph(new Coordination(7, 4),
//            List.of(new Coordination(7, 5), new Coordination(6, 4), new Coordination(7, 3)));
//        graph.setGraph(new Coordination(7, 3),
//            List.of(new Coordination(7, 4), new Coordination(6, 3), new Coordination(7, 2)));
//        graph.setGraph(new Coordination(7, 2),
//            List.of(new Coordination(7, 3), new Coordination(6, 2), new Coordination(7, 1)));
//        graph.setGraph(new Coordination(7, 1),
//            List.of(new Coordination(7, 2), new Coordination(6, 1)));

    }
    public static void fillingGraph(Graph graph) {
        for (int yCoordinate = MAX_Y_COORDINATE; yCoordinate >= MIN_Y_COORDINATE; yCoordinate--) {
            for (int xCoordinate = MIN_X_COORDINATE; xCoordinate <= MAX_X_COORDINATE; xCoordinate++) {
                // right
                if (isLowerLeftCorner(xCoordinate, yCoordinate)) {
                    graph.setGraph(new Coordination(xCoordinate, yCoordinate),
                        List.of(new Coordination(xCoordinate, yCoordinate + 1),
                            new Coordination(xCoordinate + 1, yCoordinate)));
                    continue;
                }
                // right
                if (isUpperLeftCorner(xCoordinate, yCoordinate)) {
                    graph.setGraph(new Coordination(xCoordinate, yCoordinate),
                        List.of(new Coordination(xCoordinate, yCoordinate - 1),
                            new Coordination(xCoordinate + 1, yCoordinate)));
                    continue;
                }
                // right
                if (isUpperRightCorner(xCoordinate, yCoordinate)) {
                    graph.setGraph(new Coordination(xCoordinate, yCoordinate),
                        List.of(new Coordination(xCoordinate-1, yCoordinate),
                            new Coordination(xCoordinate, yCoordinate-1)));
                    continue;
                }
                // right
                if (isLowerRightCorner(xCoordinate, yCoordinate)) {
                    graph.setGraph(new Coordination(xCoordinate, yCoordinate),
                        List.of(new Coordination(xCoordinate-1, yCoordinate),
                            new Coordination(xCoordinate, yCoordinate+1)));
                    continue;
                }
                // right
                if(isLeftSide(xCoordinate, yCoordinate)) {
                    graph.setGraph(new Coordination(xCoordinate, yCoordinate),
                        List.of(new Coordination(xCoordinate, yCoordinate-1),
                        new Coordination(xCoordinate+1, yCoordinate),
                            new Coordination(xCoordinate, yCoordinate+1)));
                    continue;
                }
                // right
                if(isAbove(xCoordinate, yCoordinate)){
                    graph.setGraph(new Coordination(xCoordinate, yCoordinate),
                        List.of(new Coordination(xCoordinate-1, yCoordinate),
                            new Coordination(xCoordinate, yCoordinate-1), new Coordination(xCoordinate+1, yCoordinate)));
                    continue;
                }
                // right
                if(isBottom(xCoordinate, yCoordinate)){
                    graph.setGraph(new Coordination(xCoordinate, yCoordinate),
                        List.of(new Coordination(xCoordinate-1, yCoordinate),
                            new Coordination(xCoordinate, yCoordinate+1),
                            new Coordination(xCoordinate+1, yCoordinate)));
                    continue;
                }
                // right
                if(isRightSide(xCoordinate, yCoordinate)){
                    graph.setGraph(new Coordination(xCoordinate, yCoordinate),
                        List.of(new Coordination(xCoordinate, yCoordinate-1),
                            new Coordination(xCoordinate,yCoordinate+1),
                            new Coordination(xCoordinate-1, yCoordinate)));
                    continue;
                }
                // right
                graph.setGraph(new Coordination(xCoordinate, yCoordinate),
                    List.of(new Coordination(xCoordinate, yCoordinate+1),
                        new Coordination(xCoordinate+1, yCoordinate),
                        new Coordination(xCoordinate, yCoordinate-1),
                        new Coordination(xCoordinate-1, yCoordinate)));
            }
        }
    }
    // right
    private static boolean isRightSide(int xCoordinate, int yCoordinate){
        return (((yCoordinate > MIN_Y_COORDINATE) && (yCoordinate < MAX_Y_COORDINATE)) && (xCoordinate == MAX_X_COORDINATE));
    }
    // right
    private static boolean isBottom(int xCoordinate, int yCoordinate){
        return (((xCoordinate > MIN_X_COORDINATE) && (xCoordinate < MAX_X_COORDINATE)) && (yCoordinate == MIN_Y_COORDINATE));
    }
    // right
    private static boolean isAbove(int xCoordinate, int yCoordinate) {
        return (((xCoordinate > MIN_X_COORDINATE) && (xCoordinate < MAX_X_COORDINATE)) && (yCoordinate == MAX_Y_COORDINATE));
    }
    // right
    private static boolean isLeftSide(int xCoordinate, int yCoordinate) {
        return (((yCoordinate > MIN_Y_COORDINATE) && (yCoordinate < MAX_Y_COORDINATE)) && (xCoordinate == MIN_X_COORDINATE));
    }
    // right
    private static boolean isLowerRightCorner(int xCoordinate, int yCoordinate) {
        return ((yCoordinate == MIN_Y_COORDINATE) && (xCoordinate == MAX_X_COORDINATE));
    }
    // right
    private static boolean isUpperRightCorner(int xCoordinate, int yCoordinate) {
        return ((yCoordinate == MAX_Y_COORDINATE) && (xCoordinate == MAX_X_COORDINATE));
    }
    // right
    private static boolean isUpperLeftCorner(int xCoordinate, int yCoordinate) {
        return ((yCoordinate == MAX_Y_COORDINATE) && (xCoordinate == MIN_X_COORDINATE));
    }
    // right
    private static boolean isLowerLeftCorner(int xCoordinate, int yCoordinate){
        return ((yCoordinate == MIN_Y_COORDINATE) && (xCoordinate == MIN_X_COORDINATE));
    }
//        Track without Predator
//        graph.getGraph().remove(new Coordination(1,2));
//        for (Map.Entry<Coordination, List<Coordination>> pair : graph.getGraph().entrySet()) {
//            if (pair.getValue().equals(new Coordination(1, 2))) graph.getGraph().remove(pair.getKey(), new Coordination(1, 2));
//
//        }

//        // Moving
//        Deque<Coordination> track = herbivore.makeTrack(graph, new Coordination(3,3), new Coordination(1,1), map);
//        Coordination creature = track.poll();
//        creature = moving(map, track, creature);
//        creature = moving(map, track, creature);
//        creature = moving(map, track, creature);
//        creature = moving(map, track, creature);
}


