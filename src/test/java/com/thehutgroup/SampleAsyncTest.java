package com.thehutgroup;

import org.junit.Test;

import static com.thehutgroup.Sample.isDone;
import static java.util.concurrent.CompletableFuture.runAsync;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SampleAsyncTest {

  @Test
  public void asyncTest() {
    runAsync(Sample::test);
    assertThat(isDone(), is(true));
  }

}
