package entity.rule;

import main.GameMap;

public interface RoutineRule {
  void starve(GameMap map);
  //TODO THROW EXCEPTION IF DON'T MAKE
  void reproduce(GameMap map);
  void upHp();

}
