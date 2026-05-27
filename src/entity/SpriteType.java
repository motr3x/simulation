package entity;

public enum SpriteType {
  EMPTY("\uD83D\uDFEB"),
  HERBIVORE("\uD83D\uDC30"),
  PREDATOR("\uD83D\uDC3A"),
  GRASS("\uD83C\uDF3F"),
  TREE("\uD83C\uDF33"),
  ROCK("\uD83D\uDDFF");

  private final String code;

  SpriteType(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
