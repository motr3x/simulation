package entity.staticObject;

import static utility.OtherUtility.getRandomEmptyCoordinate;

import entity.Coordination;
import entity.Entity;
import main.GameMap;

public class Grass extends Entity {
  @Override
  public String toString() {
    return "\uD83C\uDF3F";
  }
}
