package entity;

import java.util.Objects;

public class Coordination {
    private final int x;
    private final int y;


    public Coordination(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
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
