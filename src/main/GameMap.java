package main;


import entity.Coordinates;
import entity.Entity;
import exception.EntityNotExistException;
import exception.InvalidCoordinate;
import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class GameMap {

  public static final int MIN_COORDINATE = 1;
  private final Map<Coordinates, Entity> entities = new LinkedHashMap<>();
  private final int boardWidth;
  private final int boardHeight;

  public GameMap(int boardWidth, int boardHeight) {
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
  }

  public Optional<Entity> get(Coordinates coordinates) {
    validate(coordinates);
    Entity entity = entities.get(coordinates);
    return Optional.ofNullable(entity);
  }

  public <T extends Entity> Optional<T> get(Coordinates coordinates, Class<T> type) {
    validate(coordinates);
    Entity entity = entities.get(coordinates);
    if (type.isInstance(entity)) {
      return Optional.of(type.cast(entity));
    }
    return Optional.empty();
  }

  public Set<Entry<Coordinates, Entity>> getEntitiesSet() {
    Set<Entry<Coordinates, Entity>> copyOfEntrySet = entities.entrySet();
    return copyOfEntrySet;
  }

  public Optional<Coordinates> getCoordinate(Entity entity) {
    for (Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
      if (Objects.equals(entity, entry.getValue())) {
        return Optional.of(entry.getKey());
      }
    }
    return Optional.empty();
  }

  public <T extends Entity> Queue<Coordinates> getCoordinates(GameMap gameMap, Class<T> type) {
    Queue<Coordinates> coordinates = new ArrayDeque<>();
    for (Map.Entry<Coordinates, Entity> element : gameMap.getEntitiesSet()) {
      if (type.isInstance(element.getValue())) {
        coordinates.add(element.getKey());
      }
    }
    return coordinates;
  }

  public void add(Coordinates coordinates, Entity entity) {
    validate(coordinates);
    entities.put(coordinates, entity);
  }

  public void remove(Entity entity) {
    Coordinates coordinates = getCoordinate(entity).orElseThrow(
        () -> new EntityNotExistException("Entity doesn't exist"));
    entities.remove(coordinates);
  }

  public void remove(Coordinates coordinates) {
    validate(coordinates);
    entities.remove(coordinates);
  }

  public void validate(Coordinates coordinates) {
    if ((coordinates.x() <= boardWidth) && (coordinates.y() <= boardHeight)) {
      return;
    }
    throw new InvalidCoordinate("Invalid coordinates");

  }

  public void shift(Coordinates oldCoordinate, Coordinates newCoordinate, Entity entity) {
    validate(oldCoordinate);
    validate(newCoordinate);
    entities.put(newCoordinate, entity);
    entities.remove(oldCoordinate);
  }

  public boolean CoordinatesIsEmpty(Coordinates coordinates) {
    validate(coordinates);
    return this.get(coordinates, Entity.class).isEmpty();
  }

  public static <T> boolean checkClassType(GameMap gameMap, Coordinates coordinates,
      Class<T> type) {
    boolean emptyCell = false;
    Optional<Entity> entity = gameMap.get(coordinates, Entity.class);
    return entity.map(type::isInstance).orElse(emptyCell);
  }

  public static Coordinates getRandomEmptyPosition(GameMap gameMap) {
    Random random = new Random();
    int xCoordinate = random.nextInt(MIN_COORDINATE, gameMap.boardWidth);
    int yCoordinate = random.nextInt(MIN_COORDINATE, gameMap.boardHeight);
    Coordinates coordinates = new Coordinates(xCoordinate, yCoordinate);
    while (!gameMap.CoordinatesIsEmpty(coordinates)) {
      xCoordinate = random.nextInt(MIN_COORDINATE, gameMap.boardWidth);
      yCoordinate = random.nextInt(MIN_COORDINATE, gameMap.boardHeight);
      coordinates = new Coordinates(xCoordinate, yCoordinate);
    }
    return coordinates;
  }
}
