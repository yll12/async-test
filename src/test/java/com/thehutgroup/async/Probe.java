package com.thehutgroup.async;

import org.hamcrest.Description;

public interface Probe {

  boolean isSatisfied();
  void sample();
  void describeFailureTo(Description d);

}

