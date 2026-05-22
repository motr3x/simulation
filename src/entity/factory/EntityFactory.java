package entity.factory;

import entity.Entity;
import main.GameMap;

public interface EntityFactory {
  Entity create(GameMap map);
}
