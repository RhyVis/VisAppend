package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static gregtech.api.enums.TierEU.*;

import com.rhynia.gtnh.append.common.machine.mapRecipe.VARecipe;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Recipe;

public class VASAssemblyMatrixRecipePool implements IRecipePool {

    @Override
    public void loadRecipes() {
        final GT_Recipe.GT_Recipe_Map AM = VARecipe.instance.AssemblyMatrixRecipes;
        // 透镜配方
        GT_Values.RA.stdBuilder()
            .itemInputs(
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 4),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 4),
                VA_WerkstoffMaterialPool.Primogem.get(OrePrefixes.lens,64),
                VA_WerkstoffMaterialPool.Originiums.get(OrePrefixes.lens,64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 4),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 4),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.AstroInf.getGems(16),
                VA_GregtechMaterialPool.AstroInf.getGems(16),
                Materials.Redstone.getDust(64),
                Materials.Redstone.getDust(64))
            .fluidInputs(
                Materials.Water.getFluid(32000),
                Materials.Lava.getFluid(32000),
                Materials.Neutronium.getMolten(144000),
                Materials.CosmicNeutronium.getMolten(144000),
                Materials.Silver.getMolten(144000),
                Materials.Infinity.getMolten(144000))
            .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 1))
            .noOptimize()
            .eut(RECIPE_UXV)
            .duration(900 * 20)
            .addTo(AM);
    }
}
