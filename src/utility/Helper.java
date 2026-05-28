package utility;

import static config.SimulationConfig.MAX_X_COORDINATE;
import static config.SimulationConfig.MAX_Y_COORDINATE;
import static config.SimulationConfig.MIN_X_COORDINATE;
import static config.SimulationConfig.MIN_Y_COORDINATE;

import entity.Coordination;
import entity.Entity;
import java.util.Optional;
import java.util.Random;
import main.GameMap;

public final class Helper {

  private Helper() {

  }

  public static <T> boolean checkClassType(GameMap map, Coordination coordination, Class<T> type) {
    boolean emptyCell = false;
    Optional<Entity> entity = map.get(coordination, Entity.class);
    return entity.map(type::isInstance).orElse(emptyCell);
  }

  public static Coordination getRandomEmptyPosition(GameMap map) {
    Random random = new Random();
    int xCoordinate = random.nextInt(MIN_X_COORDINATE, MAX_X_COORDINATE);
    int yCoordinate = random.nextInt(MIN_Y_COORDINATE, MAX_Y_COORDINATE);
    Coordination coordination = new Coordination(xCoordinate, yCoordinate);
    while (!map.fieldIsEmpty(coordination)) {
      xCoordinate = random.nextInt(MIN_X_COORDINATE, MAX_X_COORDINATE);
      yCoordinate = random.nextInt(MIN_Y_COORDINATE, MAX_Y_COORDINATE);
      coordination = new Coordination(xCoordinate, yCoordinate);
    }
    return coordination;
  }
}
