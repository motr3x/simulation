package entity.factory;

import entity.Entity;
import entity.EntityType;
import entity.Herbivore;
import entity.Predator;
import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;

public class EntityFactory {
  public Entity create(EntityType entityType){
    return switch (entityType){
      case PREDATOR -> new Predator();
      case HERBIVORE -> new Herbivore();
      case GRASS -> new Grass();
      case ROCK -> new Rock();
      case TREE -> new Tree();
    };
  }
}
