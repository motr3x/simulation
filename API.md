# Documentation
### InitAction.class
<table>
    <tr>
        <th>method</th>
        <th>return type</th>
        <th>description</th>
    </tr>
    <tr>
        <td>initMap()</td>
        <td>void</td>
        <td>-</td>
    </tr>
    <tr>
        <td>initGraph()</td>
        <td>void</td>
        <td>-</td>
    </tr>
    <tr>
        <td>fillingGraph()</td>
        <td>void</td>
        <td>-</td>
    </tr>
</table>

### TurnAction.class
<table>
    <tr>
        <th>method</th>
        <th>return type</th>
        <th>description</th>
    </tr>
    <tr>
        <td>makeMoveForEverybody()</td>
        <td>void</td>
        <td>-</td>
    </tr>
</table>

### Entity.class
<table>
    <tr>
        <th>method</th>
        <th>return type</th>
        <th>description</th>
    </tr>
    <tr>
        <td>getEntityCoordinate()</td>
        <td>Coordination</td>
        <td>-</td>
    </tr>
</table>

### Creature.class extend Entity.class
<table>
  <tr>
    <th>method</th>
    <th>return type</th>
    <th>description</th>
  </tr>
  <tr>
    <td>Creature(int speed, int hp)</td>
    <td>constructor</td>
    <td>создаёт существо с указанной скоростью и количеством жизней</td>
  </tr>
  <tr>
    <td>checkBarrier(GameMap map, Coordination node)</td>
    <td>boolean</td>
    <td>проверяет, является ли клетка препятствием для передвижения</td>
  </tr>
  <tr>
    <td>isGoal(GameMap map, Coordination followCoordinate)</td>
    <td>boolean</td>
    <td>проверяет, достигнута ли целевая клетка (трава для Herbivore или животное для Predator)</td>
  </tr>
  <tr>
    <td>upHp(GameMap map, Coordination creatureCoordinate)</td>
    <td>void</td>
    <td>увеличивает HP существа при достижении цели</td>
  </tr>
  <tr>
    <td>makeAction(GameMap map, Coordination creatureCoordinate, Coordination followCoordinate)</td>
    <td>void</td>
    <td>выполняет действие при достижении цели (поедание травы/животного)</td>
  </tr>
  <tr>
    <td>makeTrack(Map&lt;Coordination, List&lt;Coordination&gt;&gt; graph, Coordination start, Coordination goal, GameMap map)</td>
    <td>Optional&lt;Deque&lt;Coordination&gt;&gt;</td>
    <td>строит оптимальный путь от старта до цели используя BFS, возвращает путь или empty</td>
  </tr>
  <tr>
    <td>followTrack(GameMap gameMap, Graph graph, Queue&lt;Coordination&gt; creaturesCoordinates)</td>
    <td>void</td>
    <td>статический метод: последовательно перемещает всех существ по очереди, строя пути до целей</td>
  </tr>
  <tr>
    <td>moving(GameMap map, Coordination creatureCoordinate, Coordination followCoordinate)</td>
    <td>void</td>
    <td>перемещает существо на соседнюю клетку или вызывает makeAction если достигнута цель</td>
  </tr>
</table>

### Herbivore.class extend Creature.class

<table>
  <tr>
    <th>method</th>
    <th>return type</th>
    <th>description</th>
  </tr>
  <tr>
    <td>Herbivore(int speed, int hp)</td>
    <td>constructor</td>
    <td>создаёт травоядное с указанной скоростью и HP, устанавливает зелёный спрайт</td>
  </tr>
  <tr>
    <td>create(GameMap map)</td>
    <td>void</td>
    <td>статический метод: создаёт травоядное на случайной свободной клетке карты</td>
  </tr>
  <tr>
    <td>setToMap(Coordination coordination, GameMap map)</td>
    <td>void</td>
    <td>приватный статический: помещает новое травоядное (speed=10, hp=10) на карту</td>
  </tr>
  <tr>
    <td>upHp(GameMap map, Coordination creatureCoordinate)</td>
    <td>void</td>
    <td>увеличивает HP травоядного на VALUE_OF_GRASS при поедании травы</td>
  </tr>
  <tr>
    <td>eatGrass(GameMap map, Coordination creatureCoordinate, Coordination goalCoordinate)</td>
    <td>void</td>
    <td>приватный: вызывает upHp и удаляет траву с карты</td>
  </tr>
  <tr>
    <td>makeAction(GameMap map, Coordination creatureCoordinate, Coordination followCoordinate)</td>
    <td>void</td>
    <td>переопределённый: вызывает eatGrass при достижении цели</td>
  </tr>
  <tr>
    <td>isGoal(GameMap map, Coordination followCoordinate)</td>
    <td>boolean</td>
    <td>проверяет, является ли клетка травой (Grass) – цель травоядного</td>
  </tr>
  <tr>
    <td>checkBarrier(GameMap map, Coordination node)</td>
    <td>boolean</td>
    <td>проверяет, является ли клетка препятствием: Rock, Tree, Predator или другое Herbivore</td>
  </tr>
</table>

### Predator.class extend Creature.class

<table>
  <tr>
    <th>method</th>
    <th>return type</th>
    <th>description</th>
  </tr>
  <tr>
    <td>Predator(int speed, int hp, int power)</td>
    <td>constructor</td>
    <td>создаёт хищника с указанной скоростью, HP и силой атаки, устанавливает красный спрайт</td>
  </tr>
  <tr>
    <td>create(GameMap map)</td>
    <td>void</td>
    <td>статический метод: создаёт хищника на случайной свободной клетке карты</td>
  </tr>
  <tr>
    <td>setToMap(Coordination coordination, GameMap map)</td>
    <td>void</td>
    <td>приватный статический: помещает нового хищника (speed=10, hp=10, power=10) на карту</td>
  </tr>
  <tr>
    <td>isDead(Creature herbivore)</td>
    <td>boolean</td>
    <td>приватный: проверяет, умирает ли травоядное от атаки; если нет – уменьшает его HP на power</td>
  </tr>
  <tr>
    <td>makeAttack(GameMap map, Coordination creatureCoordinate, Coordination goalCoordinate)</td>
    <td>void</td>
    <td>приватный: атакует травоядное; если оно умерло – вызывает upHp и удаляет его с карты</td>
  </tr>
  <tr>
    <td>upHp(GameMap map, Coordination creatureCoordinate)</td>
    <td>void</td>
    <td>увеличивает HP хищника на VALUE_OF_HERBIVORE при убийстве травоядного</td>
  </tr>
  <tr>
    <td>makeAction(GameMap map, Coordination creatureCoordinate, Coordination followCoordinate)</td>
    <td>void</td>
    <td>переопределённый: вызывает makeAttack при достижении цели</td>
  </tr>
  <tr>
    <td>isGoal(GameMap map, Coordination followCoordinate)</td>
    <td>boolean</td>
    <td>проверяет, является ли клетка травоядным (Herbivore) – цель хищника</td>
  </tr>
  <tr>
    <td>checkBarrier(GameMap map, Coordination node)</td>
    <td>boolean</td>
    <td>проверяет, является ли клетка препятствием: Rock, Tree, Grass или другой Predator</td>
  </tr>
</table>

### Coordinate.class
<table>
  <tr>
    <th>method</th>
    <th>return type</th>
    <th>description</th>
  </tr>
  <tr>
    <td>Coordination(int x, int y)</td>
    <td>constructor</td>
    <td>создаёт координату с указанными значениями x и y</td>
  </tr>
  <tr>
    <td>getX()</td>
    <td>int</td>
    <td>возвращает координату X</td>
  </tr>
  <tr>
    <td>getY()</td>
    <td>int</td>
    <td>возвращает координату Y</td>
  </tr>
  <tr>
    <td>equals(Object o)</td>
    <td>boolean</td>
    <td>сравнивает две координаты: равны если x и y совпадают</td>
  </tr>
  <tr>
    <td>hashCode()</td>
    <td>int</td>
    <td>возвращает хеш-код на основе x и y</td>
  </tr>
  <tr>
    <td>toString()</td>
    <td>String</td>
    <td>возвращает строковое представление "Coordination{x=..., y=...}"</td>
  </tr>
</table>

### Simulation.class
<table>
  <tr>
    <th>method</th>
    <th>return type</th>
    <th>description</th>
  </tr>
  <tr>
    <td>Simulation(GameMap gameMap, Graph graph)</td>
    <td>constructor</td>
    <td>создаёт симуляцию с указанной картой и графом путей</td>
  </tr>
  <tr>
    <td>renderField(GameMap map)</td>
    <td>void</td>
    <td>статический метод: отображает текущее состояние карты в консоли с задержкой 500 мс</td>
  </tr>
  <tr>
    <td>nextTurn()</td>
    <td>void</td>
    <td>выполняет один ход симуляции: движение всех существ + рендер + очистка экрана</td>
  </tr>
  <tr>
    <td>startSimulation()</td>
    <td>void</td>
    <td>запускает бесконечный цикл симуляции (движение → рендер → очистка)</td>
  </tr>
  <tr>
    <td>pauseSimulation()</td>
    <td>void</td>
    <td>приостанавливает бесконечный цикл симуляции (тело метода пустое)</td>
  </tr>
</table>

### Graph.class

<table>
  <tr>
    <th>method</th>
    <th>return type</th>
    <th>description</th>
  </tr>
  <tr>
    <td>Graph()</td>
    <td>constructor</td>
    <td>создаёт пустой граф, инициализируя пустую HashMap</td>
  </tr>
  <tr>
    <td>setGraph(Coordination coordination, List&lt;Coordination&gt; coordinations)</td>
    <td>void</td>
    <td>добавляет в граф вершину с указанным списком смежных координат</td>
  </tr>
  <tr>
    <td>setAllGraph(Map&lt;Coordination, List&lt;Coordination&gt;&gt; copyOfGraph)</td>
    <td>void</td>
    <td>добавляет все вершины из переданной карты в текущий граф</td>
  </tr>
  <tr>
    <td>getGraph()</td>
    <td>Map&lt;Coordination, List&lt;Coordination&gt;&gt;</td>
    <td>возвращает граф в виде карты, где ключ – вершина, значение – список смежных координат</td>
  </tr>
</table>

### GameMap.class

<table>
  <tr>
    <th>method</th>
    <th>return type</th>
    <th>description</th>
  </tr>
  <tr>
    <td>GameMap()</td>
    <td>constructor</td>
    <td>создаёт пустую карту, инициализируя LinkedHashMap</td>
  </tr>
  <tr>
    <td>setMap(Coordination coordination, Entity entity)</td>
    <td>void</td>
    <td>помещает сущность на указанную координату</td>
  </tr>
  <tr>
    <td>getMap()</td>
    <td>Map&lt;Coordination, Entity&gt;</td>
    <td>возвращает карту в виде связной HashMap (ключ – координата, значение – сущность)</td>
  </tr>
</table>

### EntityUtility.class
<table>
  <tr>
    <th>method</th>
    <th>return type</th>
    <th>description</th>
  </tr>
  <tr>
    <td>getColor(String sprite)</td>
    <td>String</td>
    <td>оборачивает спрайт в ANSI-коды для случайного цвета (от 30 до 37) и сбрасывает форматирование</td>
  </tr>
</table>

### MapUtility.class
<table>
  <tr>
    <th>method</th>
    <th>return type</th>
    <th>description</th>
  </tr>
  <tr>
    <td>clearScreen()</td>
    <td>void</td>
    <td>очищает консоль с помощью ANSI-кодов (\033[H\033[2J)</td>
   </tr>
   <tr>
    <td>getCreaturesCoordinates(GameMap map)</td>
    <td>List&lt;Queue&lt;Coordination&gt;&gt;</td>
    <td>возвращает список из двух очередей: [0] – координаты травоядных, [1] – координаты хищников, добавляет недостающих существ</td>
   </tr>
   <tr>
    <td>creatMissingCreatures(Queue&lt;Coordination&gt; herbivoreCoordinates, Queue&lt;Coordination&gt; predatorCoordinates, GameMap map)</td>
    <td>void</td>
    <td>приватный: создаёт недостающих травоядных и хищников до минимального количества</td>
   </tr>
   <tr>
    <td>printInfoBar(GameMap map, int xCoordinate, int yCoordinate)</td>
    <td>void</td>
    <td>выводит информационную панель с HP существ в правом верхнем углу (TODO REFACTORING)</td>
   </tr>
   <tr>
    <td>getInfoByGrass(GameMap map)</td>
    <td>void</td>
    <td>собирает координаты травы и создаёт новую при недостаточном количестве (мин. 3)</td>
   </tr>
   <tr>
    <td>getRandomCoordinate()</td>
    <td>Coordination</td>
    <td>генерирует случайные координаты в пределах карты (от 1 до MAX)</td>
   </tr>
   <tr>
    <td>fieldIsEmpty(Coordination coordination, GameMap map)</td>
    <td>boolean</td>
    <td>проверяет, свободна ли клетка на карте (нет сущности)</td>
   </tr>
</table>