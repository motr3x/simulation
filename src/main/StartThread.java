package main;

public class StartThread implements Runnable {

  final Simulation simulation;

  public StartThread(Simulation simulation) {
    this.simulation = simulation;
  }

  @Override
  public void run() {
    simulation.startSimulation();
  }
}
