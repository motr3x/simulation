package entity.factory;

import static entity.Creature.DEFAULT_CREATURE_HP;
import static entity.Creature.DEFAULT_CREATURE_SPEED;
import static utility.OtherUtility.getRandomEmptyCoordinate;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import entity.Herbivore;
import exception.EntityNotCreateException;
import main.GameMap;

public class HerbivoreFactory implements EntityFactory {
  @Override
  public Entity create(GameMap map) {
    Coordination coordination = getRandomEmptyCoordinate(map);
    map.setMap(coordination,
        new Herbivore(
            DEFAULT_CREATURE_HP,
            DEFAULT_CREATURE_SPEED));
    return map.getEntityByCoordinate(coordination, Creature.class).orElseThrow(() -> new EntityNotCreateException("Entity doesn't create"));
  }
}
