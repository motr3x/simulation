package entity;

import java.util.Objects;

public record Coordination(int x, int y) {

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Coordination that = (Coordination) o;
    return x == that.x && y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "Coordination{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }
}
