package utility;

import java.util.Random;

public class EntityUtility {

  protected static final Random random = new Random();
  private static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_SKELETON = "\u001B[";
  public static final String ANSI_ENDING = "m";

  public static String getColor(String sprite){
    String randomColor = String.valueOf(random.nextInt(30, 38));
    return ANSI_SKELETON + randomColor + ANSI_ENDING + sprite + ANSI_RESET;
  }

}
