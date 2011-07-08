package com.tw.cucumberTest;

import cuke4duke.internal.jvmclass.SpringFactory;

public class MySpringFactory extends SpringFactory {
    @Override
    public void createObjects() {
       //I found the applicationContext.refresh cause a memory leak
    }
}

