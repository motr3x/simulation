package main;

import java.util.Scanner;
import config.AppSettingFactory;
import config.AppSettings;
import config.DefaultAppSettings;
import config.InputAppSettingFactory;

public class Main {
  private static final int DEFAULT_SETTINGS = 1;
  private static final int INPUT_SETTINGS = 0;
  public static void main(String[] args) {
    int settingsMode = getSettingsMode();
    AppSettingFactory appSettingFactory = getFactory(settingsMode);
    AppSettings appSettings = appSettingFactory.get();

    GameMap gameMap = new GameMap(appSettings.boardWidth, appSettings.boardHeight);
    Graph graph = new Graph();
    Boolean stopFlag = Boolean.FALSE;
    Simulation simulation = new Simulation(gameMap, graph, stopFlag);
    Menu menu = new Menu(simulation, stopFlag);
    menu.printMenu();
  }

  private static int getSettingsMode(){
    System.out.println("Открыть меню с настройками? (y/n)");
    Scanner scanner = new Scanner(System.in);
    String answer = scanner.nextLine();
    if(answer.equals("y")){
      return INPUT_SETTINGS;
    }
    return DEFAULT_SETTINGS;
  }
  private static AppSettingFactory getFactory(int settingsMode) {
    return switch (settingsMode) {
      case DEFAULT_SETTINGS -> new DefaultAppSettings();
      case INPUT_SETTINGS -> new InputAppSettingFactory();
      default -> throw new IllegalArgumentException();
    };
  }

}