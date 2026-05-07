package entity.herbivore;


import static actions.TurnAction.HERBIVORE_PLACE;
import static utility.MapUtility.fieldIsEmpty;
import static utility.MapUtility.getCreaturesCoordinates;
import static utility.MoveUtility.followTrack;
import static utility.OtherUtility.getRandomCoordinate;

import entity.Coordination;
import entity.Creature;
import entity.ability.ActionAble;
import entity.ability.MoveAble;
import entity.rule.HerbivoreActionRule;
import entity.rule.HerbivoreMoveRule;
import main.GameMap;
import main.Graph;

public class Herbivore extends Creature {

  protected static final MoveAble moveRule = new HerbivoreMoveRule();
  protected static final ActionAble actionRule = new HerbivoreActionRule();

  public Herbivore(int speed, int hp, MoveAble moveable, ActionAble actionAble) {
    super(speed, hp, moveable, actionAble);
  }

  public static void create(GameMap map) {
    Coordination coordination = getRandomCoordinate();
    if (fieldIsEmpty(coordination, map)) {
      setToMap(coordination, map);
    }
  }
  @Override
  public String toString() {
    return "\uD83D\uDC30";
  }

  private static void setToMap(Coordination coordination, GameMap map) {
    map.setMap(coordination,
        new Herbivore(DEFAULT_CREATURE_SPEED,
            DEFAULT_CREATURE_HP,
            Herbivore.moveRule,
            Herbivore.actionRule));
  }
  public static void makeMove(GameMap gameMap, Graph graph){
    followTrack(gameMap, graph, getCreaturesCoordinates(gameMap).get(HERBIVORE_PLACE), moveRule, actionRule);
  }
}
