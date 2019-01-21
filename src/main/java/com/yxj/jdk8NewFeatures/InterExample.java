package com.yxj.jdk8NewFeatures;

import  java.lang.FunctionalInterface;

@FunctionalInterface
public interface InterExample<F,T> {

    public abstract  T run(F a);
}
