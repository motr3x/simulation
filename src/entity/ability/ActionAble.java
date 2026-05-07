package entity.ability;

import entity.Coordination;
import main.GameMap;

public interface ActionAble {

  void makeAction(GameMap map, Coordination creatureCoordinate,
      Coordination followCoordinate);

  void upHp(GameMap map, Coordination creatureCoordinate);

  void makeAttack(Coordination creatureCoordinate, Coordination goalCreature,
      GameMap map);

}
