package entity.ability;

import main.GameMap;
import main.Graph;

public interface Moveable {
  void makeMove(GameMap map, Graph graph);
}
