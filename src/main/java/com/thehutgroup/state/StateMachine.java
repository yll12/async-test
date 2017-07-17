package com.thehutgroup.state;

import static com.thehutgroup.state.State.Created;
import static java.util.concurrent.CompletableFuture.runAsync;

public class StateMachine {

  private State currentState;

  private StateMachine() {
  }

  private void createSlowly() {
    try {
      Thread.sleep(1000);
      currentState = Created;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public State getCurrentState() {
    return currentState;
  }


  public static StateMachine create() {
    final StateMachine stateMachine = new StateMachine();
    runAsync(stateMachine::createSlowly);
    return stateMachine;
  }
}
