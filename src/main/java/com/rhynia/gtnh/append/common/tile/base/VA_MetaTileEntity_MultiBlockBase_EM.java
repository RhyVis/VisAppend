package com.rhynia.gtnh.append.common.tile.base;

import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.gtnewhorizon.structurelib.alignment.constructable.IConstructable;
import com.gtnewhorizon.structurelib.alignment.constructable.ISurvivalConstructable;

public abstract class VA_MetaTileEntity_MultiBlockBase_EM extends GT_MetaTileEntity_MultiblockBase_EM
    implements IConstructable, ISurvivalConstructable {

    // region Builder
    protected VA_MetaTileEntity_MultiBlockBase_EM(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    protected VA_MetaTileEntity_MultiBlockBase_EM(String aName) {
        super(aName);
    }
    // endregion

}
