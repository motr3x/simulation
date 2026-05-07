package main;

import entity.Coordination;
import entity.Entity;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GameMap {

  private final Map<Coordination, Entity> map = new LinkedHashMap<>();

  public void setMap(Coordination coordination, Entity entity) {
    map.put(coordination, entity);
  }

  public Entity getEntityByCoordinate(Coordination coordination){
    return map.get(coordination);
  }

  public Set<Entry<Coordination, Entity>> getEntrySet(){
    return map.entrySet();
  }

  public void removeEntityByCoordinate(Coordination coordination){
    map.remove(coordination);
  }
}
