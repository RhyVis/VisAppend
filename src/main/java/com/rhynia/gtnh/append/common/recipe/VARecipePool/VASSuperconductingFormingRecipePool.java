package com.rhynia.gtnh.append.common.recipe.VARecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import com.rhynia.gtnh.append.api.enums.refHelper.SCPart;
import com.rhynia.gtnh.append.api.enums.refHelper.SolderMaterial;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.api.recipe.AppendRecipeMaps;
import com.rhynia.gtnh.append.api.recipe.builder.VA_RecipeBuilder;
import com.rhynia.gtnh.append.api.util.ItemHelper;
import com.rhynia.gtnh.append.common.material.VAMaterials;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.util.GT_Utility;

public class VASSuperconductingFormingRecipePool implements IRecipePool {

    private final RecipeMap<?> SF = AppendRecipeMaps.superconductingFormingRecipes;

    @Override
    public void loadRecipesCompleteInit() {

        // Wire x1 from dust
        for (SCPart SC : SCPart.values()) {
            VA_RecipeBuilder.builder()
                .itemInputs(
                    GT_Utility.getIntegratedCircuit(1),
                    SC.getPump(4),
                    ItemHelper.setStackSize(SC.getDust(1), 128))
                .itemOutputs(ItemHelper.setStackSize(SC.getWire(1), 512))
                .fluidInputs(
                    VAMaterials.Astrium.getMolten(SC.getMultiplier() * INGOTS),
                    SC.getSxEqualFluid(32),
                    Materials.Helium.getGas(SC.getMultiplier() * 8L * BUCKETS))
                .eut(SC.getRecipeVoltage())
                .duration(64 * SECONDS)
                .addTo(SF);
        }

        // Wire x1 from molten
        for (SCPart SC : SCPart.values()) {
            VA_RecipeBuilder.builder()
                .itemInputs(GT_Utility.getIntegratedCircuit(11), SC.getPump(4))
                .itemOutputs(ItemHelper.setStackSize(SC.getWire(1), 512))
                .fluidInputs(SC.getMolten(128 * INGOTS), Materials.Helium.getGas(SC.getMultiplier() * 8L * BUCKETS))
                .eut(SC.getRecipeVoltage())
                .duration(16 * SECONDS)
                .addTo(SF);
        }

        // Frame from molten
        for (SCPart SC : SCPart.values()) {
            VA_RecipeBuilder.builder()
                .itemInputs(GT_Utility.getIntegratedCircuit(4), ItemHelper.setStackSize(SC.getDust(1), 256))
                .itemOutputs(SC.getFrame(64))
                .fluidInputs(SolderMaterial.SolderingAlloy.getFluidStack(2 * SC.getMultiplier() * INGOTS))
                .eut(SC.getRecipeVoltageLow())
                .duration(20 * SECONDS)
                .addTo(SF);
        }

        // Solenoid from molten
        for (SCPart SC : SCPart.values()) {
            VA_RecipeBuilder.builder()
                .itemInputs(GT_Utility.getIntegratedCircuit(8), ItemHelper.setStackSize(SC.getWire(1), 128))
                .itemOutputs(SC.getSolenoid(16))
                .fluidInputs(SolderMaterial.IndaAlloy.getFluidStack(SC.getPowValue() * INGOTS))
                .eut(SC.getRecipeVoltageHigh())
                .duration(16 * SECONDS)
                .addTo(SF);
        }

        // Base from Sx
        for (SCPart SC : SCPart.values()) {
            VA_RecipeBuilder.builder()
                .itemInputs(
                    GT_Utility.getIntegratedCircuit(24),
                    SC.getSolenoid(0),
                    ItemHelper.setStackSize(VAMaterials.Astrium.get(OrePrefixes.dust, 1), 16 * SC.getMultiplier()))
                .itemOutputs(SC.getDust(64))
                .fluidInputs(SC.getSxEqualFluid(16), Materials.UUMatter.getFluid(SC.getPowValue() * 500L))
                .fluidOutputs(SC.getMolten(512 * INGOTS))
                .eut(SC.getRecipeVoltageHigh())
                .duration(32 * SECONDS)
                .addTo(SF);
        }

    }

    @Override
    public void loadRecipesPostInit() {}
}
