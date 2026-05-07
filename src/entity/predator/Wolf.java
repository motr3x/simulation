package entity.predator;

import java.util.function.Predicate;

public class Wolf extends Predator {
  public Wolf(int speed, int power, int hp) {
    super(speed, power, hp, Predator.moveRule, Predator.actionRule);
  }

  @Override
  public String toString() {
    return "\uD83D\uDC3A";
  }
}
