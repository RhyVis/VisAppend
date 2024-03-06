package com.rhynia.gtnh.append.common.recipe.GTRecipePool;

import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.BUCKETS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.INGOTS;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.RECIPE_MV;
import static com.rhynia.gtnh.append.api.enums.VA_Values.RecipeValues.SECONDS;

import com.github.bartimaeusnek.bartworks.API.recipe.BartWorksRecipeMaps;
import com.rhynia.gtnh.append.api.interfaces.IRecipePool;
import com.rhynia.gtnh.append.common.loader.VA_ItemList;
import com.rhynia.gtnh.append.common.material.VA_Materials;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IRecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GT_Utility;
import gtPlusPlus.core.material.ELEMENT;

public class VACommonRecipePool implements IRecipePool {

    @Override
    public void loadRecipesCompleteInit() {
        final IRecipeMap EMS = RecipeMaps.electroMagneticSeparatorRecipes;
        final IRecipeMap IMP = BartWorksRecipeMaps.electricImplosionCompressorRecipes;

        // region 星辉
        // 磁析神秘
        GT_Values.RA.stdBuilder()
            .itemInputs(Materials.Thaumium.getDust(8))
            .itemOutputs(Materials.Iron.getDust(6), VA_Materials.Astrium.get(OrePrefixes.dust, 4))
            .noOptimize()
            .eut(RECIPE_MV)
            .duration(21 * SECONDS)
            .addTo(EMS);
        // 聚爆星辉
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_Materials.AstriumInfinity.get(OrePrefixes.dust, 8))
            .fluidInputs(VA_Materials.Astrium.getMolten(2 * INGOTS))
            .itemOutputs(VA_ItemList.AstriumInfinityGem.get(1))
            .noOptimize()
            .eut(6000000)
            .duration(1)
            .addTo(IMP);
        GT_Values.RA.stdBuilder()
            .itemInputs(MaterialsUEVplus.SpaceTime.getDust(64), ELEMENT.STANDALONE.HYPOGEN.getDust(32))
            .fluidInputs(VA_Materials.AstriumInfinity.getMolten(48 * INGOTS))
            .itemOutputs(GT_Utility.copyAmountUnsafe(256, VA_ItemList.AstriumInfinityGem.get(1)))
            .noOptimize()
            .eut(24000000)
            .duration(1)
            .addTo(IMP);
        // 星辉残留
        GT_Values.RA.stdBuilder()
            .fluidInputs(VA_Materials.AstralResidue.getFluidOrGas(32 * BUCKETS))
            .fluidOutputs(ELEMENT.STANDALONE.HYPOGEN.getFluidStack(16 * INGOTS))
            .noOptimize()
            .eut(8000000)
            .duration(1)
            .addTo(IMP);
        GT_Values.RA.stdBuilder()
            .itemInputs(VA_ItemList.PreTesseract.get(1))
            .fluidInputs(VA_Materials.AstralResidue.getFluidOrGas(16 * BUCKETS))
            .fluidOutputs(MaterialsUEVplus.TranscendentMetal.getMolten(4 * INGOTS))
            .noOptimize()
            .eut(32000000)
            .duration(1)
            .addTo(IMP);
        // endregion

    }
}
