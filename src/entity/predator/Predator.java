package entity.predator;

import static actions.TurnAction.PREDATORS_PLACE;
import static utility.MapUtility.fieldIsEmpty;
import static utility.MapUtility.getCreaturesCoordinates;
import static utility.MoveUtility.followTrack;
import static utility.OtherUtility.getRandomCoordinate;

import entity.Coordination;
import entity.Creature;
import entity.ability.ActionAble;
import entity.ability.MoveAble;
import entity.rule.PredatorActionRule;
import entity.rule.PredatorMoveRule;
import main.GameMap;
import main.Graph;

public class Predator extends Creature {

  private static final int DEFAULT_PREDATOR_POWER = 10;
  private final int power;

  protected static final MoveAble moveRule = new PredatorMoveRule();
  protected static final ActionAble actionRule = new PredatorActionRule();

  public Predator(int speed, int power, int hp, MoveAble moveable, ActionAble actionAble) {
    super(speed, hp, moveable, actionAble);
    this.power = power;
  }

  public int getPower() {
    return power;
  }

  public static void create(GameMap map) {
    Coordination coordination = getRandomCoordinate();
    if (fieldIsEmpty(coordination, map)) {
      setToMap(coordination, map);
    }
  }

  private static void setToMap(Coordination coordination, GameMap map) {
    map.setMap(coordination,
        new Predator(
            DEFAULT_CREATURE_SPEED,
            DEFAULT_PREDATOR_POWER,
            DEFAULT_CREATURE_HP,
            Predator.moveRule,
            Predator.actionRule));
  }

  public static void makeMove(GameMap gameMap, Graph graph){
    followTrack(gameMap, graph, getCreaturesCoordinates(gameMap).get(PREDATORS_PLACE), moveRule, actionRule);
  }

  @Override
  public String toString() {
    return "\uD83D\uDC3A";
  }
}
