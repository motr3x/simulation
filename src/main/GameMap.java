package main;

import entity.Coordination;
import entity.Entity;
import exception.EntityNotExistException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public final class GameMap {

  private final Map<Coordination, Entity> map = new LinkedHashMap<>();

  public void set(Coordination coordination, Entity entity) {
    map.put(coordination, entity);
  }

  public void shift(Coordination oldCoordinate, Coordination newCoordinate, Entity entity){
    map.put(newCoordinate, entity);
    map.remove(oldCoordinate);
  }

  public <T extends Entity> Optional<T> get(Coordination coordination,
      Class<T> type) {
    Entity entity = map.get(coordination);
    if (type.isInstance(entity)) {
      return Optional.of(type.cast(entity));
    }
    return Optional.empty();
  }

  public Optional<Entity> get(Coordination coordination) {
    Entity entity = map.get(coordination);
    return Optional.ofNullable(entity);
  }

  public void remove(Entity entity) {
    Coordination coordination = getPosition(entity).orElseThrow(
        () -> new EntityNotExistException("Entity doesn't exist"));
    map.remove(coordination);
  }


  public Optional<Coordination> getPosition(Entity entity) {
    for (Map.Entry<Coordination, Entity> entry : map.entrySet()) {
      if (Objects.equals(entity, entry.getValue())) {
        return Optional.of(entry.getKey());
      }
    }
    return Optional.empty();
  }

  public Set<Entry<Coordination, Entity>> getEntrySet() {
    Set<Entry<Coordination, Entity>> copyOfEntrySet = map.entrySet();
    return copyOfEntrySet;
  }

  public void remove(Coordination coordination) {
    map.remove(coordination);
  }
}
