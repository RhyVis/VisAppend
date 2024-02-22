package com.rhynia.gtnh.append.api.process.processingLogic;

import javax.annotation.Nonnull;

import gregtech.api.logic.ProcessingLogic;
import gregtech.api.util.GT_ParallelHelper;
import gregtech.api.util.GT_Recipe;

public class VA_ProcessingLogic extends ProcessingLogic {

    /**
     * Long output limit.
     */
    @Nonnull
    @Override
    protected GT_ParallelHelper createParallelHelper(@Nonnull GT_Recipe recipe) {
        return new VA_ParallelHelper().setRecipe(recipe)
            .setItemInputs(inputItems)
            .setFluidInputs(inputFluids)
            .setAvailableEUt(availableVoltage * availableAmperage)
            .setMachine(machine, protectItems, protectFluids)
            .setRecipeLocked(recipeLockableMachine, isRecipeLocked)
            .setMaxParallel(maxParallel)
            .setEUtModifier(euModifier)
            .enableBatchMode(batchSize)
            .setConsumption(true)
            .setOutputCalculation(true);
    }
}
