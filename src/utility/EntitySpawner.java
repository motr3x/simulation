package utility;


import entity.Coordinates;
import entity.Entity;
import entity.EntityType;
import entity.factory.EntityFactory;
import main.GameMap;

public final class EntitySpawner {

  private final EntityFactory factory = new EntityFactory();

  public void spawnToMap(GameMap gameMap, EntityType entityType) {
    Entity entity = factory.create(entityType);
    Coordinates coordinates = gameMap.getRandomEmptyPosition(gameMap);
    gameMap.add(coordinates, entity);
  }

}
