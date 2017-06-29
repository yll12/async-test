package com.thehutgroup.async;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import java.util.List;

public class Poller {

  private final int timeoutMillis;
  private final int pollDelayMillis;

  private long timeoutTime;

  public Poller(int timeoutMillis, int pollDelayMillis) {
    this.timeoutMillis = timeoutMillis;
    this.pollDelayMillis = pollDelayMillis;
  }

  public void check(Probe probe, Matcher<Iterable<? extends String>> matcher) throws InterruptedException {

    timeoutTime = System.currentTimeMillis() + timeoutMillis;

    List<String> sample = probe.sample();
    while (!matcher.matches(sample)) {

      if (timedOut())
        throw new AssertionError(describeFailureOf(sample, matcher));

      Thread.sleep(pollDelayMillis);
      sample = probe.sample();
    }
  }

  private boolean timedOut() {
    return System.currentTimeMillis() > timeoutTime;
  }

  private String describeFailureOf(List<String> sample, Matcher<Iterable<? extends String>> matcher) {
    Description description = new StringDescription();
    description.appendText("\nExpected: ")
               .appendDescriptionOf(matcher)
               .appendText("\n     but: ");
    matcher.describeMismatch(sample, description);
    return description.toString();
  }

}
