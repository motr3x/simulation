package utility;


import entity.Coordination;
import entity.Entity;
import entity.herbivore.Herbivore;
import entity.predator.Predator;
import entity.staticObject.Grass;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import main.GameMap;

public class MapUtility {

  public static final int VALUE_OF_GRASS = 5;
  public static final int VALUE_OF_HERBIVORE = 5;
  public static final String HERBIVORE_SPRITE = "\uD83D\uDC30";
  public static final String PREDATOR_SPRITE = "\uD83D\uDC3A";
  public static final String GRASS_SPRITE = "☘\uFE0F";
  public static final String ROCK_SPRITE = "\uD83E\uDEA8";
  public static final String TREE_SPRITE = "\uD83C\uDF33";

  public static Coordination getEntityCoordinate(GameMap map, Entity entity){
    for(Map.Entry<Coordination, Entity> element : map.getEntrySet()){
      if(element.getValue() != null && element.getValue().equals(entity)){
        return element.getKey();
      }
    }
    return null;
  }

  public static boolean fieldIsEmpty(Coordination coordination, GameMap map) {
    return (map.getEntityByCoordinate(coordination) == null);
  }
  // Get separate entity coordinates
  public static List<Queue<Coordination>> getCreaturesCoordinates(GameMap map) {

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
