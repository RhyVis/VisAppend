package com.rhynia.gtnh.append.api.util;

@SuppressWarnings("unused")
public class ProcessingSet {

    private final int pBaseParallel;

    ProcessingSet(int parallel) {
        pBaseParallel = parallel;
    }

    public int getBaseParallel() {
        return this.pBaseParallel;
    }

}
