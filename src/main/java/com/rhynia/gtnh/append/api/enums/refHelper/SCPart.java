package com.rhynia.gtnh.append.api.enums.refHelper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.rhynia.gtnh.append.common.material.VA_Materials;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;

@SuppressWarnings("unused")
public enum SCPart {

    MV(Materials.SuperconductorMV, Materials.Pentacadmiummagnesiumhexaoxid),
    HV(Materials.SuperconductorHV, Materials.Titaniumonabariumdecacoppereikosaoxid),
    EV(Materials.SuperconductorEV, Materials.Uraniumtriplatinid),
    IV(Materials.SuperconductorIV, Materials.Vanadiumtriindinid),
    LuV(Materials.SuperconductorLuV, Materials.Tetraindiumditindibariumtitaniumheptacoppertetrakaidekaoxid),
    ZPM(Materials.SuperconductorZPM, Materials.Tetranaquadahdiindiumhexaplatiumosminid),
    UV(Materials.SuperconductorUV, Materials.Longasssuperconductornameforuvwire),
    UHV(Materials.SuperconductorUHV, Materials.Longasssuperconductornameforuhvwire),
    UEV(Materials.SuperconductorUEV, Materials.SuperconductorUEVBase),
    UIV(Materials.SuperconductorUIV, Materials.SuperconductorUIVBase),
    UMV(Materials.SuperconductorUMV, Materials.SuperconductorUMVBase);

    private final Materials production, raw;

    SCPart(Materials production, Materials raw) {
        this.production = production;
        this.raw = raw;
    }

    /**
     * Get the Material
     *
     * @param base if superconductor base
     * @return Material in GT
     */
    public final Materials getMaterial(boolean base) {
        return base ? this.raw : this.production;
    }

    public final int getMultiplier() {
        return this.ordinal() + 1;
    }

    public final int getPowValue() {
        return (int) Math.pow(2, this.ordinal());
    }

    /**
     * @return Wire material voltage
     */
    public final long getVoltage() {
        return GT_Values.V[this.ordinal() + 2];
    }

    /**
     * @return Wire material voltage
     */
    public final long getRecipeVoltage() {
        return GT_Values.VP[this.ordinal() + 2];
    }

    /**
     * @return Wire material voltage lower 1 level
     */
    public final long getRecipeVoltageLow() {
        return GT_Values.VP[this.ordinal() + 1];
    }

    /**
     * @return Wire material voltage higher 1 level
     */
    public final long getRecipeVoltageHigh() {
        return GT_Values.VP[this.ordinal() + 3];
    }

    /**
     * @return GT Wire x1, by default superconductor
     */
    public final ItemStack getWire(int amount) {
        return GT_OreDictUnificator.get(OrePrefixes.wireGt01, this.production, amount);
    }

    /**
     * @return GT Wire x1
     */
    public final ItemStack getWire(int amount, boolean base) {
        return GT_OreDictUnificator.get(OrePrefixes.wireGt01, this.getMaterial(base), amount);
    }

    /**
     * @return GT Fine Wire
     */
    public final ItemStack getWireFine(int amount) {
        return GT_OreDictUnificator.get(OrePrefixes.wireFine, this.raw, amount);
    }

    /**
     * @return GT Frame of base material
     */
    public ItemStack getFrame(int amount) {
        return GT_OreDictUnificator.get(OrePrefixes.frameGt, this.raw, amount);
    }

    /**
     * @return Dust of base material
     */
    public ItemStack getDust(int amount) {
        return this.raw.getDust(amount);
    }

    /**
     * @return Pumps used to assemble wire
     */
    public ItemStack getPump(int amount) {
        return Tier.valueOf(this.toString())
            .getComponent(Tier.Component.Electric_Pump, amount);
    }

    /**
     * @return Solenoid of base material
     */
    public ItemStack getSolenoid(int amount) {
        return ItemList.valueOf("Superconducting_Magnet_Solenoid_" + this)
            .get(amount);
    }

    public ItemStack getPrefix(OrePrefixes prefix, int amount) {
        return getPrefix(prefix, amount, false);
    }

    public ItemStack getPrefix(OrePrefixes prefix, int amount, boolean base) {
        return GT_OreDictUnificator.get(prefix, base ? this.raw : this.production, amount);
    }

    public FluidStack getSxEqualFluid(int originalAmount) {
        return VA_Materials.SuperconductorFlux.getFluidOrGas((int) Math.pow(2, this.ordinal()) * originalAmount);
    }

    public FluidStack getSxEqualFluid(int originalAmount, boolean raw) {
        if (!raw) {
            return getSxEqualFluid(originalAmount);
        } else {
            return VA_Materials.SuperconductorFlux
                .getFluidOrGas((int) Math.pow(2, this.ordinal()) * originalAmount * 4);
        }
    }

    /**
     * @return Molten base material
     */
    public FluidStack getMolten(int amount) {
        return this.getMaterial(true)
            .getMolten(amount);
    }
}
