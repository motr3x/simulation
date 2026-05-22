package entity.rule;

import main.GameMap;

public interface RoutineRule {
  void starve(GameMap map);
  void reproduce(GameMap map);
  void upHp();

}
