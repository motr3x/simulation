package entity.herbivore;

public class Rabbit extends Herbivore {

  public Rabbit(int speed, int hp) {
    super(speed, hp, Herbivore.moveRule, Herbivore.actionRule);
  }

  @Override
  public String toString() {
    return "\uD83D\uDC30";
  }

}
