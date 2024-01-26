package com.rhynia.gtnh.append.loader;

import com.rhynia.gtnh.append.common.recipePool.IRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VACentrifugeRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VAChemicalReactorRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VACommonRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VACompressorRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VAElectrolyzeRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VAFusionRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VAHammerRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VALaserEngraverRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VAMachineRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VAMixerRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.GTRecipePool.VAPlasmaForgeRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.VARecipePool.VASAstraForgeRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.VARecipePool.VASIntegratedAssemblyRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.VARecipePool.VASMicroAssemblyRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.VARecipePool.VASSuperconductingBinderRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.VARecipePool.VASThermonuclearControlRecipePool;
import com.rhynia.gtnh.append.common.recipePool.container.VARecipePool.VASTranscendentReactorRecipePool;

public class RecipeLoader {

    public static void loadRecipesPostInit() {
        IRecipePool[] recipePools = new IRecipePool[] {
            // VA Special Recipe
            new VASIntegratedAssemblyRecipePool(), new VASSuperconductingBinderRecipePool(),
            new VASThermonuclearControlRecipePool(), new VASAstraForgeRecipePool(), new VASMicroAssemblyRecipePool() };

        for (IRecipePool recipePool : recipePools) {
            recipePool.loadRecipesPostInit();
        }
    }

    public static void loadRecipesCompleteInit() {
        IRecipePool[] recipePools = new IRecipePool[] {
            // VA Special Recipe
            new VASIntegratedAssemblyRecipePool(), new VASSuperconductingBinderRecipePool(),
            new VASThermonuclearControlRecipePool(), new VASAstraForgeRecipePool(), new VASMicroAssemblyRecipePool(),
            new VASTranscendentReactorRecipePool(), new VAPlasmaForgeRecipePool(),
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
