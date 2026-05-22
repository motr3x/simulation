package utility;


import static entity.EntityType.GRASS;
import static entity.EntityType.PREDATOR;
import static main.Simulation.EMPTY_SPACE;

import entity.Coordination;
import entity.Entity;
import entity.EntityType;
import entity.Herbivore;
import entity.Predator;
import entity.staticObject.Grass;
import exception.EntityNotExistException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import main.GameMap;

public final class MapUtility {

  public static final int VALUE_OF_GRASS = 5;
  public static final int VALUE_OF_HERBIVORE = 5;
  public static final String HERBIVORE_SPRITE = "\uD83D\uDC30";
  public static final String PREDATOR_SPRITE = "\uD83D\uDC3A";
  public static final String GRASS_SPRITE = "☘\uFE0F";
  public static final String ROCK_SPRITE = "\uD83E\uDEA8";
  public static final String TREE_SPRITE = "\uD83C\uDF33";

  private MapUtility(){

  }

//  public static <T> String getEntitySprite(EntityType type){
//    String sprite = switch (type){
//      case PREDATOR -> PREDATOR_SPRITE;
//      case HERBIVORE -> HERBIVORE_SPRITE;
//      case GRASS -> GRASS_SPRITE;
//    };
//    return sprite;
//  }
//  public static <T> String getEntitySprite(Class<T> type){
//    if(type.isInstance(Predator.class)){
//      return PREDATOR_SPRITE;
//    }
//    else if (type.isInstance(Herbivore.class)){
//      return HERBIVORE_SPRITE;
//    }
//    else if(type.isInstance(Grass.class)){
//      return GRASS_SPRITE;
//    }
//    return null;
//  }
public static String getEntitySprite(Entity entity){
  if(entity instanceof Predator){
    return PREDATOR_SPRITE;
  }
  else if (entity instanceof Herbivore){
    return HERBIVORE_SPRITE;
  }
  else if(entity instanceof Grass){
    return GRASS_SPRITE;
  }
  return null;
}

  public static <T> boolean checkClassType(GameMap map, Coordination coordination, Class<T> type) {
    boolean emptyCell = false;
    Optional<Entity> entity = map.getEntityByCoordinate(coordination, Entity.class);
    return entity.map(type::isInstance).orElse(emptyCell);
  }

  public static boolean fieldIsEmpty(Coordination coordination, GameMap map) {
    return map.getEntityByCoordinate(coordination, Entity.class).isEmpty();
  }

  public static List<Queue<Coordination>> getInfoByCreaturesCoordinates(GameMap map) {

    Queue<Coordination> herbivoreCoordinates = new ArrayDeque<>();
    Queue<Coordination> predatorCoordinates = new ArrayDeque<>();
    Queue<Coordination> grassCoordinates = new ArrayDeque<>();


    for (Map.Entry<Coordination, Entity> element : map.getEntrySet()) {
      if (element.getValue() instanceof Herbivore) {
        herbivoreCoordinates.add(element.getKey());
        continue;
      }
      if (element.getValue() instanceof Predator) {
        predatorCoordinates.add(element.getKey());
        continue;
      }
      if (element.getValue() instanceof Grass) {
        grassCoordinates.add(element.getKey());
        continue;
      }
    }

    List<Queue<Coordination>> separateEntityCoordinates = new ArrayList<>(
        List.of(herbivoreCoordinates, predatorCoordinates, grassCoordinates));

    return separateEntityCoordinates;
  }

}
