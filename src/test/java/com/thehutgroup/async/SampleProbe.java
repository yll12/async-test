package com.thehutgroup.async;

import com.thehutgroup.SlowList;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.List;

public class SampleProbe implements Probe {

  private SlowList                          slowList;
  private Matcher<? super Iterable<String>> matcher;
  private List<String>                      list;

  public SampleProbe(SlowList slowList, Matcher<? super Iterable<String>> matcher) {
    this.slowList = slowList;
    this.matcher = matcher;
  }

  @Override
  public boolean isSatisfied() {
    return matcher.matches(list);
  }

  @Override
  public void sample() {
    list = slowList.getList();
  }

  @Override
  public void describeFailureTo(Description d) {
    d.appendText("Expected: ")
     .appendDescriptionOf(matcher)
     .appendText("\n     but: ");

    d.appendText("was <");
    describeReceivedElements(d);
    d.appendText(">");
  }

  private void describeReceivedElements(Description d) {
    for (String elements : list) {
      d.appendText("[").appendValue(elements).appendText("] ");
    }
  }
}
