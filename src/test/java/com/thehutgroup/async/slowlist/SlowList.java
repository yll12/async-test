package com.thehutgroup.async.slowlist;

import java.util.LinkedList;
import java.util.List;

public class SlowList {

  private List<String> list = new LinkedList<>();

  public void add(String element) {
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    list.add(element);
  }

  public List<String> getList() {
    return list;
  }
}
