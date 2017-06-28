package com.thehutgroup.async;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;

public class Poller {

  private final int timeoutMillis;
  private final int pollDelayMillis;

  private long timeoutTime;

  public Poller(int timeoutMillis, int pollDelayMillis) {
    this.timeoutMillis = timeoutMillis;
    this.pollDelayMillis = pollDelayMillis;
  }

  public void check(Probe probe) throws InterruptedException {

    timeoutTime = System.currentTimeMillis() + timeoutMillis;

    while (!probe.isSatisfied()) {

      if (timedOut())
        throw new AssertionError(describeFailureOf(probe));

      Thread.sleep(pollDelayMillis);

      probe.sample();
    }
  }

  private boolean timedOut() {
    return System.currentTimeMillis() > timeoutTime;
  }

  private String describeFailureOf(Probe probe) {
    Description description = new StringDescription();
    probe.describeFailureTo(description);
    return description.toString();
  }
}
