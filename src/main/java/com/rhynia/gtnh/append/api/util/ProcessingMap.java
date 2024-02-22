package com.rhynia.gtnh.append.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.rhynia.gtnh.append.api.enums.processingUtil.ProcessingEnum;

import gregtech.api.recipe.RecipeMap;

public class ProcessingMap {

    public static Map<String, ArrayList<RecipeMap<?>>> PROCESSING_MAP;

    public static void generateMap() {
        if (PROCESSING_MAP == null) PROCESSING_MAP = new HashMap<>();
        for (ProcessingEnum process : ProcessingEnum.values()) {
            ArrayList<RecipeMap<?>> tempArray;
            String tempStack = process.getProcessingMachine()
                .getUnlocalizedName();
            if (!PROCESSING_MAP.containsKey(tempStack)) {
                tempArray = new ArrayList<>();
            } else {
                tempArray = PROCESSING_MAP.get(tempStack);
            }
            tempArray.add(process.getProcessingRecipeMap());
            PROCESSING_MAP.put(tempStack, tempArray);
        }
    }
}
