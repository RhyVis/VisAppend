package com.rhynia.gtnh.append.common.tileentity.singleMachine;

import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import com.rhynia.gtnh.append.common.tileentity.base.VA_MetaTileEntity_Hatch_FluidGenerator;

import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.objects.GT_RenderedTexture;
import gtPlusPlus.core.util.minecraft.FluidUtils;

public class VA_MetaTileEntity_Hatch_Steam extends VA_MetaTileEntity_Hatch_FluidGenerator {

    public VA_MetaTileEntity_Hatch_Steam(final int aID, final String aName, final String aNameRegional,
        final int aTier) {
        super(aID, aName, aNameRegional, aTier);
    }

    public VA_MetaTileEntity_Hatch_Steam(final String aName, final int aTier, final String[] aDescription,
        final ITexture[][][] aTextures) {
        super(aName, aTier, aDescription, aTextures);
    }

    @Override
    public MetaTileEntity newMetaEntity(final IGregTechTileEntity aTileEntity) {
        return new VA_MetaTileEntity_Hatch_Steam(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    @Override
    public String[] getCustomTooltip() {
        String[] aTooltip = new String[4];
        aTooltip[0] = "内置强劲锅炉.";
        aTooltip[1] = "每5秒填充至最大容量.";
        aTooltip[2] = "蓄水仓里的水都沸腾了!";
        return aTooltip;
    }

    @Override
    public Fluid getFluidToGenerate() {
        return FluidUtils.getFluidStack("steam", 1)
            .getFluid();
    }

    @Override
    public int getAmountOfFluidToGenerate() {
        return 2_000_000_000;
    }

    @Override
    public int getMaxTickTime() {
        return 100;
    }

    @Override
    public int getCapacity() {
        return 2_000_000_000;
    }

    @Override
    public boolean doesHatchMeetConditionsToGenerate() {
        return true;
    }

    @Override
    public void generateParticles(World aWorld, String name) {}

    @Override
    public ITexture[] getTexturesActive(final ITexture aBaseTexture) {
        return new ITexture[] { aBaseTexture, new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_FUSION1) };
    }

    @Override
    public ITexture[] getTexturesInactive(final ITexture aBaseTexture) {
        return new ITexture[] { aBaseTexture, new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_FUSION1) };
    }
}
