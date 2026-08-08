package main;


import java.util.Scanner;

public class Menu{

  private boolean simulationFlag = true;
  Boolean stopFlag;
  private final Simulation simulation;
  public final static String POSITIVE_DECISION = "y";
  public final static String NEGATIVE_DECISION = "n";
  public final static String STOP_DECISION = "s";
  public final static String INCORRECT_INPUT = "Incorrect input. Try again.";
  public final static String START = "start";
  public final static String CONTINUE = "continue";
  public final static String QUESTION = "Do you want %s simulation? %s/%s";
  public final static String STOP_MESSAGE = "You stop simulation";
  public final static String END_MESSAGE = "The end";

  public Menu(Simulation simulation, Boolean stopFlag) {
    this.simulation = simulation;
    this.stopFlag = stopFlag;
  }

  public void printMenu(){
    Scanner scanner = new Scanner(System.in);

    System.out.printf(QUESTION, START, POSITIVE_DECISION, NEGATIVE_DECISION);
    String startInput = scanner.next().toLowerCase();
    while (!isCorrectInput(startInput)){
      System.out.println(INCORRECT_INPUT);
      System.out.printf(QUESTION, START, POSITIVE_DECISION, NEGATIVE_DECISION);
      startInput = scanner.next().toLowerCase();
    }
    while (simulationFlag) {
      if(startInput.equals(POSITIVE_DECISION)){
        //Start simulation
        Thread startThread = new Thread(new StartThread(simulation));
        startThread.start();
        String decisionInput = scanner.next().toLowerCase();
        if(decisionInput.equals(STOP_DECISION)){
          //Pause simulation
          simulation.pauseSimulation();
          System.out.println(STOP_MESSAGE);
          System.out.printf(QUESTION, CONTINUE, POSITIVE_DECISION, NEGATIVE_DECISION);
          decisionInput = scanner.next();
          while (!isCorrectInput(decisionInput)){
            System.out.println(INCORRECT_INPUT);
            System.out.println(STOP_MESSAGE);
            System.out.printf(QUESTION, CONTINUE, POSITIVE_DECISION, NEGATIVE_DECISION);
            decisionInput = scanner.next().toLowerCase();
          }
          if(decisionInput.equals(POSITIVE_DECISION)){
            simulation.continueSimulation();
          }
          if(decisionInput.equals(NEGATIVE_DECISION)){
            //End simulation
            finishSimulation();
            System.out.println(END_MESSAGE);
          }
        }
      }
      if(startInput.equals(NEGATIVE_DECISION)){
        //End simulation
        finishSimulation();
        System.out.println(END_MESSAGE);
      }
    }
  }
  public void finishSimulation(){
    simulationFlag = false;
  }
  public boolean isCorrectInput(String input){
    return (input.equals(POSITIVE_DECISION) || input.equals(NEGATIVE_DECISION));
  }

}
