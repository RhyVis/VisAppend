package com.rhynia.gtnh.append.common.tile.blockMachine;

import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_Input;

public class VA_MetaTileEntity_HumongousCalibrationInputHatch extends GT_MetaTileEntity_Hatch_Input {

    public VA_MetaTileEntity_HumongousCalibrationInputHatch(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional, 13);
        this.mDescriptionArray[1] = "容量: 1,000,000,000L";
    }

    public VA_MetaTileEntity_HumongousCalibrationInputHatch(String aName, int aTier, String[] aDescription,
        ITexture[][][] aTextures) {
        super(aName, aTier, aDescription, aTextures);
    }

    @Override
    public int getCapacity() {
        return 1_000_000_000;
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new VA_MetaTileEntity_HumongousCalibrationInputHatch(
            this.mName,
            this.mTier,
            this.mDescriptionArray,
            this.mTextures);
    }
}
