package entity;



import static utility.EntityUtility.getColor;
import static utility.MapUtility.fieldIsEmpty;
import static utility.MapUtility.getRandomCoordinate;

import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import java.util.Map;
import main.GameMap;
import main.Graph;

public class Predator extends Creature {

    private final int power;

    public static void create(GameMap map) {
        Coordination coordination = getRandomCoordinate();
        if (fieldIsEmpty(coordination, map)) {
            setToMap(coordination, map);
        }
    }

    private static void setToMap(Coordination coordination, GameMap map){
        map.setMap(coordination, new Predator(10,10,10));
    }

    private boolean isDead(Creature herbivore) {
        int herbivoreHp = herbivore.getHp();
        if(herbivoreHp <= power) {
            return true;
        }
        herbivore.setHp(herbivoreHp - power);
        return false;
    }

    private void makeAttack(GameMap map, Coordination creatureCoordinate,
        Coordination goalCoordinate) {
        Creature herbivore = (Creature) map.getMap().get(goalCoordinate);
        if(isDead(herbivore)){
            upHp(map,creatureCoordinate);
            map.getMap().remove(goalCoordinate);
        }
    }

    @Override
    protected void upHp(GameMap map, Coordination creatureCoordinate) {
        Creature predator = (Creature) map.getMap().get(creatureCoordinate);
        predator.setHp(predator.getHp() + VALUE_OF_HERBIVORE);
    }

    @Override
    protected void makeAction(GameMap map, Coordination creatureCoordinate,
        Coordination followCoordinate) {
        makeAttack(map, creatureCoordinate, followCoordinate);
    }

    @Override
    protected boolean isGoal(GameMap map, Coordination followCoordinate) {
        return (map.getMap().get(followCoordinate) instanceof Herbivore);
    }

//    @Override
//    protected Coordination getGoalIfExistCoordinate(GameMap gameMap) {
//        for (Map.Entry<Coordination, Entity> element : gameMap.getMap().entrySet()) {
//            if (element.getValue() instanceof Herbivore) {
//                return element.getKey();
//            }
//        }
//        return null;
//    }

//    @Override
//    protected void setCoordinateSpecificCreature(Coordination outdatedCoordinate,
//        Coordination newCoordinate, GameMap map) {
//        map.getPredatorsCoordinates().remove(outdatedCoordinate);
//        map.setPredatorsCoordinates(newCoordinate);
//    }

    @Override
    protected boolean checkBarrier(GameMap map, Coordination node) {
        return ((map.getMap().get(node) instanceof Rock)
            || (map.getMap().get(node) instanceof Tree)
            || (map.getMap().get(node) instanceof Grass)
            || (map.getMap().get(node) instanceof Predator));
    }

    public Predator(int speed, int hp, int power) {
        super(speed, hp);
        this.power = power;
        sprite = getColor(PREDATOR_SPRITE);
    }
}
