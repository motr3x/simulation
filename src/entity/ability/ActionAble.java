package entity.ability;

import entity.Coordination;
import main.GameMap;

public interface ActionAble {
  void makeAttack(GameMap map, Coordination goalCreature);
}
