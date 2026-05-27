package utility;



import entity.Coordination;
import entity.Entity;
import entity.Herbivore;
import entity.Predator;
import entity.SpriteType;
import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import java.util.ArrayDeque;
import java.util.ArrayList;
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

public static Optional<String> getEntitySprite(Entity entity){
  return switch (entity) {
    case Predator predator -> Optional.of(SpriteType.PREDATOR.getCode());
    case Herbivore herbivore -> Optional.of(SpriteType.HERBIVORE.getCode());
    case Grass grass -> Optional.of(SpriteType.GRASS.getCode());
    case Rock rock -> Optional.of(SpriteType.ROCK.getCode());
    case Tree tree -> Optional.of(SpriteType.TREE.getCode());
    case null, default -> Optional.empty();
  };
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
