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

  private MapUtility() {

  }

  public static <T> boolean checkClassType(GameMap map, Coordination coordination, Class<T> type) {
    boolean emptyCell = false;
    Optional<Entity> entity = map.get(coordination, Entity.class);
    return entity.map(type::isInstance).orElse(emptyCell);
  }

  public static boolean fieldIsEmpty(Coordination coordination, GameMap map) {
    return map.get(coordination, Entity.class).isEmpty();
  }


}
