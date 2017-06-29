package com.thehutgroup.async;

import org.hamcrest.Matcher;

public class AssertEventually {

  private static final int TENTH_OF_A_SECOND_DELAY = 100;
  private static final int TEN_SECOND_TIMEOUT = 10 * 1000;

  public static void assertEventually(Probe probe, Matcher<Iterable<? extends String>> matcher) throws InterruptedException {
    new Poller(TEN_SECOND_TIMEOUT, TENTH_OF_A_SECOND_DELAY).check(probe, matcher);
  }
}
