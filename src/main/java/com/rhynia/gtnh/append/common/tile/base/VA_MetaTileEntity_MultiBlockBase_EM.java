package com.rhynia.gtnh.append.common.tile.base;

import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;

import gregtech.api.logic.ProcessingLogic;

public abstract class VA_MetaTileEntity_MultiBlockBase_EM extends GT_MetaTileEntity_MultiblockBase_EM {

    // region Builder
    protected VA_MetaTileEntity_MultiBlockBase_EM(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }
    // endregion

    protected VA_MetaTileEntity_MultiBlockBase_EM(String aName) {
        super(aName);
    }

    // region ProcessingLogic Helper
    protected int uParallel = 1;
    protected float uSpeed = 1.0F;

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new ProcessingLogic().setMaxParallel(getMaxParallel())
            .setSpeedBonus(getSpeedBonus());
    }

    protected int getMaxParallel() {
        return uParallel;
    }

    protected float getSpeedBonus() {
        return uSpeed;
    }
    // endregion
}
