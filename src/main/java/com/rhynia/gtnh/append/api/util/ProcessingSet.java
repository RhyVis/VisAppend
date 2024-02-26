package com.rhynia.gtnh.append.api.util;

@SuppressWarnings("unused")
public class ProcessingSet {

    private final int pBaseParallel;
    private final float pBaseSpeedBonus;

    ProcessingSet(int p, float s) {
        pBaseParallel = p;
        pBaseSpeedBonus = s;
    }

    ProcessingSet(int p) {
        pBaseParallel = p;
        pBaseSpeedBonus = 1.0F;
    }

    ProcessingSet(float s) {
        pBaseParallel = 1;
        pBaseSpeedBonus = s;
    }

    public static ProcessingSet DEFAULT = new ProcessingSet(1, 1.0F);

    public int getBaseParallel() {
        return this.pBaseParallel;
    }

    public float getBaseSpeed() {
        return this.pBaseSpeedBonus;
    }

}
