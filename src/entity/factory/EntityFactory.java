package entity.factory;

import config.CreatureConfig;
import entity.Entity;
import entity.EntityType;
import entity.Herbivore;
import entity.Predator;
import entity.environmentalentities.Grass;
import entity.environmentalentities.Rock;
import entity.environmentalentities.Tree;

public final class EntityFactory {

  public Entity create(EntityType entityType) {
    return switch (entityType) {
      case PREDATOR -> new Predator(CreatureConfig.DEFAULT_PREDATOR_HP, CreatureConfig.DEFAULT_PREDATOR_SPEED);
      case HERBIVORE -> new Herbivore(CreatureConfig.DEFAULT_HERBIVORE_HP, CreatureConfig.DEFAULT_HERBIVORE_SPEED);
      case GRASS -> new Grass();
      case ROCK -> new Rock();
      case TREE -> new Tree();
    };
  }
}
