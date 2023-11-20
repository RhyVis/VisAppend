package com.rhynia.gtnh.append.recipe.container.VARecipePool;

import static gregtech.api.enums.TierEU.*;

import com.rhynia.gtnh.append.common.VAItemList;
import com.rhynia.gtnh.append.common.machine.mapRecipe.VARecipe;
import com.rhynia.gtnh.append.common.material.VA_GregtechMaterialPool;
import com.rhynia.gtnh.append.common.material.VA_WerkstoffMaterialPool;
import com.rhynia.gtnh.append.recipe.IRecipePool;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
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
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroInf, 64),
                VA_WerkstoffMaterialPool.Primogem.get(OrePrefixes.lens, 64),
                VA_WerkstoffMaterialPool.Originiums.get(OrePrefixes.lens, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 64),
                GT_OreDictUnificator.get(OrePrefixes.lens, VA_GregtechMaterialPool.AstroMagic, 64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.Astro.getDust(64),
                VA_GregtechMaterialPool.AstroInf.getGems(64),
                VA_GregtechMaterialPool.AstroInf.getGems(64),
                MaterialsUEVplus.Eternity.getNanite(64),
                MaterialsUEVplus.Eternity.getNanite(64))
            .fluidInputs(
                MaterialsUEVplus.Universium.getMolten(144000000),
                MaterialsUEVplus.ExcitedDTSC.getFluid(120000000),
                Materials.Neutronium.getMolten(144000000),
                Materials.CosmicNeutronium.getMolten(144000000),
                MaterialsUEVplus.SpaceTime.getMolten(144000000),
                Materials.Infinity.getMolten(144000000))
            .itemOutputs(VAItemList.ItemUltimate.get(1))
            .noOptimize()
            .eut(RECIPE_MAX)
            .duration(16000 * 20)
            .addTo(AM);
    }
}
