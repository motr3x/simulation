package entity.factory;

import static entity.Creature.DEFAULT_CREATURE_HP;
import static entity.Creature.DEFAULT_CREATURE_SPEED;
import static entity.Predator.DEFAULT_PREDATOR_POWER;
import static utility.OtherUtility.getRandomEmptyCoordinate;

import entity.Coordination;
import entity.Creature;
import entity.Entity;
import entity.Predator;
import exception.EntityNotCreateException;
import main.GameMap;

public class PredatorFactory implements EntityFactory {
  @Override
  public Entity create(GameMap map) {
    Coordination coordination = getRandomEmptyCoordinate(map);
    map.setMap(coordination,
        new Predator(
            DEFAULT_CREATURE_HP,
            DEFAULT_CREATURE_SPEED,
            DEFAULT_PREDATOR_POWER
            ));
    return map.getEntityByCoordinate(coordination, Creature.class).orElseThrow(() -> new EntityNotCreateException("Entity doesn't create"));
  }
}
