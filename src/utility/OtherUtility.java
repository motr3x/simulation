package utility;

import static config.SimulationConfig.MAX_X_COORDINATE;
import static config.SimulationConfig.MAX_Y_COORDINATE;
import static config.SimulationConfig.MIN_X_COORDINATE;
import static config.SimulationConfig.MIN_Y_COORDINATE;
import static utility.MapUtility.fieldIsEmpty;

import entity.Coordination;
import java.util.Random;
import main.GameMap;

public final class OtherUtility {

  private OtherUtility() {

  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static Coordination getRandomEmptyCoordinate(GameMap map) {
    Random random = new Random();
    int xCoordinate = random.nextInt(MIN_X_COORDINATE, MAX_X_COORDINATE);
    int yCoordinate = random.nextInt(MIN_Y_COORDINATE, MAX_Y_COORDINATE);
    Coordination coordination = new Coordination(xCoordinate, yCoordinate);
    while (!fieldIsEmpty(coordination, map)) {
      xCoordinate = random.nextInt(MIN_X_COORDINATE, MAX_X_COORDINATE);
      yCoordinate = random.nextInt(MIN_Y_COORDINATE, MAX_Y_COORDINATE);
      coordination = new Coordination(xCoordinate, yCoordinate);
    }
    return coordination;
  }
}
