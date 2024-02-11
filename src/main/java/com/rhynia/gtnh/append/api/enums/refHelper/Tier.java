package com.rhynia.gtnh.append.api.enums.refHelper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.rhynia.gtnh.append.common.VAItemList;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;

@SuppressWarnings("unused")
public enum Tier {

    ULV(Materials.Primitive),
    LV(Materials.Basic),
    MV(Materials.Good),
    HV(Materials.Advanced),
    EV(Materials.Data),
    IV(Materials.Elite),
    LuV(Materials.Master),
    ZPM(Materials.Ultimate),
    UV(Materials.SuperconductorUHV),
    UHV(Materials.Infinite),
    UEV(Materials.Bio),
    UIV(Materials.Optical),
    UMV(Materials.Exotic, Materials.Piko),
    UXV(Materials.Cosmic, Materials.Quantum),
    MAX(Materials.Transcendent);

    private final Materials material;
    private Materials altMaterial;

    Tier(Materials material) {
        this.material = material;
    }

    Tier(Materials material, Materials altMaterial) {
        this.material = material;
        this.altMaterial = altMaterial;
    }

    public long getVoltage() {
        return GT_Values.V[this.ordinal()];
    }

    public long getVoltageRecipe() {
        return GT_Values.VP[this.ordinal()];
    }

    public SCPart getSC() {
        if (this == ULV || this == LV || this == UXV || this == MAX) {
            throw new IllegalArgumentException("This tier has no standard superconducting!");
        } else {
            return SCPart.values()[this.ordinal() - 2];
        }
    }

    public SolderMaterial getSolder() {
        return switch (this) {
            case ULV, LV, MV, HV, EV -> SolderMaterial.SolderingAlloy;
            case IV, LuV, ZPM, UV, UHV -> SolderMaterial.IndaAlloy;
            case UEV, UIV, UMV, UXV, MAX -> SolderMaterial.MutatedLivingAlloy;
        };
    }

    public FluidStack getSolderStack(int amount) {
        return this.getSolder()
            .getFluidStack(amount);
    }

    public Materials getCircuitMaterial() {
        return getCircuitMaterial(true);
    }

    public Materials getCircuitMaterial(boolean compatibility) {
        return switch (this) {
            case ULV, LV, MV, HV, EV, IV, LuV, ZPM, UV, UHV, UEV, UIV, MAX -> this.material;
            case UMV, UXV -> compatibility ? this.altMaterial : this.material;
        };
    }

    public ItemStack getCircuit(int amount) {
        return GT_OreDictUnificator.get(OrePrefixes.circuit, this.getCircuitMaterial(), amount);
    }

    public ItemStack getCircuitWrap(int amount) {
        return GGChip.values()[this.ordinal()].getItemStack(amount);
    }

    public enum Type {

        Electric_Motor,
        Electric_Piston,
        Electric_Pump,
        Robot_Arm,
        Conveyor_Module,
        Emitter,
        Sensor,
        Field_Generator;

        @Override
        public String toString() {
            return super.toString() + "_";
        }

    }

    public ItemStack getComponent(int amount, Type type) {
        if (this == ULV) return VAItemList.Test.get(amount);
        return ItemList.valueOf(type.toString() + this)
            .get(amount);
    }
}
