package entity;


import static utility.EntityUtility.getColor;
import static utility.MapUtility.fieldIsEmpty;
import static utility.MapUtility.getRandomCoordinate;

import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import java.util.Map;
import main.GameMap;

public class Herbivore extends Creature {

    public Herbivore(int speed, int hp) {
        super(speed, hp);
        sprite = getColor(HERBIVORE_SPRITE);
    }

    public static void create(GameMap map) {
        Coordination coordination = getRandomCoordinate();
        if (fieldIsEmpty(coordination, map)) {
            setToMap(coordination, map);
        }
    }

    private static void setToMap(Coordination coordination, GameMap map){
        map.setMap(coordination, new Herbivore(10,10));
    }

    @Override
    protected void upHp(GameMap map, Coordination creatureCoordinate) {
        Creature herbivore = (Creature) map.getMap().get(creatureCoordinate);
        herbivore.setHp(herbivore.getHp() + VALUE_OF_GRASS);
    }

    private void eatGrass(GameMap map, Coordination creatureCoordinate,
        Coordination goalCoordinate) {
        upHp(map, creatureCoordinate);
        map.getMap().remove(goalCoordinate);
    }

    @Override
    protected void makeAction(GameMap map, Coordination creatureCoordinate,
        Coordination followCoordinate) {
        eatGrass(map, creatureCoordinate, followCoordinate);
    }

//    @Override
//    static protected Coordination getGoalIfExistCoordinate(GameMap gameMap) {
//        for (Map.Entry<Coordination, Entity> element : gameMap.getMap().entrySet()) {
//            if (element.getValue() instanceof Grass) {
//                return element.getKey();
//            }
//        }
//        return null;
//    }


//    @Override
//    protected void setCoordinateSpecificCreature(Coordination outdatedCoordinate,
//        Coordination newCoordinate, GameMap map) {
//        map.getHerbivoresCoordinates().remove(outdatedCoordinate);
//        map.setHerbivoresCoordinates(newCoordinate);
//    }

    @Override
    protected boolean isGoal(GameMap map, Coordination followCoordinate) {
        return (map.getMap().get(followCoordinate) instanceof Grass);
    }

    @Override
    protected boolean checkBarrier(GameMap map, Coordination node) {
        return ((map.getMap().get(node) instanceof Rock)
            || (map.getMap().get(node) instanceof Tree)
            || (map.getMap().get(node) instanceof Predator)
            || (map.getMap().get(node) instanceof Herbivore));
    }
}
