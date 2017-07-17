package com.thehutgroup;

import com.thehutgroup.state.StateMachine;

import org.junit.Test;

import static com.thehutgroup.async.AssertEventually.assertEventually;
import static com.thehutgroup.state.State.Completed;
import static org.hamcrest.Matchers.equalTo;

public class StateMachineTest {

  @Test
  public void stateTest() throws InterruptedException {
    StateMachine stateMachine = StateMachine.create();
    assertEventually(stateMachine::getCurrentState, equalTo(Completed));
  }

}
