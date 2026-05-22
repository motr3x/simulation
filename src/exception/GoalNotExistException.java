package exception;

public class GoalNotExistException extends RuntimeException {

  public GoalNotExistException(String message) {
    super(message);
  }
}
