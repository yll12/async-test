package com.thehutgroup.async;

@FunctionalInterface
public interface Probe<T> {

  T sample();

}

