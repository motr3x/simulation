package main;

import static actions.TurnAction.makeMoveForEverybody;
import static utility.MapUtility.clearScreen;
import static utility.MapUtility.printInfoBar;

import entity.Coordination;


public class Simulation {
    private final GameMap gameMap;
    private final Graph graph;
    public static final int MAX_X_COORDINATE = 20;
    public static final int MIN_X_COORDINATE = 1;
    public static final int MAX_Y_COORDINATE = 10;
    public static final int MIN_Y_COORDINATE = 1;
    private static final char EMPTY_SPACE = '-';

    // Вывод актуального состояния карты
    public static void renderField(GameMap map){
        for(int yCoordinate = MAX_Y_COORDINATE; yCoordinate >= MIN_Y_COORDINATE; yCoordinate--){
            for(int xCoordinate = MIN_X_COORDINATE; xCoordinate <= MAX_X_COORDINATE; xCoordinate++){
//                for(Map.Entry<Coordination, Entity> entity : map.getMap().entrySet()){
//                    if(entity.getKey().getX() == x && entity.getKey().getY() == y) System.out.print(entity.getValue().toString());
//
//                }

                if(map.getMap().get(new Coordination(xCoordinate, yCoordinate))!=null) {
                    System.out.print(map.getMap().get(new Coordination(xCoordinate, yCoordinate)));
                    continue;
                }
                System.out.print(EMPTY_SPACE);
                // TODO REFACTORING
                printInfoBar(map, xCoordinate, yCoordinate);
            }
            System.out.println();
        }
        System.out.println();
      try {
        Thread.sleep(500L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    public Simulation(GameMap gameMap, Graph graph) {
        this.gameMap = gameMap;
        this.graph = graph;
        //track = herbivore.makeTrack(herbivore.removePredatorFromGraphForHerbivore(graph), new Coordination(1, 1), new Coordination(3, 3), gameMap);
    }

     //Просимулировать и отрендерить один ход
    void nextTurn(){
        makeMoveForEverybody(gameMap, graph);
        renderField(gameMap);
        clearScreen();
    }

    // Запустить бесконечный цикл симуляции и рендеринга
    void startSimulation(){
        while (true){
            makeMoveForEverybody(gameMap, graph);
            renderField(gameMap);
            clearScreen();
        }
    }

    // Приостановить бесконечный цикл симуляции и рендеринга
    void pauseSimulation(){

    }
}
