package com.thehutgroup.async;

import com.thehutgroup.slowlist.SlowList;

import java.util.List;

public class SlowListProbe implements Probe<List<String>> {

  private SlowList slowList;

  public SlowListProbe(SlowList slowList) {
    this.slowList = slowList;
  }

  @Override
  public List<String> sample() {
    return slowList.getList();
  }

}
