package com.thehutgroup;

import java.util.LinkedList;
import java.util.List;

public class SlowList {

  private List<String> list = new LinkedList<>();

  void add(String element) {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    list.add(element);
  }

  public List<String> getList() {
    return list;
  }
}
