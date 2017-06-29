package com.thehutgroup;

import com.thehutgroup.async.SlowListProbe;

import org.junit.Test;

import static com.thehutgroup.async.AssertEventually.assertEventually;
import static java.util.concurrent.CompletableFuture.runAsync;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

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

  private String message() {
    return "\nExpected: iterable containing [\"fail\"]" +
           "\n     but: item 0: was \"succeed\"";
  }

  private SlowListProbe slowList() {
    return new SlowListProbe(slowList);
  }

}
