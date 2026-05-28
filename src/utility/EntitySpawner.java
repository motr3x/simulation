package utility;

import static utility.Helper.getRandomEmptyPosition;

import entity.Coordination;
import entity.Entity;
import entity.EntityType;
import entity.factory.EntityFactory;
import main.GameMap;

public final class EntitySpawner {

  private final EntityFactory FACTORY = new EntityFactory();

  public void spawnToMap(GameMap map, EntityType entityType) {
    Entity entity = FACTORY.create(entityType);
    Coordination coordination = getRandomEmptyPosition(map);
    map.set(coordination, entity);
  }

}
