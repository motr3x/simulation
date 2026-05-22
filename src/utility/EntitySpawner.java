package utility;

import entity.EntityType;
import entity.factory.EntityFactory;
import java.util.Map;
import main.GameMap;

public final class EntitySpawner {

  private final Map<EntityType, EntityFactory> factories;

  public EntitySpawner(Map<EntityType, EntityFactory> factory) {
    this.factories = factory;
  }

  public void spawn(EntityType type, GameMap map) {
    factories.get(type).create(map);
  }
}
