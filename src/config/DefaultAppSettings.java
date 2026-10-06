package config;

public class DefaultAppSettings implements AppSettingFactory {

  private static final int BOARD_WIDTH = 10;
  private static final int BOARD_HEIGHT = 10;

  @Override
  public AppSettings get() {
    AppSettings appSettings = new AppSettings();

    appSettings.boardWidth = BOARD_WIDTH;
    appSettings.boardHeight = BOARD_HEIGHT;

    return appSettings;
  }
}
