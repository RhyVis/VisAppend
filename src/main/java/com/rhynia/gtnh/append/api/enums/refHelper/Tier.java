package com.rhynia.gtnh.append.api.enums.refHelper;

import static gregtech.api.enums.Mods.BartWorks;
import static gregtech.api.enums.Mods.GTPlusPlus;
import static gregtech.api.enums.Mods.GoodGenerator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import com.github.technus.tectech.loader.recipe.BaseRecipeLoader;
import com.github.technus.tectech.thing.CustomItemList;
import com.rhynia.gtnh.append.VisAppend;
import com.rhynia.gtnh.append.common.loader.VA_ItemList;
import com.rhynia.gtnh.append.common.loader.VA_WirelessExtraItemList;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;

@SuppressWarnings({ "unused", "SpellCheckingInspection" })
public enum Tier {

    ULV(Materials.Primitive),
    LV(Materials.Basic),
    MV(Materials.Good),
    HV(Materials.Advanced),
    EV(Materials.Data),
    IV(Materials.Elite),
    LuV(Materials.Master),
    ZPM(Materials.Ultimate),
    UV(Materials.SuperconductorUHV), // Due to optimization
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

    public enum Component {

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

    public enum Hatch {

        Dynamo,
        Energy,
        Energy4A,
        Energy16A,
        Energy64A,
        LaserEnergy,
        LaserDynamo,
        WirelessDynamo,
        WirelessEnergy,
        WirelessEnergy4A,
        WirelessEnergy16A,
        WirelessEnergy64A,
        WirelessLaser;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public long getVoltage() {
        return GT_Values.V[this.ordinal()];
    }

    public long getVoltageRecipe() {
        return GT_Values.VP[this.ordinal()];
    }

    public SCPart getSC() {
        switch (this) {
            case ULV, LV -> {
                VisAppend.LOG.error(this + "tier has no standard SC material!");
                return SCPart.MV;
            }
            case UXV, MAX -> {
                VisAppend.LOG.error(this + "tier has no standard SC material!");
                return SCPart.UMV;
            }
            default -> {
                return SCPart.values()[this.ordinal() - 2];
            }
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
            case UMV, UXV -> compatibility ? this.altMaterial : this.material;
            default -> this.material;
        };
    }

    public ItemStack getCircuit(int amount) {
        return GT_OreDictUnificator.get(OrePrefixes.circuit, this.getCircuitMaterial(), amount);
    }

    public ItemStack getCircuitWrap(int amount) {
        return GGChip.values()[this.ordinal()].getItemStack(amount);
    }

    public ItemStack getComponent(Component component, int amount) {
        if (this == ULV) {
            VisAppend.LOG.error("Attempting to get ULV component, but it's already removed!");
            return VA_ItemList.Test01.get(amount, VA_ItemList.Test01.get(1));
        } else return ItemList.valueOf(component.toString() + this)
            .get(amount, VA_ItemList.Test01.get(1));
    }

    public ItemStack getCoil(int amount) {
        switch (this) {
            case UEV, UIV, UMV, UXV, MAX -> {
                VisAppend.LOG.error("Attempting to get " + this + " component, but it doesn't exist!");
                return VA_ItemList.Test01.get(amount, VA_ItemList.Test01.get(1));
            }
            default -> {
                return ItemList.valueOf(this + "_Coil")
                    .get(amount, VA_ItemList.Test01.get(1));
            }
        }
    }

    public ItemStack getComponentAssemblyCasing(int amount) {
        if (this == ULV) {
            VisAppend.LOG.error("Attempting to get ULV casing, but it doesn't exist!");
            return VA_ItemList.Test01.get(amount, VA_ItemList.Test01.get(1));
        } else return GT_ModHandler
            .getModItem(GoodGenerator.ID, "componentAssemblylineCasing", amount, this.ordinal() - 1);
    }

    public ItemStack getGlass(int amount) {
        switch (this) {
            case ULV, LV, MV, HV -> {
                return GT_ModHandler.getModItem(BartWorks.ID, "BW_GlasBlocks", amount, 0);
            }
            case UMV, UXV, MAX -> {
                return GT_ModHandler.getModItem(BartWorks.ID, "BW_GlasBlocks2", amount);
            }
            default -> {
                return GT_ModHandler.getModItem(BartWorks.ID, "BW_GlasBlocks", amount, this.ordinal() - 3);
            }
        }
    }

    public ItemStack getBufferCore(int amount) {
        switch (this) {
            case UHV, UEV, UIV, UMV, UXV, MAX -> {
                return GT_ModHandler.getModItem(GTPlusPlus.ID, "item.itemBufferCore10", amount);
            }
            default -> {
                return GT_ModHandler.getModItem(GTPlusPlus.ID, "item.itemBufferCore" + (this.ordinal() + 1), amount);
            }
        }
    }

    public ItemStack getHatch(@NotNull Hatch hatch, int amount) {
        return switch (hatch) {
            case Dynamo -> this.getDynamoHatch(amount);
            case Energy -> this.getEnergyHatch(amount);
            case Energy4A -> this.getEnergyHatch4A(amount);
            case Energy16A -> this.getEnergyHatch16A(amount);
            case Energy64A -> this.getEnergyHatch64A(amount);
            case LaserEnergy -> this.getLaserTarget(1, amount);
            case LaserDynamo -> VA_ItemList.Test01.get(amount);
            case WirelessDynamo -> this.getDynamoWireless(amount);
            case WirelessEnergy -> this.getEnergyWireless(amount);
            case WirelessEnergy4A -> this.getEnergyWireless4A(amount);
            case WirelessEnergy16A -> this.getEnergyWireless16A(amount);
            case WirelessEnergy64A -> this.getEnergyWireless64A(amount);
            case WirelessLaser -> this.getLaserWireless(1, amount);
        };
    }

    public ItemStack getDynamoHatch(int amount) {
        return switch (this) {
            case MAX -> VA_ItemList.Test01.get(1);
            case ULV, LV, MV, HV, EV, IV, LuV, ZPM, UV -> ItemList.valueOf("Hatch_Dynamo_" + this)
                .get(amount, VA_ItemList.Test01.get(1));
            case UHV -> ItemList.Hatch_Dynamo_MAX.get(amount);
            case UEV, UIV, UMV, UXV -> BaseRecipeLoader.getItemContainer("Hatch_Dynamo_" + this)
                .get(amount, VA_ItemList.Test01.get(1));
        };
    }

    public ItemStack getEnergyHatch(int amount) {
        return switch (this) {
            case MAX -> VA_ItemList.Test01.get(1);
            case ULV, LV, MV, HV, EV, IV, LuV, ZPM, UV -> ItemList.valueOf("Hatch_Energy_" + this)
                .get(amount, VA_ItemList.Test01.get(1));
            case UHV -> ItemList.Hatch_Energy_MAX.get(amount);
            case UEV, UIV, UMV, UXV -> BaseRecipeLoader.getItemContainer("Hatch_Energy_" + this)
                .get(amount, VA_ItemList.Test01.get(1));
        };
    }

    public ItemStack getEnergyHatch4A(int amount) {
        switch (this) {
            case ULV, LV, MV, HV, MAX -> {
                VisAppend.LOG.error("Attempting to get " + this + " 4A energy hatch, but it doesn't exist!");
                return VA_ItemList.Test01.get(amount);
            }
            case EV, IV, LuV, ZPM, UV, UHV, UEV, UIV, UMV, UXV -> {
                return CustomItemList.valueOf("eM_energyMulti4_" + this)
                    .get(amount, VA_ItemList.Test01.get(1));
            }
        }
        return VA_ItemList.Test01.get(1);
    }

    public ItemStack getEnergyHatch16A(int amount) {
        switch (this) {
            case ULV, LV, MV, HV, MAX -> {
                VisAppend.LOG.error("Attempting to get " + this + " 16A energy hatch, but it doesn't exist!");
                return VA_ItemList.Test01.get(amount);
            }
            case EV, IV, LuV, ZPM, UV, UHV, UEV, UIV, UMV, UXV -> {
                return CustomItemList.valueOf("eM_energyMulti16_" + this)
                    .get(amount, VA_ItemList.Test01.get(1));
            }
        }
        return VA_ItemList.Test01.get(1);
    }

    public ItemStack getEnergyHatch64A(int amount) {
        switch (this) {
            case ULV, LV, MV, HV, MAX -> {
                VisAppend.LOG.error("Attempting to get " + this + " 64A energy hatch, but it doesn't exist!");
                return VA_ItemList.Test01.get(amount);
            }
            case EV, IV, LuV, ZPM, UV, UHV, UEV, UIV, UMV, UXV -> {
                return CustomItemList.valueOf("eM_energyMulti64_" + this)
                    .get(amount, VA_ItemList.Test01.get(1));
            }
        }
        return VA_ItemList.Test01.get(1);
    }

    public ItemStack getDynamoWireless(int amount) {
        if (this == MAX) {
            return VA_ItemList.Test01.get(1);
        }
        return ItemList.valueOf("Wireless_Dynamo_Energy_" + this)
            .get(amount, VA_ItemList.Test01.get(1));
    }

    public ItemStack getEnergyWireless(int amount) {
        if (this == MAX) {
            return VA_ItemList.Test01.get(1);
        }
        return ItemList.valueOf("Wireless_Hatch_Energy_" + this)
            .get(amount, VA_ItemList.Test01.get(1));
    }

    public ItemStack getEnergyWireless4A(int amount) {
        return switch (this) {
            case ULV, LV, MV, HV, MAX -> VA_ItemList.Test01.get(1);
            default -> CustomItemList.valueOf("eM_energyWirelessMulti4_" + this)
                .get(amount, VA_ItemList.Test01.get(1));
        };
    }

    public ItemStack getEnergyWireless16A(int amount) {
        return switch (this) {
            case ULV, LV, MV, HV, MAX -> VA_ItemList.Test01.get(1);
            default -> CustomItemList.valueOf("eM_energyWirelessMulti16_" + this)
                .get(amount, VA_ItemList.Test01.get(1));
        };
    }

    public ItemStack getEnergyWireless64A(int amount) {
        return switch (this) {
            case ULV, LV, MV, HV, MAX -> VA_ItemList.Test01.get(1);
            default -> CustomItemList.valueOf("eM_energyWirelessMulti64_" + this)
                .get(amount, VA_ItemList.Test01.get(1));
        };
    }

    public ItemStack getLaserTarget(@Range(from = 1, to = 7) int tier, int amount) {
        return switch (this) {
            case ULV, LV, MV, HV, EV, MAX -> VA_ItemList.Test01.get(1);
            default -> CustomItemList.valueOf("eM_energyTunnel" + tier + "_" + this)
                .get(amount, VA_ItemList.Test01.get(1));
        };
    }

    public ItemStack getLaserWireless(@Range(from = 1, to = 7) int tier, int amount) {
        return switch (this) {
            case ULV, LV, MV, HV, EV, MAX -> VA_ItemList.Test01.get(1);
            case UXV -> CustomItemList.valueOf("eM_energyWirelessTunnel" + tier + "_UXV")
                .get(amount, VA_ItemList.Test01.get(1));
            default -> VA_WirelessExtraItemList.valueOf("extLaser_" + this + "_" + tier)
                .get(amount, VA_ItemList.Test01.get(1));
        };
    }
}
