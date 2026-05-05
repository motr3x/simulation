package entity;

import java.util.Map;
import main.GameMap;

public abstract class Entity {
  protected String sprite;
  protected static final int VALUE_OF_GRASS = 5;
  protected static final int VALUE_OF_HERBIVORE = 5;
  protected static final String HERBIVORE_SPRITE = "☺";
  protected static final String PREDATOR_SPRITE = "Θ";

  protected static Coordination getEntityCoordinate(GameMap map, Entity entity){
    for(Map.Entry<Coordination, Entity> element : map.getMap().entrySet()){
        if(element.getValue() != null && element.getValue().equals(entity)){
          return element.getKey();
      }
    }
    return null;
  }

  public String getSprite() {
    return sprite;
  }
}
