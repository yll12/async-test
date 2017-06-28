package com.thehutgroup;

import com.thehutgroup.async.SampleProbe;

import org.hamcrest.Matcher;
import org.junit.Test;

import static com.thehutgroup.async.AssertEventually.assertEventually;
import static java.util.concurrent.CompletableFuture.runAsync;
import static org.hamcrest.Matchers.contains;

public class SlowListAsyncTest {

  private SlowList slowList = new SlowList();

  @Test
  public void addElementToSlowList() throws InterruptedException {
    runAsync(() -> slowList.add("succeed"));

    assertEventually(
        slowList(
            contains("fail")));
  }

  private SampleProbe slowList(Matcher<? super Iterable<String>> matcher) {
    return new SampleProbe(slowList, matcher);
  }

}
