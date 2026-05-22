package entity;

import static utility.MapUtility.GRASS_SPRITE;
import static utility.MapUtility.HERBIVORE_SPRITE;
import static utility.MapUtility.PREDATOR_SPRITE;

public enum EntityType {
  PREDATOR{
    @Override
    public String toString() {
      return PREDATOR_SPRITE;
    }
  }, HERBIVORE{
    @Override
    public String toString() {
      return HERBIVORE_SPRITE;
    }
  }, GRASS{
    @Override
    public String toString() {
      return GRASS_SPRITE;
    }
  }
}
