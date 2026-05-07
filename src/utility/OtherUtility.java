package utility;

import static main.Simulation.MAX_X_COORDINATE;
import static main.Simulation.MAX_Y_COORDINATE;
import static main.Simulation.MIN_X_COORDINATE;
import static main.Simulation.MIN_Y_COORDINATE;

import entity.Coordination;
import java.util.Random;

public class OtherUtility {
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static Coordination getRandomCoordinate() {
    Random random = new Random();
    int xCoordinate = random.nextInt(MIN_X_COORDINATE, MAX_X_COORDINATE);
    int yCoordinate = random.nextInt(MIN_Y_COORDINATE, MAX_Y_COORDINATE);
    return new Coordination(xCoordinate, yCoordinate);
  }
}
