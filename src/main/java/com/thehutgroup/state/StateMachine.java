package com.thehutgroup.state;

import static com.thehutgroup.state.State.Completed;
import static com.thehutgroup.state.State.Created;
import static com.thehutgroup.state.State.Submitted;
import static com.thehutgroup.state.State.Validating;
import static java.util.concurrent.CompletableFuture.runAsync;

public class StateMachine {

  private State currentState = Created;

  private StateMachine() {
  }

  public State getCurrentState() {
    return currentState;
  }

  private void validate() {
    sleep(963);
    currentState = Validating;
    submit();
  }

  private void submit() {
    sleep(454);
    currentState = Submitted;
    complete();
  }

  private void complete() {
    sleep(812);
    currentState = Completed;
  }

  private static void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }


  public static StateMachine create() {
    final StateMachine stateMachine = new StateMachine();
    runAsync(stateMachine::validate);
    return stateMachine;
  }
}
