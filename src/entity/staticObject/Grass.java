package entity.staticObject;

import static utility.MapUtility.fieldIsEmpty;
import static utility.MapUtility.getRandomCoordinate;

import entity.Coordination;
import entity.Entity;
import main.GameMap;

public class Grass extends Entity {
    @Override
    public String toString() {
        return "§";
    }

    public static void create(GameMap map) {
        Coordination coordination = getRandomCoordinate();
        if (fieldIsEmpty(coordination, map)) {
            Grass.setToMap(coordination, map);
        }
    }

    private static void setToMap(Coordination coordination, GameMap map){
        map.setMap(coordination, new Grass());
    }
}
