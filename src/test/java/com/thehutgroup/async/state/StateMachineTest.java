package com.thehutgroup.async.state;

import org.junit.Test;

import static com.thehutgroup.async.AsynchronousAssert.assertEventually;
import static com.thehutgroup.async.state.State.Completed;
import static org.hamcrest.Matchers.equalTo;

public class StateMachineTest {

  @Test
  public void stateTest() throws InterruptedException {
    StateMachine stateMachine = StateMachine.create();
    assertEventually(stateMachine::getCurrentState, equalTo(Completed));
  }

}
