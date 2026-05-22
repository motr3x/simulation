package entity.factory;

import static utility.OtherUtility.getRandomEmptyCoordinate;

import entity.Coordination;
import entity.Entity;
import entity.staticObject.Grass;
import exception.EntityNotCreateException;
import exception.EntityNotExistException;
import main.GameMap;

public class GrassFactory implements EntityFactory {
  @Override
  public Entity create(GameMap map) {
    Coordination coordination = getRandomEmptyCoordinate(map);
    map.setMap(coordination,
        new Grass());
    return map.getEntityByCoordinate(coordination, Grass.class).orElseThrow(() -> new EntityNotCreateException("Entity doesn't create"));
  }
}
