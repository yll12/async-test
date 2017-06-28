package com.thehutgroup;

class Sample {

  private static boolean done;

  static void test() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    done = true;
  }

  static boolean isDone() {
    return done;
  }
}
