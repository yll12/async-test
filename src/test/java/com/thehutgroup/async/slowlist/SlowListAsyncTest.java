package com.thehutgroup.async.slowlist;

import com.thehutgroup.async.Probe;

import org.junit.Test;

import static com.thehutgroup.async.AssertEventually.assertEventually;
import static java.util.concurrent.CompletableFuture.runAsync;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class SlowListAsyncTest {

  private SlowList slowList = new SlowList();

  @Test
  public void addElementToSlowList() throws InterruptedException {
    runAsync(() -> slowList.add("succeed"));

//    assertThat(asList("succeed"), contains("fail"));
    assertEventually(
        slowList(), contains("succeed"));
  }

  @Test
  public void failingTest() throws InterruptedException {
    runAsync(() -> slowList.add("succeed"));
    try {
      assertEventually(
          slowList(), contains("fail"));
    } catch (AssertionError e) {
      assertThat(e.getMessage(), equalTo(
          message()
      ));
    }
  }

  @Test
  public void counterTest() throws InterruptedException {
    final Probe<Integer> probe = new Probe<Integer>() {
      int i = 0;

      @Override
      public Integer sample() {
        return i++;
      }
    };
    assertEventually(probe, greaterThan(3));
  }

  private String message() {
    return "\nExpected: iterable containing [\"fail\"]" +
           "\n     but: item 0: was \"succeed\"";
  }

  private SlowListProbe slowList() {
    return new SlowListProbe(slowList);
  }

}
