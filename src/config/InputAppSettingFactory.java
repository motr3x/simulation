package config;

import java.util.Scanner;

public class InputAppSettingFactory implements AppSettingFactory {
  private static final int MIN_BOARD_WIDTH = 3;
  private static final int MAX_BOARD_WIDTH = 99;
  private static final int MIN_BOARD_HEIGHT = 3;
  private static final int MAX_BOARD_HEIGHT = 99;


  @Override
  public AppSettings get() {
    AppSettings appSettings = new AppSettings();
    System.out.println("Введите данные");
    String failMessage = "Ошибка ввода";
    appSettings.boardWidth = input("Ширина карты", failMessage, MIN_BOARD_WIDTH, MAX_BOARD_WIDTH);
    appSettings.boardHeight = input("Высота карты", failMessage, MIN_BOARD_HEIGHT, MAX_BOARD_HEIGHT);;
    return appSettings;
  }

  private int input(String title, String failMessage, int min, int max){
    Scanner scanner = new Scanner(System.in);
    while(true){
      System.out.printf("%s {%d-%d}: ", title, min, max);
      String line = scanner.nextLine();
      if(isInteger(line)){
        int value = Integer.parseInt(line);
        if(value >= min && value <= max){
          return value;
        }
      }
      System.out.println(failMessage);
    }
  }

  private static boolean isInteger(String s){
    try {
      Integer.parseInt(s);
      return true;
    } catch (NumberFormatException e){
      return false;
    }
  }
}
