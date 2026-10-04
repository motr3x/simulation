package utility;

public class DefaultAppSettings implements AppSettingFactory {

  private static final int BOARD_WIDTH = 10;
  private static final int BOARD_HEIGHT = 10;

  private static final int INIT_COUNT_OF_HERBIVORE = 3;
  private static final int INIT_COUNT_OF_PREDATOR = 3;
  private static final int INIT_COUNT_OF_GRASS = 7;
  private static final int INIT_COUNT_OF_TREE = 2;
  private static final int INIT_COUNT_OF_ROCK = 1;
  private static final int MIN_COUNT_OF_GRASS = 6;
  private static final int COST_OF_GRASS = 5;
  private static final int COST_OF_HERBIVORE = 5;

  @Override
  public AppSettings get() {
    AppSettings appSettings = new AppSettings();

    appSettings.boardWidth = BOARD_WIDTH;
    appSettings.boardHeight = BOARD_HEIGHT;

    appSettings.initCountOfHerbivore = INIT_COUNT_OF_HERBIVORE;
    appSettings.initCountOfPredator = INIT_COUNT_OF_PREDATOR;
    appSettings.initCountOfTree = INIT_COUNT_OF_TREE;
    appSettings.initCountOfRock = INIT_COUNT_OF_ROCK;
    appSettings.initCountOfGrass = INIT_COUNT_OF_GRASS;
    appSettings.minCountOfGrass = MIN_COUNT_OF_GRASS;
    appSettings.costOfGrass = COST_OF_GRASS;
    appSettings.costOfHerbivore = COST_OF_HERBIVORE;

    return appSettings;
  }
}
