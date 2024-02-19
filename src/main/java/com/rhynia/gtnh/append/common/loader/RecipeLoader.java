package com.rhynia.gtnh.append.common.loader;

import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VACentrifugeRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VAChemicalReactorRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VACommonRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VACompressorRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VAElectrolyzeRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VAFusionRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VAHammerRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VALaserEngraverRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VAMachineRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VAMixerRecipePool;
import com.rhynia.gtnh.append.common.recipe.GTRecipePool.VAPlasmaForgeRecipePool;
import com.rhynia.gtnh.append.common.recipe.VARecipePool.VASAstraForgeRecipePool;
import com.rhynia.gtnh.append.common.recipe.VARecipePool.VASCompatibilityRecipePool;
import com.rhynia.gtnh.append.common.recipe.VARecipePool.VASIntegratedAssemblyRecipePool;
import com.rhynia.gtnh.append.common.recipe.VARecipePool.VASMicroAssemblyRecipePool;
import com.rhynia.gtnh.append.common.recipe.VARecipePool.VASQuarkRefactoringRecipePool;
import com.rhynia.gtnh.append.common.recipe.VARecipePool.VASSuperconductingFormingRecipePool;
import com.rhynia.gtnh.append.common.recipe.VARecipePool.VASThermonuclearControlRecipePool;
import com.rhynia.gtnh.append.common.recipe.VARecipePool.VASTranscendentReactorRecipePool;
import com.rhynia.gtnh.append.common.recipe.VARecipePool.VASVoidEnergyGeneratorRecipePool;

public class RecipeLoader {

    public static void loadRecipesPostInit() {
        IRecipePool[] recipePools = new IRecipePool[] {
            // VA Special Recipe
            new VASIntegratedAssemblyRecipePool(), new VASVoidEnergyGeneratorRecipePool(),
            new VASThermonuclearControlRecipePool(), new VASAstraForgeRecipePool(), new VASMicroAssemblyRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipesPostInit();
        }
    }

    public static void loadRecipesCompleteInit() {
        IRecipePool[] recipePools = new IRecipePool[] {
            // VA Special Recipe
            new VASIntegratedAssemblyRecipePool(), new VASVoidEnergyGeneratorRecipePool(),
            new VASThermonuclearControlRecipePool(), new VASAstraForgeRecipePool(), new VASMicroAssemblyRecipePool(),
            new VASTranscendentReactorRecipePool(), new VAPlasmaForgeRecipePool(),
            new VASSuperconductingFormingRecipePool(), new VASQuarkRefactoringRecipePool(),
            // Compatibility
            new VASCompatibilityRecipePool(),
            // Basic & Misc Recipe
            new VACommonRecipePool(), new VAMachineRecipePool(),
            // GT Recipe
            new VACentrifugeRecipePool(), new VAElectrolyzeRecipePool(), new VAMixerRecipePool(),
            new VAChemicalReactorRecipePool(), new VAHammerRecipePool(), new VALaserEngraverRecipePool(),
            new VAFusionRecipePool(), new VACompressorRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipesCompleteInit();
        }
    }
}
