package com.rhynia.gtnh.append.api.enums.refHelper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.rhynia.gtnh.append.common.material.VAMaterials;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;

@SuppressWarnings("unused")
public enum SCPart {

    MV,
    HV,
    EV,
    IV,
    LuV,
    ZPM,
    UV,
    UHV,
    UEV,
    UIV,
    UMV;

    /**
     * Get the Material
     *
     * @param base if superconductor base
     * @return Material in GT
     */
    public final Materials getMaterial(boolean base) {
        if (!base) {
            return switch (this) {
                case MV -> Materials.SuperconductorMV;
                case HV -> Materials.SuperconductorHV;
                case EV -> Materials.SuperconductorEV;
                case IV -> Materials.SuperconductorIV;
                case LuV -> Materials.SuperconductorLuV;
                case ZPM -> Materials.SuperconductorZPM;
                case UV -> Materials.SuperconductorUV;
                case UHV -> Materials.SuperconductorUHV;
                case UEV -> Materials.SuperconductorUEV;
                case UIV -> Materials.SuperconductorUIV;
                case UMV -> Materials.SuperconductorUMV;
            };
        } else {
            return switch (this) {
                case MV -> Materials.Pentacadmiummagnesiumhexaoxid;
                case HV -> Materials.Titaniumonabariumdecacoppereikosaoxid;
                case EV -> Materials.Uraniumtriplatinid;
                case IV -> Materials.Vanadiumtriindinid;
                case LuV -> Materials.Tetraindiumditindibariumtitaniumheptacoppertetrakaidekaoxid;
                case ZPM -> Materials.Tetranaquadahdiindiumhexaplatiumosminid;
                case UV -> Materials.Longasssuperconductornameforuvwire;
                case UHV -> Materials.Longasssuperconductornameforuhvwire;
                case UEV -> Materials.SuperconductorUEVBase;
                case UIV -> Materials.SuperconductorUIVBase;
                case UMV -> Materials.SuperconductorUMVBase;
            };
        }
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
        return GT_OreDictUnificator.get(OrePrefixes.wireGt01, this.getMaterial(false), amount);
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
        return GT_OreDictUnificator.get(OrePrefixes.wireFine, this.getMaterial(true), amount);
    }

    /**
     * @return GT Frame of base material
     */
    public ItemStack getFrame(int amount) {
        return GT_OreDictUnificator.get(OrePrefixes.frameGt, this.getMaterial(true), amount);
    }

    /**
     * @return Dust of base material
     */
    public ItemStack getDust(int amount) {
        return this.getMaterial(true)
            .getDust(amount);
    }

    /**
     * @return Pumps used to assemble wire
     */
    public ItemStack getPump(int amount) {
        return switch (this) {
            case MV -> ItemList.Electric_Pump_MV.get(amount);
            case HV -> ItemList.Electric_Pump_HV.get(amount);
            case EV -> ItemList.Electric_Pump_EV.get(amount);
            case IV -> ItemList.Electric_Pump_IV.get(amount);
            case LuV -> ItemList.Electric_Pump_LuV.get(amount);
            case ZPM -> ItemList.Electric_Pump_ZPM.get(amount);
            case UV -> ItemList.Electric_Pump_UV.get(amount);
            case UHV -> ItemList.Electric_Pump_UHV.get(amount);
            case UEV -> ItemList.Electric_Pump_UEV.get(amount);
            case UIV -> ItemList.Electric_Pump_UIV.get(amount);
            case UMV -> ItemList.Electric_Pump_UMV.get(amount);
        };
    }

    /**
     * @return Solenoid of base material
     */
    public ItemStack getSolenoid(int amount) {
        return switch (this) {
            case MV -> ItemList.Superconducting_Magnet_Solenoid_MV.get(amount);
            case HV -> ItemList.Superconducting_Magnet_Solenoid_HV.get(amount);
            case EV -> ItemList.Superconducting_Magnet_Solenoid_EV.get(amount);
            case IV -> ItemList.Superconducting_Magnet_Solenoid_IV.get(amount);
            case LuV -> ItemList.Superconducting_Magnet_Solenoid_LuV.get(amount);
            case ZPM -> ItemList.Superconducting_Magnet_Solenoid_ZPM.get(amount);
            case UV -> ItemList.Superconducting_Magnet_Solenoid_UV.get(amount);
            case UHV -> ItemList.Superconducting_Magnet_Solenoid_UHV.get(amount);
            case UEV -> ItemList.Superconducting_Magnet_Solenoid_UEV.get(amount);
            case UIV -> ItemList.Superconducting_Magnet_Solenoid_UIV.get(amount);
            case UMV -> ItemList.Superconducting_Magnet_Solenoid_UMV.get(amount);
        };
    }

    public FluidStack getSxEqualFluid(int originalAmount) {
        return VAMaterials.SuperconductorFlux.getFluidOrGas((int) Math.pow(2, this.ordinal()) * originalAmount);
    }

    public FluidStack getSxEqualFluid(int originalAmount, boolean raw) {
        if (!raw) {
            return getSxEqualFluid(originalAmount);
        } else {
            return VAMaterials.SuperconductorFlux.getFluidOrGas((int) Math.pow(2, this.ordinal()) * originalAmount * 4);
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
